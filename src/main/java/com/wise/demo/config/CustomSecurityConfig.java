package com.wise.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.wise.demo.app.authentication.CustomAuthenticationFailureHandler;
import com.wise.demo.app.authentication.CustomAuthenticationSuccessHandler;
import com.wise.demo.config.constants.SecurityConstants;
import com.wise.demo.config.properties.SecurityProperties;
import com.wise.demo.validate.code.ValidateCodeFilter;

/**
 * 安全配置类
 * @author lingyuwang
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	private ValidateCodeFilter validateCodeFilter;

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

		http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class) // 验证码过滤器
		    .formLogin() // 开启表单认证
			    .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) // 未登录的处理
			    .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM) // 登录处理 url
			    .successHandler(customAuthenticationSuccessHandler) // 登录成功的处理器
			    .failureHandler(customAuthenticationFailureHandler) // 登录失败的处理器
			    .and()
		    .authorizeRequests() // 表示要进行请求授权
		    .antMatchers(
		    		SecurityConstants.DEFAULT_UNAUTHENTICATION_URL, // 未登录的处理请求 URL
		    		SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*" // 验证码请求 URL 路径
		    		).permitAll() // 表示登录页不需通过权限验证
		    .anyRequest() // 所有请求
		    .authenticated() // 需要认证后才能访问
		    .and()
		    .csrf().disable() // 禁用 CSRF 检查
		    ;
		
    }
    
}
