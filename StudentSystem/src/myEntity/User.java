package myEntity;
/**
 * 
 * User ���ڷ�װ�û���Ϣ
 *
 */
public class User {
//	����
	private String name;
//	�Ա�
	private String sex;
//	����
	private int age;
//	רҵ
	private String profession;
//	����
	private String hobby;
//	��ϵ�绰
	private String phonenumber;
//	��¼����
	private String password;
//	ͷ����
	private String imagename;
	public User() {}
	public User(String name)
	{
		this.name = name;
	}
	public User(String name,String password)
	{
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String sex, int age, String profession, String hobby, String phonenumber,
			String password) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.profession = profession;
		this.hobby = hobby;
		this.phonenumber = phonenumber;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword()
	{
		return password;
	}
	public void setImagename(String imagename)
	{
		this.imagename = imagename;
	}
	public String getImagename()
	{
		return imagename;
	}
}
