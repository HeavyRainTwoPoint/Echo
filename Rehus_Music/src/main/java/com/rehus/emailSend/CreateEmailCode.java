package com.rehus.emailSend;

import java.util.UUID;

/**
 * ����������,ʹ��UUID�����������һ��������
 * @author lenovo
 *
 */

public class CreateEmailCode {
	public static String create() {
		String code = UUID.randomUUID().toString().replaceAll("-", "");
		return code;
	}
}