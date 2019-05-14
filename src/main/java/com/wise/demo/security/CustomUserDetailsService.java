package com.wise.demo.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义用户详情服务
 * @author lingyuwang
 *
 */
@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("登录用户名：{}", username);
		
		// 模拟获取数据库存储的密码
		String password = "123456";

		log.info("数据库存储的密码是：{}", password);
		
		return new User(
				username, 
				password, 
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin, ROLE_USER") // 以逗号分割的权限值
				);
	}

}
