package com.wise.demo.validate.code;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wise.demo.validate.code.sms.DefaultSmsValidateCodeSender;
import com.wise.demo.validate.code.sms.SmsValidateCodeSender;

/**
 * 验证码相关的扩展点配置。配置在这里的 bean，业务系统都可以通过声明同类型或同名的 bean 来覆盖安全模块默认的配置。
 * @author lingyuwang
 *
 */
@Configuration
public class ValidateCodeExtendConfig {
	
	/**
	 * 短信验证码发送器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SmsValidateCodeSender.class)
	public SmsValidateCodeSender smsCodeSender() {
		return new DefaultSmsValidateCodeSender();
	}

}
