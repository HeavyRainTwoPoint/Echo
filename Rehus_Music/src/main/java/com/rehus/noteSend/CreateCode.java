package com.rehus.noteSend;

import java.util.Random;

/**
 * Ϊ�ֻ�����֤�����������������λ�������
 * @author lenovo
 *
 */

public class CreateCode {
	/**
	 * 
	 * @return  ����һ�����ɵ���֤��
	 */
	public static int create() {
		Random random = new Random();
		int size = 0;  //��ǰѭ���Ĵ���
		int code = 0;  //��֤��
		while(size<6) {
			int number = abs(random.nextInt()%9);
			while(number==0) {  //��ǰ���ֲ���Ϊ�㣬��������´���
				number = abs(random.nextInt()%9);
			}
			code = code*10+number;
			size++;
		}
		return code;
	}
	/**
	 * ��������Ϊ����
	 * @param number  ����һ������
	 * @return  �����ݱ�Ϊ���������ȥ
	 */
	private static int abs(int number) {
		if(number<0)
			number = -number;
		return number;
	}
}
