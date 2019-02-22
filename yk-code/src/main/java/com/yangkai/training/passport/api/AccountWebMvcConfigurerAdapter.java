/**
 * 
 */
package com.training.passport.api;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hujiang.pass.account.controller.servlet.SelfCheckServlet;
import com.hujiang.pass.account.controller.servlet.AvatarServlet;

/**
 * @author yangkai
 *
 */
@SuppressWarnings("deprecation")
@Configuration
public class AccountWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Bean
    public AvatarServlet avatarServlet() {
        return new AvatarServlet();
    }

    @Bean
    public ServletRegistrationBean appAvatarServletRegistration(AvatarServlet avatarServlet) {
        return new ServletRegistrationBean(avatarServlet, "/account/api/v2/avatar/token/upload");
    }
    
    @Bean
    public ServletRegistrationBean accountSelfCheckServletRegistration() {
        return new ServletRegistrationBean(new SelfCheckServlet(), "/account/do_not_delete/health_check", "/account/do_not_delete/health_check/");
    }
}
