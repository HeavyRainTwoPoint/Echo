package checklsNullUtil;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class CheckIsNull {
	/**
	 * 
	 * @param params   ���벻�����ȵ��ַ���
	 * @return  �����Ƿ�Ϊ��true��ʾ����֮һ����ȫ��Ϊ�գ�false��ʾ��Ϊ��
	 */
	public static boolean check(String...params) {
		for(String param:params) {
			if(param==null) {
				System.out.println("��checkIsNull���check�����У��ƺ���ֵΪ��"+param);
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		try {
			InternetAddress address = new InternetAddress("localhost");
			System.out.println(address.getAddress());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
