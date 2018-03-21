/**
 * 
 */
package com.yk.test.reflection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

/**
 * @author yangkai
 *
 */
@Data
public class Message implements Serializable {


	private static final long serialVersionUID = 1L;
	private String initiator;
	private Integer initiatorID;
    private List<Integer> receiverIDs;
    private List<String> receivers;
    private String token;
    private String topic;
    private MessageBody body;
    
	public Message() {
		
	}
    
	@Data
    public static class MessageBody implements Serializable {

		private static final long serialVersionUID = 1L;
		private String batchid;
        private String touser;
        private String template_id;
        private String url;        
        private String data;
        private Boolean isopenid;
        private Integer templateType;
        private String topcolor;
        private String wechatAppID;

        public MessageBody() {}

        public String getTouser() {
            return touser;
        }

        public void setTouser(String touser) {
            if (StringUtils.isNoneBlank(touser)) {
                List<String> temptouser = new ArrayList<>();
                new ArrayList<>(Arrays.asList(touser.trim().split(","))).forEach(id -> temptouser.add(id.trim()));
                touser = StringUtils.join(temptouser, ",");
            }
            this.touser = touser;

        }

        public void setTemplate_id(String template_id) {
            this.template_id = StringUtils.isNoneBlank(template_id) ? template_id.trim() : template_id;
        }

        public String getWechatAppID() {
            return StringUtils.isNotBlank(wechatAppID) ? wechatAppID : "xxxxxx";
        }

        public String getBatchid() {
            return StringUtils.isNotBlank(batchid) ? batchid :  String.valueOf(new Date().getTime());
        }

    }
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
