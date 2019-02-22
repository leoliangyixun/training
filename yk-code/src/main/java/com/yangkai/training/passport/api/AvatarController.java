package com.training.passport.api;

import com.google.common.collect.ImmutableMap;
import com.training.passport.account.AvatarService;

import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.basic.framework.core.util.DateUtil;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.pass.integration.annotation.LoginRequired;
import com.hujiang.pass.integration.interceptors.ClubAuthInterceptor;
import com.hujiang.pass.service.account.AvatarService;
import com.hujiang.pass.support.common.SecurityHelper;
import com.hujiang.pass.support.common.Utils;
import com.hujiang.pass.support.constants.ResponseEnum;
import com.hujiang.pass.support.constants.SysConst;
import com.hujiang.pass.support.model.bo.CurrentUser;
import com.hujiang.pass.support.model.dto.account.Avatar;
import com.hujiang.pass.support.model.dto.account.CropInfo;
import com.hujiang.pass.support.model.dto.response.BaseRes;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.regex.Pattern;

import static com.hujiang.pass.support.constants.SysConst.ALLOWED_EXTENSIONS;
import static com.hujiang.pass.support.constants.SysConst.AVATAR_UPLOAD_PATH;

/**
 * Created by yangkai on 2017/6/16.
 */
@Slf4j
@RestController
@RequestMapping("/account/api")
public class AvatarController extends BaseController {

    @Autowired
    private AvatarService avatarService;

    /**
     * @param image
     * @return
     */
    @ApiOperation(value = "上传头像", httpMethod = "POST", response = ResponseEntity.class)
    @LoginRequired
    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public ResponseEntity<BaseRes<?>> uploadAvatar(@RequestParam("file") MultipartFile image) {
        BaseRes<?> res;
        try {
            Avatar avatar = avatarService.upload(ClubAuthInterceptor.getCurrentUser(), image.getBytes(), image.getOriginalFilename());
            res = BaseRes.ok(ResponseEnum.OK.status(), ResponseEnum.OK.message(), avatar);
        } catch (AppException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (SysException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            throw new SysException(ResponseEnum.SystemError.status(), ResponseEnum.SystemError.message(),
                    new Avatar(null, e.getMessage(), null));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    /**
     * 头像上传第一步
     * @param image
     * @param request
     * @param response
     * @return
     */
    @Deprecated
    @ApiOperation(value = "头像上传第一步", httpMethod = "POST", response = ResponseEntity.class)
    @LoginRequired
    @RequestMapping(value = "/uploadavatar", method = RequestMethod.POST)
    public ResponseEntity<BaseRes<?>> upload(@RequestParam("file") MultipartFile image, HttpServletRequest request, HttpServletResponse response) {
        BaseRes<?> res;
        try {
            String contentType = request.getContentType();

            if (!ContentType.MULTIPART_FORM_DATA.getMimeType().equals(StringUtils.split(contentType, ";")[0])) {
                res = BaseRes.fail(ResponseEnum.ContentTypeError.status(), ResponseEnum.ContentTypeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            if (image == null || StringUtils.isBlank(image.getOriginalFilename())) {
                res = BaseRes.fail(ResponseEnum.FileSizeError.status(), ResponseEnum.FileSizeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            //validate media type
            if (!Utils.isAllowedMediaType(image.getInputStream())) {
                res = BaseRes.fail(ResponseEnum.FileTypeError.status(), ResponseEnum.FileTypeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            if (image.getSize() > 1024 * 1024 * 2) {
                res = BaseRes.fail(ResponseEnum.FileSizeError.status(), ResponseEnum.FileSizeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            // validate extension
            if (!ArrayUtils.contains(ALLOWED_EXTENSIONS, StringUtils.split(image.getOriginalFilename(), '.')[1])) {
                res = BaseRes.fail(ResponseEnum.FileTypeError.status(), ResponseEnum.FileTypeError.message(), ImmutableMap.of());
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

            String imageName = upload2TempDir(ClubAuthInterceptor.getCurrentUser(), image);
            // fileName ---> DES Encrypt ---> uploadToken
            // uploadToken ---> DES Decrypt ---> fileName
            res = BaseRes.fail(ResponseEnum.OK.status(), ResponseEnum.OK.message(),
                    ImmutableMap.of(
                            "uploadToken", SecurityHelper.desEncrypt(imageName),
                            "pic", StringUtils.isNotBlank(imageName)
                                    ? String.format("%s/temp/%s", request.getServletPath().split(File.separator)[1], imageName)
                                    : ""));
        } catch (AppException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(),e.getReturnObj());
        } catch (SysException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(),e.getReturnObj());
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(ResponseEnum.FileSizeError.status(), ResponseEnum.FileSizeError.message(), ImmutableMap.of());
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 支持JSONP
     * @deprecated java 版本后续不再支持jsonp
     * @param cropInfo
     * @param callback
     * @param response
     * @return
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    @ApiOperation(value = "上传头像第二步", httpMethod = "GET", response = ResponseEntity.class)
    @LoginRequired
    @RequestMapping(value = "/changeavatar", method = RequestMethod.GET)
    public String upload(CropInfo cropInfo, @RequestParam(defaultValue = "") String callback, HttpServletResponse response) {
        if (validateCallback(callback, false)) {
            throw new AppException(ResponseEnum.SourceError.status(), ResponseEnum.SourceError.message());
        }

        try {
            // fileName ---> DES Encrypt ---> uploadToken
            // uploadToken ---> DES Decrypt ---> fileName
            File image = new File(AVATAR_UPLOAD_PATH + SecurityHelper.desDecrypt(cropInfo.getUploadToken()));
            Avatar avatar = avatarService.cropAndUpload(ClubAuthInterceptor.getCurrentUser(), cropInfo, image);
            return this.responseJsonp(BaseRes.ok(ResponseEnum.OK.status(), ResponseEnum.OK.message(), avatar),
                    callback, response);
        } catch (AppException e) {
            log.error(SysConst.ERROR_STACK, e);
            return this.responseJsonp(BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj()),
                    callback, response);
        } catch (SysException e) {
            log.error(SysConst.ERROR_STACK, e);
            return this.responseJsonp(BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj()),
                    callback, response);
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            return this.responseJsonp(BaseRes.fail(ResponseEnum.SystemError.status(), ResponseEnum.SystemError.message(),
                    new Avatar(null, e.getMessage(), null)),
                    callback, response);
        }
    }

    /**
     * 非JSONP
     * @param cropInfo
     * @return
     */
    @ApiOperation(value = "头像上传第二步", httpMethod = "POST", response = ResponseEntity.class)
    @LoginRequired
    @RequestMapping(value = "/changeavatar", method = RequestMethod.POST)
    public ResponseEntity<BaseRes<?>>  uploadAvatar(@RequestBody CropInfo cropInfo) {
        BaseRes<?> res;
        try {
            // fileName ---> DES Encrypt ---> uploadToken
            // uploadToken ---> DES Decrypt ---> fileName
            File image = new File(AVATAR_UPLOAD_PATH + SecurityHelper.desDecrypt(cropInfo.getUploadToken()));
            Avatar avatar = avatarService.cropAndUpload(ClubAuthInterceptor.getCurrentUser(), cropInfo, image);
            res = BaseRes.ok(ResponseEnum.OK.status(), ResponseEnum.OK.message(), avatar);
        } catch (AppException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (SysException e) {
            log.error(SysConst.ERROR_STACK, e);
            res = BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            throw new SysException(ResponseEnum.SystemError.status(), ResponseEnum.SystemError.message(),
                    new Avatar(null, e.getMessage(), JsonUtil.object2JSON(cropInfo)));
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * 临时保存上传的图像到本地
     * @param currentUser
     * @param image
     * @return
     */
    @Deprecated
    private String upload2TempDir(CurrentUser currentUser, MultipartFile image) {
        try {
            String timestamp = DateUtil.toDateString(new Date(), DateUtil.YYYYMMDD_DATE_PATTERN);
            String imageName = new StringBuilder("")
                    .append(String.valueOf(currentUser.getUserId()))
                    .append("_").append(SecurityHelper.md5(timestamp))
                    .append(".")
                    .append(StringUtils.split(image.getOriginalFilename(), '.')[1].toLowerCase()).toString();

            File uploadDir = new File(AVATAR_UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File file = new File(AVATAR_UPLOAD_PATH + imageName);

            //存在的话，删除
            if (file.exists()) {
                file.delete();
            }

            image.transferTo(file);

            return imageName;
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            throw new AppException(
                    ResponseEnum.SystemError.status(),
                    ResponseEnum.SystemError.message(),
                    ImmutableMap.of("uploadToken", SecurityHelper.desEncrypt(""), "pic", "")
            );

        }
    }



    // ----------------------------------deprecated-----------------------------------
    /**
     * @deprecated deprecated
     * @param res
     * @param callback
     * @param response
     * @return
     */
    @Deprecated
    private String responseJsonpScript(BaseRes<?> res, String callback, HttpServletResponse response) {
        response.setContentType("text/html");
        return "<script type='text/javascript'>" + callback + "(" + JsonUtil.object2JSON(res) + ");</script>";
    }

    /**
     * @deprecated deprecated
     * @param res
     * @param callback
     * @param response
     * @return
     */
    @Deprecated
    private String responseJsonp(BaseRes<?> res, String callback, HttpServletResponse response) {
        response.setContentType("text/html");
        return StringUtils.isBlank(callback) ? JsonUtil.object2JSON(res) : callback + "(" + JsonUtil.object2JSON(res) + ")";
    }

    /**
     * jsonp callback
     *
     * @deprecated no longer support jsonp validation after transform to Java
     * @param callback
     * @param isIncludeScript
     * @return
     */
    @Deprecated
    protected boolean validateCallback(String callback, boolean isIncludeScript) {
        return StringUtils.isBlank(callback) ? true
                : isIncludeScript
                ? Pattern
                .compile("^(document\\.domain='(hujiang|yeshj|hjclass|hjenglish|cctalk|hjact)\\.com';)?top\\.(\\w{1,32})$")
                .matcher(callback).matches()
                : Pattern.compile("^(\\w{1,50})$").matcher(callback).matches();
    }

}
