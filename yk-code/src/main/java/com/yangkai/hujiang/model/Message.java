/**
 * 
 */
package com.yangkai.hujiang.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yk.processor2.MessageProcessor;

import lombok.Data;

/**
 * @author yangkai
 *
 */
@Data
public class Message {

	private String initiator;
	private Integer initiatorID;
    private List<Integer> receiverIDs;
    private List<String> receivers;
    private String token;
    private String topic;
    private MessageBody body;
    private SendType sendType;
    
    private MessageProcessor<Message> messageProcessor;
    
	public Message() {
		
	}
    
    @Data
    public static class MessageBody {

        private String touser;
        private String template_id;
        private String url;        
        private String data;

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

        public String getTemplate_id() {
            return template_id;
        }

        public void setTemplate_id(String template_id) {
            this.template_id = StringUtils.isNoneBlank(template_id) ? template_id.trim() : template_id;
        }

        public MessageBody() {
    		
    	}
        
    }

    public  enum SendType {
        WX,QQ
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
