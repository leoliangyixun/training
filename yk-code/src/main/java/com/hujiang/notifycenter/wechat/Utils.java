/**
 * 
 */
package com.hujiang.notifycenter.wechat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.spi.ErrorCode;

/**
 * @author yangkai
 *
 */
public class Utils {
    /**
     * 将data装换成json格式
     * @return
     */
    public String dataFormat() {
        try {
            if (StringUtils.isNotBlank(data)) {
                List<String> elems = new ArrayList<>();
                Arrays.asList(StringUtils.split(data, ",")).stream().forEach(content -> {
                    StringBuilder sb = new StringBuilder();
                    //like this --> first:有小伙伴回复了你|||#000000
                    String[] text = StringUtils.split(content, ":");
                    if (text.length == 0) {
                        text = new String[]{"", ""};
                    } else if (text.length == 1) {
                        text = new String[]{text[0], ""};
                    }

                    sb.append("\"").append(text[0]).append("\"").append(":");
                    //like this --> 有小伙伴回复了你|||#000000
                    if (text[1].contains("|||")) {
                        //1.有小伙伴回复了你|||#000000
                        //2.|||
                        //3. |||
                        //4.有小伙伴回复了你|||
                        //5.|||#000000
                        String[] pairs = StringUtils.split(text[1], "|||");
                        //兼容逻辑，防止出现异常（如："keyword3:|||,keyword4:|||" 会出现异常）
                        if (pairs.length == 0) {
                            pairs = new String[]{"", ""};
                        } else if (pairs.length == 1) {
                            if (pairs[0].contains("#")) {
                                pairs = new String[]{"", pairs[0]};
                            } else {
                                pairs = new String[]{pairs[0], ""};
                            }
                        }

                        sb.append("{");
                        sb.append("\"value\"").append(":").append("\"").append(pairs[0].replaceAll("&colon;", ":").replaceAll("&comma;", ",")).append("\"").append(",");
                        sb.append("\"color\"").append(":").append("\"").append(pairs[1]).append("\"");
                        sb.append("}");
                    } else {
                        sb.append("{");
                        sb.append("\"value\"").append(":").append("\"").append(text[1]).append("\"").append(",");
                        sb.append("\"color\"").append(":").append("\"\"");
                        sb.append("}");
                    }

                    elems.add(sb.toString());
                });

                return StringUtils.join(elems, ",");
            }
        } catch (Exception e) {
            log.error(String.format("invalid template data: %s, error stack: ", data), e);
            throw new AppException(ErrorCode.invalid_args.errorCode(), ErrorCode.invalid_args.errorMsg());
        }

        return null;
    }

}
