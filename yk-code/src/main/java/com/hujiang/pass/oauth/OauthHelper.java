/**
 * 
 */
package com.hujiang.pass.support.common.oauth;

import lombok.Getter;

/**
 * @author yangkai
 *
 */
public class OauthHelper {

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {

	/*	@Getter
		protected String state;
		@Getter
		protected String display;
		@Getter
		protected String parameter;
		@Getter
		protected String configKey;*/
		@Getter
		protected OauthType oauthType;
		@Getter
		protected String callbackUrl;
/*		@Getter
		protected String redirectUrl;
		@Getter
		protected String currentUrl;*/


/*		public Builder state(String state) {
			this.state = state;
			return this;
		}

		public Builder display(String display) {
			this.display = display;
			return this;
		}

		public Builder parameter(String parameter) {
			this.parameter = parameter;
			return this;
		}

		public Builder configKey(String configKey) {
			this.configKey = configKey;
			return this;
		}*/

		public Builder oauthType(OauthType oauthType) {
			this.oauthType = oauthType;
			return this;
		}

		public Builder callbackUrl(String callbackUrl) {
			this.callbackUrl = callbackUrl;
			return this;
		}

		public OauthBase build() {
			return this.oauthType.getOauthBusiness(this);
		}
	}
}
