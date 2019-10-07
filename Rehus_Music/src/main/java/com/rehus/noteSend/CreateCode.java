package com.rehus.noteSend;

import java.util.Random;

/**
 * 为手机号验证生成随机数（生成六位随机数）
 * @author lenovo
 *
 */

public class CreateCode {
	/**
	 * 
	 * @return  返回一个生成的验证码
	 */
	public static int create() {
		Random random = new Random();
		int size = 0;  //当前循环的次数
		int code = 0;  //验证码
		while(size<6) {
			int number = abs(random.nextInt()%9);
			while(number==0) {  //当前数字不能为零，否则就重新创建
				number = abs(random.nextInt()%9);
			}
			code = code*10+number;
			size++;
		}
		return code;
	}
	/**
	 * 将负数变为正数
	 * @param number  传入一个数据
	 * @return  将数据变为正数传输回去
	 */
	private static int abs(int number) {
		if(number<0)
			number = -number;
		return number;
	}
}
