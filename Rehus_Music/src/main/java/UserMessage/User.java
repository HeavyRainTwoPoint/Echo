package UserMessage;

/**
 * �û�������Ϣ�ࣨ���ڴ���û��ĸ�����Ϣ��
 * @author lenovo
 *
 */
public class User {
	private String uname;  //�û���
	
	private String password;  //����
	
	private String phone;  //�ֻ���
	
	private int phoneCode;  //�ֻ�����֤��
	
	private String emailCode;  //������֤��
	
	private String email;  //��ȡ����
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(int phoneCode) {
		this.phoneCode = phoneCode;
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
