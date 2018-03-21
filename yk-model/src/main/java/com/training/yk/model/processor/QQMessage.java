/**
 * 
 */
package com.training.yk.model.processor;

/**
 * @author yangkai
 *
 */

public class QQMessage extends Message {
    public QQMessageBody body;

    
    public QQMessage() {
       super();
    }
    

    public static class QQMessageBody {
        
        private String touser;
        private String url;        
        private String data;
        
        public String getTouser() {
            return touser;
        }
        public void setTouser(String touser) {
            this.touser = touser;
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


    public QQMessageBody getBody() {
        return body;
    }

    public void setBody(QQMessageBody body) {
        this.body = body;
    }
    
    
    

}
