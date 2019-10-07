package UserMessage;

/**
 * 用户个人信息类（用于存放用户的个人信息）
 * @author lenovo
 *
 */
public class User {
	private String uname;  //用户名
	
	private String password;  //密码
	
	private String phone;  //手机号
	
	private int phoneCode;  //手机号验证码
	
	private String emailCode;  //邮箱验证码
	
	private String email;  //获取邮箱
	
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
