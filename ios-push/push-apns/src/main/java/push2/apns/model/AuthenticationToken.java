/**
 * 
 */
package push2.apns.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author yangkai
 *
 */
public class AuthenticationToken {
    private static class Header {
        @JSONField(name="alg")
        private String algorithm = "ES256";

        @JSONField(name="kid")
        private String keyId;

        public String getKeyId() {
            return this.keyId;
        }
    }

    private static class Payload {

        @JSONField(name="iss")
        private final String issuer;

        @JSONField(name="iat")
        private Date issuedAt;

        Payload(final String teamId, final Date issuedAt) {
            this.issuer = teamId;
            this.issuedAt = issuedAt;
        }

        public String getIssuer() {
            return this.issuer;
        }

        public Date getIssuedAt() {
            return this.issuedAt;
        }
    }
}
