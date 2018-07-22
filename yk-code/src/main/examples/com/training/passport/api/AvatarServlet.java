/**
 * 
 */
package com.training.passport.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.training.passport.account.AvatarService;

import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.pass.service.account.AvatarService;
import com.hujiang.pass.support.common.SecurityHelper;
import com.hujiang.pass.support.common.Utils;
import com.hujiang.pass.support.constants.ResponseEnum;
import com.hujiang.pass.support.constants.SysConst;
import com.hujiang.pass.support.model.dto.account.Avatar;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangkai
 *
 */
@Slf4j
@WebServlet(urlPatterns = {"/account/api/v2/avatar/token/upload"}, description = "app avatar upload")
@Deprecated
public class AvatarServlet extends HttpServlet {

    private static final long serialVersionUID = 8620651340189251459L;

    @Autowired
    private AvatarService avatarService;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try {
            String uidStr = request.getParameter("uid");
            String once = request.getParameter("once");
            String token = request.getParameter("token");

            Integer uid = StringUtils.isNotBlank(uidStr) ? Integer.valueOf(uidStr) : null;
            log.info("AppAvatarServlet --> uid: {}, once: {}, token: {}", uid, once, token);

            if (uid == null || uid <= 0) {
                write(writer, HttpStatus.BAD_REQUEST.value(), "invalid uid", null);
                return;

            }

            if (StringUtils.isBlank(once)) {
                write(writer, HttpStatus.BAD_REQUEST.value(), "invalid once", null);
                return;
            }

            if (StringUtils.isBlank(token)) {
                write(writer, HttpStatus.BAD_REQUEST.value(), "invalid token", null);
                return;
            }

            FileItem item = null;
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");

            List<FileItem> items = upload.parseRequest(request);

            if (CollectionUtils.isEmpty(items)) {
                write(writer, HttpStatus.BAD_REQUEST.value(), "file is empty", null);
                return;
            }

            item = items.get(0);
            byte[] content = IOUtils.toByteArray(item.getInputStream());

            // 图像不能超过2M
            if (content.length > 1024 * 1024 * 2) {
                write(writer, HttpStatus.BAD_REQUEST.value(), "file length is greater than 2MB", null);
                return;
            }

            //验证token
            String gToken = SecurityHelper.md5(String.format("filelength=%s&once=%s&uid=%s%s", content.length, once, uid, "www.hujiang.com@2016"));
            if (!Objects.equals(token, gToken)) {
                write(writer, HttpStatus.BAD_REQUEST.value(), "access denied", null);
                return;
            }

            //validate media type
            if (!Utils.isAllowedMediaType(content)) {
                write(writer, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "unsupported media type", null);
                return;
            }

            Avatar avatar = avatarService.upload(uid, content);
            write(writer, ResponseEnum.OK.status(), ResponseEnum.OK.message(),  avatar != null ? avatar.getAvatar() : "");
        } catch (AppException e) {
            log.error(SysConst.ERROR_STACK, e);
            write(writer, e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (SysException e) {
            log.error(SysConst.ERROR_STACK, e);
            write(writer, e.getCode(), e.getMessage(), e.getReturnObj());
        } catch (Exception e) {
            log.error(SysConst.ERROR_STACK, e);
            write(writer, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null);
        }
    }

    private void write(Writer writer, int status, String message, Object data) throws IOException {
        String result = JSONObject.toJSONString(
                new JSONObject()
                        .fluentPut("code", status)
                        .fluentPut("status", status)
                        .fluentPut("message", message)
                        .fluentPut("data", data), SerializerFeature.WriteMapNullValue);
        writer.write(result);
        writer.flush();
        log.info("AvatarServlet process result: {}", result);
    }
}
