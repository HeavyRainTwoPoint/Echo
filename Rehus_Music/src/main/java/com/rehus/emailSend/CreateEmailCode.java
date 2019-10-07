package com.rehus.emailSend;

import java.util.UUID;

/**
 * 创建激活码,使用UUID类来随机创建一个激活码
 * @author lenovo
 *
 */

public class CreateEmailCode {
	public static String create() {
		String code = UUID.randomUUID().toString().replaceAll("-", "");
		return code;
	}
}