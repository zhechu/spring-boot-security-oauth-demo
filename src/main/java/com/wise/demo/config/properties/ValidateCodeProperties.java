package com.wise.demo.config.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证码配置属性
 * @author lingyuwang
 *
 */
@Setter
@Getter
public class ValidateCodeProperties {
	
	/**
	 * 短信验证码配置
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();

}
