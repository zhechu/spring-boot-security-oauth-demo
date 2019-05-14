/**
 * 
 */
package com.wise.demo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.wise.demo.config.constants.SecurityConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 安全配置属性
 * @author lingyuwang
 *
 */
@ConfigurationProperties(prefix = SecurityConstants.APPLICATION_SECURITY_PROPERTIES_PREFIX)
@Setter
@Getter
public class SecurityProperties {
	
	/**
	 * 验证码配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();

}

