package com.wise.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.wise.demo.app.authentication.CustomAuthenticationSuccessHandler;

/**
 * 安全配置类
 * @author lingyuwang
 *
 */
@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
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

		http.formLogin() // 开启表单认证
			    .successHandler(customAuthenticationSuccessHandler) // 登录成功的处理器
			    .and()
		    .csrf().disable() // 禁用 CSRF 检查
		    ;
		
    }
    
}
