package checklsNullUtil;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class CheckIsNull {
	/**
	 * 
	 * @param params   传入不定长度的字符串
	 * @return  返回是否为空true表示其中之一或者全部为空，false表示不为空
	 */
	public static boolean check(String...params) {
		for(String param:params) {
			if(param==null) {
				System.out.println("在checkIsNull类的check方法中，似乎有值为："+param);
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
