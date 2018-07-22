package com.training.passport.account;

import com.google.common.collect.ImmutableMap;
import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.pass.integration.annotation.LoginRequired;
import com.hujiang.pass.integration.interceptors.ClubAuthInterceptor;
import com.hujiang.pass.service.account.AvatarService;
import com.hujiang.pass.support.common.SecurityHelper;
import com.hujiang.pass.support.common.Utils;
import com.hujiang.pass.support.common.cache.CacheManager;
import com.hujiang.pass.support.common.cache.KeyStruct;
import com.hujiang.pass.support.constants.ResponseEnum;
import com.hujiang.pass.support.constants.SysConst;
import com.hujiang.pass.support.model.dto.account.Avatar;
import com.hujiang.pass.support.model.dto.response.BaseRes;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static com.hujiang.pass.support.constants.SysConst.ALLOWED_EXTENSIONS;

/**
 * 之前.NET上传图像分2步：(1)上传图像到本地临时目录 (2)裁减第一步上传的图像并上传裁减后的图像到DFS
 * 现在 JAVA只提供一个接口
 * Created by yangkai on 2017/6/16.
 */
@Slf4j
@RestController
@RequestMapping("/account/api")
public class AvatarController extends BaseController {
    private static final long MAX_AVATAR_SIZE = 1024 * 1024 * 2L;

    @Autowired
    private AvatarService avatarService;

    /**
     * @param image
     * @return
     */
    @ApiOperation(value = "上传头像", httpMethod = "POST", response = ResponseEntity.class)
    @LoginRequired
    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public ResponseEntity<BaseRes<?>> avatar(@RequestParam("file") MultipartFile image, HttpServletRequest request) {
        BaseRes<?> res ;
        String contentType = request.getContentType();
        this.isDisableApi();


        try {

            if (!ContentType.MULTIPART_FORM_DATA.getMimeType().equals(StringUtils.split(contentType, ";")[0])) {
                res = BaseRes.fail(ResponseEnum.ContentTypeError.status(), ResponseEnum.ContentTypeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            if (image == null) {
                res = BaseRes.fail(ResponseEnum.FileSizeError.status(), ResponseEnum.FileSizeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            byte[] content = image.getBytes();
            //validate media type
            if (!Utils.isAllowedMediaType(content)) {
                res = BaseRes.fail(ResponseEnum.FileTypeError.status(), ResponseEnum.FileTypeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            // 图像不能超过2M
            if (image.getSize() > MAX_AVATAR_SIZE) {
                res = BaseRes.fail(ResponseEnum.FileSizeError.status(), ResponseEnum.FileSizeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            // validate extension
            if (!ArrayUtils.contains(ALLOWED_EXTENSIONS, StringUtils.split(image.getOriginalFilename(), '.')[1])) {
                res = BaseRes.fail(ResponseEnum.FileTypeError.status(), ResponseEnum.FileTypeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            //落地
            // Avatar avatar = avatarService.upload(ClubAuthInterceptor.getCurrentUser().getUserId(), image.getBytes(), image.getOriginalFilename());
            //不落地
            Avatar avatar = avatarService.upload(ClubAuthInterceptor.getCurrentUser().getUserId(), content);
            res = BaseRes.ok(ResponseEnum.OK.status(), ResponseEnum.OK.message(), avatar);
            log.info("response: {}", res);
        } catch (AppException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (SysException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(ResponseEnum.SystemError.status(), ResponseEnum.SystemError.message(),
                    new Avatar(null, e.getMessage(), null));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private void isDisableApi() {
        Boolean isDisableModify = CacheManager.getInstance().get(KeyStruct.getDbKeyInstance("hjapi:disabledapi:modify"), Boolean.class);
        if (isDisableModify != null && isDisableModify) {
            throw new AppException(ResponseEnum.ApiClosed.status(), ResponseEnum.ApiClosed.message());
        }
    }

    /**
     * @param image
     * @return
     */
    @ApiOperation(value = "上传头像", httpMethod = "POST", response = ResponseEntity.class)
    @RequestMapping(value = "/avatar/upload", method = RequestMethod.POST)
    public ResponseEntity<BaseRes<?>> uploadAvatar(@RequestParam("file") MultipartFile image, Integer userid) throws IOException{
        BaseRes<?> res = null;
        this.isDisableApi();
        InputStream input = null;
        try {
            if (userid == null || userid <= 0) {
                res = BaseRes.ok(HttpStatus.BAD_REQUEST.value(), "invalid uid or userid");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            if (image == null) {
                res = BaseRes.fail(HttpStatus.BAD_REQUEST.value(), "file is empty");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            // 图像不能超过2M
            if (image.getSize() > MAX_AVATAR_SIZE) {
                res = BaseRes.fail(HttpStatus.PAYLOAD_TOO_LARGE.value(), "file length is greater than 2MB");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            byte[] content = image.getBytes();
            //validate media type
            if (!Utils.isAllowedMediaType(content)) {
                res = BaseRes.fail(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "unsupported media type");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            Avatar avatar = avatarService.upload(userid, content);
            res = BaseRes.ok(ResponseEnum.OK.status(), ResponseEnum.OK.message(), avatar != null ? avatar.getAvatar() : "");
            log.info("response: {}", res);
        } catch (AppException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (SysException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * @param image
     * @return
     */
    @ApiOperation(value = "上传头像", httpMethod = "POST", response = ResponseEntity.class)
    @RequestMapping(value = "/avatar/token/upload", method = RequestMethod.POST)
    public ResponseEntity<BaseRes<?>> uploadAvatar(@RequestParam("file") MultipartFile image, Integer uid, String once, String token) {
        BaseRes<?> res;
        this.isDisableApi();
        try {
            if (uid == null || uid <= 0) {
                res = BaseRes.fail(HttpStatus.BAD_REQUEST.value(), "invalid uid", null);
                log.info("invalid uid");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            if (StringUtils.isBlank(once)) {
                res = BaseRes.fail(HttpStatus.BAD_REQUEST.value(), "invalid once", null);
                log.info("invalid once");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            if (StringUtils.isBlank(token)) {
                res = BaseRes.fail(HttpStatus.BAD_REQUEST.value(), "invalid token", null);
                log.info("invalid token");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            if (image == null) {
                res = BaseRes.fail(HttpStatus.BAD_REQUEST.value(), "file is empty");
                log.info("file is empty");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            // 图像不能超过2M
            if (image.getSize() > MAX_AVATAR_SIZE) {
                res = BaseRes.fail(HttpStatus.PAYLOAD_TOO_LARGE.value(), "file length is greater than 2MB");
                log.info("file length is greater than 2MB, file size: {}", image.getSize());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            //验证token
            String currToken = SecurityHelper.md5(String.format("filelength=%s&once=%s&uid=%s%s", image.getSize(), once, uid, "www.hujiang.com@2016"));
            log.info("origin token: {}, current token: {}, equals: {}, file size: {}", token, currToken, Objects.equals(token, currToken), image.getSize());
            if (!Objects.equals(token, currToken)) {
                res = BaseRes.fail(HttpStatus.FORBIDDEN.value(), "access denied");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            byte[] content = image.getBytes();
            //validate media type
            if (!Utils.isAllowedMediaType(content)) {
                res = BaseRes.fail(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "unsupported media type");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            Avatar avatar = avatarService.upload(uid, content);
            res = BaseRes.ok(ResponseEnum.OK.status(), ResponseEnum.OK.message(), avatar != null ? avatar.getAvatar() : "");
            log.info("response: {}", res);
        } catch (AppException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (SysException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
