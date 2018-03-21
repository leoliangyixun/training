/**
 * 
 */
package com.training.yk.model.processor;

/**
 * @author yangkai
 *
 */

public class WechatMessage extends Message {
    public WechatMessageBody body;

    
    public WechatMessage() {
       super();
    }
    

    public static class WechatMessageBody {
        
        private String touser;
        private String template_id;
        private String url;        
        private String data;
        
        public String getTouser() {
            return touser;
        }
        public void setTouser(String touser) {
            this.touser = touser;
        }
        public String getTemplate_id() {
            return template_id;
        }
        public void setTemplate_id(String template_id) {
            this.template_id = template_id;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public String getData() {
            return data;
        }
        public void setData(String data) {
            this.data = data;
        }
        
        
        
    }


    public WechatMessageBody getBody() {
        return body;
    }

    public void setBody(WechatMessageBody body) {
        this.body = body;
    }
    
    
    

}
