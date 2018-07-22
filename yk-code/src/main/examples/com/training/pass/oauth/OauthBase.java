/**
 * 
 */
package com.training.pass.oauth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.training.pass.oauth.OauthHelper.Builder;

import com.hujiang.pass.support.common.oauth.OauthHelper.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author yangkai
 *
 */
@Deprecated
@Slf4j
public abstract class OauthBase {

/*	protected String state;
	protected String display;
	protected String parameter;
	protected String configKey;*/
	protected OauthType oauthType;
	protected String callbackUrl;
/*	protected String redirectUrl;
	protected String currentUrl;*/
	protected String tokenUrl;
	protected ConfigBase currentConfig;

	private static Map<String, ConfigBase> qq = Maps.newHashMap();
	private static Map<String, ConfigBase> sina = Maps.newHashMap();
	private static Map<String, ConfigBase> wx = Maps.newHashMap();

	static {

		try {
			String oauthkey = StreamUtils.copyToString(new ClassPathResource("app_data/oauthkey.json").getInputStream(), StandardCharsets.UTF_8);
			System.out.println(oauthkey);

			JSONObject jo = JSONObject.parseObject(oauthkey);
			JSONObject _qq = jo.getJSONObject("QQ");
			JSONObject _sina = jo.getJSONObject("Sina");
			JSONObject _wx = jo.getJSONObject("WX");

			_qq.forEach((k, v) -> {
				ConfigBase config = JSON.toJavaObject((JSONObject) v, ConfigBase.class);
				qq.put(k, config);

			});

			_sina.forEach((k, v) -> {
				ConfigBase config = JSON.toJavaObject((JSONObject) v, ConfigBase.class);
				sina.put(k, config);
			});

			_wx.forEach((k, v) -> {
				ConfigBase config = JSON.toJavaObject((JSONObject) v, ConfigBase.class);
				wx.put(k, config);
			});
		} catch (Exception e) {
			log.error("[error stack]: ", e);
		}
	}

	public OauthBase(OauthHelper.Builder builder) {
		this.callbackUrl = builder.getCallbackUrl();
		this.oauthType = builder.getOauthType();
/*		this.state = builder.getState();
		this.display = builder.getDisplay();
		this.parameter = builder.getParameter();
		this.configKey = builder.getConfigKey();*/
	}

	//String redirectUrl, String callbackUrl, String state, String display, String currentUrl
	public String authorizeUrl(String redirectUrl, String callbackUrl, String state, String display, String currentUrl) {
		String url = "%s?response_type=code&client_id=%s&display=%s&redirect_uri=%s&state=%s";
		this.callbackUrl = StringUtils.isBlank(currentUrl) ? callbackUrl : String.format("{0}&url={1}", callbackUrl, currentUrl);

		try {
			return String.format(url, redirectUrl, currentConfig.getAppKey(), display, URLEncoder.encode(callbackUrl, StandardCharsets.UTF_8.name()), state);
		} catch (UnsupportedEncodingException e) {
			log.error("[error stack]: ", e);
		}

		return url;
	}

	public abstract String authorizeUrl_v1(String state, String display, String currentUrl);
	public abstract String authorizeUrl_v2(String state, String display, String params, String configKey);

	/**
	 * QQ Oauth
	 */
	@Deprecated
	public static class QQBusiness extends OauthBase {

		private static final String defaultConfigKey = "PC";
		private static final String defaultTokenUrl = "https://graph.qq.com/oauth2.0/token";
		private static final String defaultRedirectUrl = "https://graph.qq.com/oauth2.0/authorize";
		private static final String defaultCallbackUrl = "https://pass.hujiang.com/qc/source=qq";

		public QQBusiness(Builder builder) {
			super(builder);
/*			this.configKey = StringUtils.isBlank(configKey) ? defaultConfigKey : configKey;
			this.redirectUrl = StringUtils.isBlank(redirectUrl) ? defaultRedirectUrl : redirectUrl;*/
			this.tokenUrl = StringUtils.isBlank(tokenUrl) ? defaultTokenUrl : tokenUrl;
			this.callbackUrl = StringUtils.isBlank(callbackUrl) ? defaultCallbackUrl : String.format("s%s%source=qq", callbackUrl, callbackUrl.lastIndexOf("?") > 0 ? "?" : "&");
			this.currentConfig = qq.get(defaultConfigKey);
		}

		@Override
		public String authorizeUrl_v1(String state, String display, String currentUrl) {
			return this.authorizeUrl("https://graph.qq.com/oauth2.0/authorize", callbackUrl, state, display, currentUrl);
		}

		/**
		 * 默认QQ登录地址v2
		 * @param state  随机uuid
		 * @param display
		 * @param params  回调地址后参数集合
		 * @param configKey
		 * @return
		 */
		@Override
		public  String authorizeUrl_v2(String state, String display, String params, String configKey) {
			this.currentConfig = StringUtils.isBlank(configKey) ? this.currentConfig : qq.get(configKey);
			this.callbackUrl = StringUtils.isNotBlank(params) ? String.format("%s&%s", callbackUrl, params) : callbackUrl;

			return this.authorizeUrl(defaultRedirectUrl, callbackUrl, state, display, "");
		}
	}

	/**
	 * Sina Oauth
	 */
	@Deprecated
	public static class SinaBusiness extends OauthBase {
		private static final String defaultConfigKey = "PC";
		private static final String defaultRedirectUrl = "https://api.weibo.com/oauth2/authorize";
		private static final String defaultTokenUrl= "https://api.weibo.com/oauth2/access_token";
		private static final String defaultCallbackUrl = "http://pass.hujiang.com/m/Oauth/CallBack.aspx?source=sina";

		public SinaBusiness(Builder builder) {
			super(builder);
/*			this.configKey = StringUtils.isBlank(configKey) ? defaultConfigKey : configKey;
			this.redirectUrl = StringUtils.isBlank(redirectUrl) ? defaultRedirectUrl : redirectUrl;*/
			this.tokenUrl = StringUtils.isBlank(tokenUrl) ? defaultTokenUrl : tokenUrl;
			this.callbackUrl = StringUtils.isBlank(callbackUrl) ? defaultCallbackUrl : String.format("s%s%source=sina", callbackUrl, callbackUrl.lastIndexOf("?") > 0 ? "?" : "&");
			this.currentConfig = sina.get(defaultConfigKey);
		}

		@Override
		public String authorizeUrl_v1(String state, String display, String currentUrl) {
			return this.authorizeUrl("https://api.weibo.com/oauth2/authorize", callbackUrl, state, display, currentUrl);
		}

		@Override
		public  String authorizeUrl_v2(String state, String display, String params, String configKey) {
			this.callbackUrl = StringUtils.isNotBlank(params) ? String.format("%s&%s",callbackUrl, params) : callbackUrl;
			return this.authorizeUrl(null, null,null,null,null);
		}

	}

	/**
	 * WeiXin Oauth
	 */
	@Deprecated
	public static class WeiXinBusiness extends OauthBase {

		public WeiXinBusiness(Builder builder) {
			super(builder);
		}

		@Override
		public String authorizeUrl_v1(String state, String display, String currentUrl) {
			return null;
		}

		@Override
		public String authorizeUrl_v2(String state, String display, String params, String configKey) {
			String url = null;
			if (configKey.startsWith("Wechat")) {
				url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_userinfo&state={2}#wechat_redirect";

			} else if (configKey.startsWith("PC")) {
				url = "https://open.weixin.qq.com/connect/qrconnect?appid={0}&redirect_uri={1}&response_type=code&state={2}&scope=snsapi_login";
			}


			return null;
		}
	}

}
