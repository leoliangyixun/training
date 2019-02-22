package com.training.passport.account;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.hujiang.basic.framework.business.account.common.HJRequestContextHolder;
import com.hujiang.basic.framework.business.account.common.UserDomainMapping;
import com.hujiang.basic.framework.core.config.BaseProperties;
import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.basic.framework.core.threads.ThreadPool;
import com.hujiang.basic.framework.core.util.DateUtil;
import com.hujiang.basic.framework.core.util.HttpClientUtil;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.basic.framework.plugin.dfs.model.DfsFile;
import com.hujiang.basic.framework.plugin.dfs.service.TakaService.Builder;
import com.hujiang.basic.framework.plugin.mq.delivery.MessageType;
import com.hujiang.basic.framework.plugin.mq.delivery.Publisher;
import com.hujiang.pass.integration.sdk.HjApiSDK;
import com.hujiang.pass.support.common.SecurityHelper;
import com.hujiang.pass.support.common.StringHelper;
import com.hujiang.pass.support.common.Utils;
import com.hujiang.pass.support.constants.ResponseEnum;
import com.hujiang.pass.support.constants.SysConst;
import com.hujiang.pass.support.constants.UserInfoChangeEnum;
import com.hujiang.pass.support.model.bo.CurrentUser;
import com.hujiang.pass.support.model.bo.UserInfoChangeEntity;
import com.hujiang.pass.support.model.dto.account.Avatar;
import com.hujiang.pass.support.model.dto.account.CropInfo;
import com.hujiang.usercenter.soa.api.dto.request.UserAvatarUpd;
import com.hujiang.usercenter.soa.api.dto.response.UserAvatarUpdRes;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.hujiang.pass.support.constants.SysConst.AVATAR_UPLOAD_PATH;

/**
 * Created by yangkai on 2017/8/4.
 */
@Slf4j
@Service
public class AvatarService {

    //private static final String USER_INFO_KEY = "user:info:%s";
    private static final int[] IMAGE_SIZE = new int[] { 18, 48, 96, 120, 200 };

    @Value("${image.url}")
    private String imageUrlHost;

    @Value("${rest.api.url}")
    private String restApiUrl;

    @Autowired
    private DfsService dfsService;

    @Autowired
    private HjApiSDK hjApiSDK;

    @Autowired
    private PublishHelper publishHelper;

    //private ExecutorService executor = Executors.newFixedThreadPool(20);
    private ExecutorService executor = ThreadPool.newFixedThreadPool("avatar-upload", 30, 200);


    /**
     * 图像不落地
     * @param userId
     * @param content
     * @return
     * @throws IOException
     */
    public Avatar upload(Integer userId, byte[] content) throws IOException {
        String imageUrl = "";
        if (content != null && content.length > 0) {
            String hash = StringHelper.getRandomString(15);
            Builder builder = dfsService.getDfsSdk().advance();
            long start = System.currentTimeMillis();
            for (int size : IMAGE_SIZE) {
                String customPath =  "/" + size + "/" + userId + "/" + hash + ".jpg";
                byte[] copied = Arrays.copyOf(content, content.length);

                try (
                        ByteArrayInputStream input = new ByteArrayInputStream(copied);
                        ByteArrayOutputStream output = new ByteArrayOutputStream(1024 * 2)
                ) {
                    Thumbnails.of(input).size(size, size).outputQuality(1.0f).keepAspectRatio(false).toOutputStream(output);

                    builder.add(new DfsFile(null, customPath, output.toByteArray()));
                    if (size == 200) {
                        imageUrl = imageUrlHost + "/u/" + size + "/" + userId + "/" + hash + ".jpg";
                        updateAvatarUrl(userId, hash);
                    }
                } catch (Exception e) {
                    log.error(SysConst.ERROR_STACK, e);
                    throw new AppException(ResponseEnum.SystemError.status(), ResponseEnum.SystemError.message());
                }
            }

            long end = System.currentTimeMillis();
            log.info("upload avatar native process costs: [{} ms]", end - start);
            // upload to dfs
            start = System.currentTimeMillis();
            builder.retry(3).upload();
            end = System.currentTimeMillis();
            log.info("upload avatar to dfs costs: [{} ms]", end - start);

            final String publishUrl = imageUrl;
            final String userDomain = HJRequestContextHolder.getUserDomain();
            executor.submit(() -> {
                resetAvatarUpdatedTime(userId, userDomain);
                changeUserAvatarNotice(userId, publishUrl, userDomain);
            });

        }

        return new Avatar(imageUrl, null, null);
    }

    /**
     * 图像落地
     * @param userId
     * @param content
     * @param filename
     * @return
     * @throws IOException
     */
    public Avatar upload(Integer userId, byte[] content, String filename) throws IOException {
        String imageUrl = "";
        long start = System.currentTimeMillis();
        File image = upload2TempDir(userId, content, filename);
        long end = System.currentTimeMillis();
        log.info("upload avatar to temp directory costs: [{} ms]", end - start);
        if (image != null) {
            String hash = StringHelper.getRandomString(15);
            Builder builder = dfsService.getDfsSdk().advance();
            start = System.currentTimeMillis();
            for (int size : IMAGE_SIZE) {
                String customPath =  "/" + size + "/" + userId + "/" + hash + ".jpg";
                File tempImage = new File(new StringBuilder()
                        .append(image.getParent())
                        .append(File.separator)
                        .append(StringUtils.split(image.getName(), '.')[0])
                        .append("_")
                        .append(size)
                        .append("*")
                        .append(size)
                        .append(".")
                        .append(StringUtils.split(image.getName(), '.')[1])
                        .toString());

                FileUtils.copyFile(image, tempImage);
                Thumbnails.of(image).size(size, size).outputQuality(1.0f).keepAspectRatio(false).toFile(tempImage);
                byte[] bytes = Files.readAllBytes(tempImage.toPath());
                builder.add(new DfsFile(null, customPath, bytes));
                if (size == 200) {
                    imageUrl = imageUrlHost + "/u/" + size + "/" + userId + "/" + hash + ".jpg";
                    updateAvatarUrl(userId, hash);
                }
                //删除压缩后的临时图像
                boolean success = tempImage.delete();
                log.debug("delete image from temp dictionary : {}", success);
            }
            end = System.currentTimeMillis();
            log.info("upload avatar native process costs: [{} ms]", end - start);

            //upload to dfs
            start = System.currentTimeMillis();
            builder.retry(3).upload();
            end = System.currentTimeMillis();
            log.info("upload avatar to dfs costs: [{} ms]", end - start);
            //上传到DFS之后删除原始图像
            boolean success = image.delete();
            log.debug("delete image from temp dictionary : {}", success);

            final String publishUrl = imageUrl;
            final String userDomain = HJRequestContextHolder.getUserDomain();
            executor.submit(() -> {
                resetAvatarUpdatedTime(userId, userDomain);
                changeUserAvatarNotice(userId, publishUrl, userDomain);
            });
        }

        return new Avatar(imageUrl, null, null);
    }

    @Deprecated
    public Avatar cropAndUpload(CurrentUser currentUser, CropInfo cropInfo, File image) throws IOException {
        return upload(currentUser, crop(cropInfo, image));
    }

    /**
     * @deprecated
     * 裁剪图片
     * @param cropInfo 裁剪信息
     */
    @Deprecated
    private File crop(CropInfo cropInfo, File image) {
        try (InputStream input = new FileInputStream(image)){

            if (!image.isFile() || !image.exists()) {
                throw new AppException(ResponseEnum.UploadTokenInvalid.status(), ResponseEnum.UploadTokenInvalid.message());
            }

            // 验证图片格式是否正确
            if (!Utils.isAllowedMediaType(input)) {
                throw new AppException(ResponseEnum.FileTypeError.status(), ResponseEnum.FileTypeError.message());
            }

            String extensionName = StringUtils.split(image.getPath(), '.')[1];

       /*---------------------- 裁剪 start ----------------------*/
            BufferedImage bi = ImageIO.read(image);
            int width = bi.getWidth();
            int height = bi.getHeight();

            int x = cropInfo.getX();
            int y = cropInfo.getY();
            int w = cropInfo.getW();
            int h = cropInfo.getH();

            if (x > width || x < 0 || y > height || y < 0) {
                return null;
            }

            if (x + w > width) {
                w = width - x;
            }

            if (y + h > height) {
                h = height - y;
            }

            //ImageIO支持如下几种格式的ImageReader(jpg, bmp, gif, png, jpeg)
            Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(extensionName);
            ImageReader reader = iterator.next();
            InputStream in = new FileInputStream(image);
            ImageInputStream iis = ImageIO.createImageInputStream(in);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, w, h);
            param.setSourceRegion(rect);
            bi = reader.read(0, param);
            ImageIO.write(bi, extensionName, image);
        /*---------------------- 裁剪 end ----------------------*/
            return image;
        } catch (AppException | SysException e) {
            log.error(SysConst.ERROR_STACK, e);
            throw e;
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            throw new SysException(ResponseEnum.SystemError.status(), ResponseEnum.SystemError.message(),
                    new Avatar(null, e.getMessage(), JsonUtil.object2JSON(cropInfo)));
        }
    }

    @Deprecated
    private Avatar upload(CurrentUser currentUser, File image) throws IOException {
        String imageUrl = "";
        Integer userId = currentUser.getUserId();

        if (image != null) {
            String hash = StringHelper.getRandomString(15);
            Builder builder = dfsService.getDfsSdk().advance();
            for (int size : IMAGE_SIZE) {
                String customPath =  "/" + size + "/" + userId + "/" + hash + ".jpg";
                File tempImage = new File(image.getParent() + File.separator + size + "*" + size + "_" + image.getName());
                FileUtils.copyFile(image, tempImage);
                Thumbnails.of(image).size(size, size).outputQuality(0.7f).toFile(tempImage);
                byte[] bytes = Files.readAllBytes(tempImage.toPath());
                builder.add(new DfsFile(null, customPath, bytes));
                if (size == 200) {
                    imageUrl = imageUrlHost + "/u/" + size + "/" + userId + "/" + hash + ".jpg";
                    updateAvatarUrl(userId, hash);
                }

                boolean success = tempImage.delete();
                log.debug("delete image from temp dictionary : {}", success);
            }
            // upload files to DFS
            builder.upload();
        }

        return new Avatar(imageUrl, null, null);
    }

    private File upload2TempDir(Integer userId, byte[] content, String filename) {
        try {
            String imageName = new StringBuilder("")
                    .append(userId)
                    .append("_")
                    .append(SecurityHelper.md5(DateUtil.toDateString(new Date(), DateUtil.YYYYMMDD_DATE_PATTERN)))
                    .append(".")
                    .append(StringUtils.split(filename, '.')[1].toLowerCase()).toString();

            File uploadDir = new File(AVATAR_UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File image = new File(AVATAR_UPLOAD_PATH + imageName);

            //存在的话，删除
            if (image.exists()) {
                boolean success = image.delete();
                log.debug("delete image from temp dictionary : {}", success);
            }

            Files.write(image.toPath(), content);

            return image;
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            throw new AppException(ResponseEnum.SystemError.status(), ResponseEnum.SystemError.message());
        }
    }

    private void updateAvatarUrl(Integer userId, String hash) {
        UserAvatarUpd upd =new UserAvatarUpd(userId, hash);
        UserAvatarUpdRes res = hjApiSDK.getHjPassSdk().updateUserHeadImage(upd);
        if (res.getStatus() != 0) {
            throw new AppException(ResponseEnum.SystemError.status(), "update avatar url to ucenter encounter exception ");
        }
    }

    private boolean resetAvatarUpdatedTime(Integer userId, String userDomain) {
        HJRequestContextHolder.setUserDomain(userDomain);
        String appKey = BaseProperties.getString("hjapi.appkey");

        BasicHeader[] headers = new BasicHeader[] {
                new BasicHeader("hj_appkey", appKey),
                new BasicHeader("hj_signmethod", "MD5"),
                new BasicHeader("hj_deviceId", "pass-web-server"),
                new BasicHeader("hj_deviceType", "pass-web-server"),
                new BasicHeader("X-USER-DOMAIN", userDomain),
                new BasicHeader("Content-Type", "application/json"),
                new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36")
        };

        Map<String, Object> map = ImmutableMap.of("UserId", userId);
        String result = HttpClientUtil.sendHttpPut(restApiUrl, JsonUtil.object2JSON(map), headers);
        log.info("reset avatar updated time, url: {}, params: {}, result: {}", restApiUrl, map, result);

        return StringUtils.isNotBlank(result) && JSONObject.parseObject(result).getBooleanValue("IsSuccess");
    }

    private void changeUserAvatarNotice(Integer userId, String imageUrl, String userDomain) {
        HJRequestContextHolder.setUserDomain(userDomain);
        String exchange = BaseProperties.getProperty(UserDomainMapping.routePrefix("mq.exchange.pass"), String.class);
        String routingKey = BaseProperties.getProperty(UserDomainMapping.routePrefix("mq.routingkey"), String.class);
        publishHelper.publish(new UserInfoChangeEntity(exchange, routingKey, userId, UserInfoChangeEnum.Avatar.code(), imageUrl), MessageType.PLAIN);
        log.info("send avatar change notify, userId: {}, imageUrl: {}", userId, imageUrl);
    }
}
