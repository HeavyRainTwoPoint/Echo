package administrator.Dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import administrator.Dao.AuditRegisterUserDao;
import myDatabaseUtil.LoginDatabase;
import myEntity.User;

public class AuditRegisterUserImp implements AuditRegisterUserDao
{
//	自定义的连接数据库工具类
	LoginDatabase databaseUtil = new LoginDatabase();
//	声明一个PreparedStatement对象
	PreparedStatement preparedStatement = null;
	@Override   //获取待注册的注册者信息
	public List<User> getRegisterUser() 
	{
//		声明一个List容器
		List<User> userlist = new ArrayList<User>();
//		查询SQL语句
		String sql = "select * from register";
		try
		{
//			连接数据库并将SQL语句进行预编译
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			获取查询的结果集
			ResultSet resultSet = preparedStatement.executeQuery();
//			遍历结果集里面的成员，并一一封装成Javabean
			while(resultSet.next())
			{
				User user = new User();
				user.setName(resultSet.getString("name"));
				user.setSex(resultSet.getString("sex"));
				user.setAge(resultSet.getInt("age"));
				user.setProfession(resultSet.getString("profession"));
				user.setHobby(resultSet.getString("hobby"));
				user.setPhonenumber(resultSet.getString("phonenumber"));
				user.setPassword(resultSet.getString("password"));
//				将封装好的数据放在List容器里面
				userlist.add(user);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("查询注册者信息失败！~");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getRegister方法异常！！");
		}
		return userlist;
	}
//	获取单个注册者的注册信息      name：注册证姓名
	public User getUser(String name)
	{
//		查询SQL语句
		String sql = "select * from register where name=?";
//		待封装数据的Javabean
		User user = new User();
		try
		{
//			连接数据库并进行SQL语句 的预编译
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			对占位符赋值
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
//			获取结果集的信息，并进行封装
			if(resultSet.next())
			{
				user.setName(resultSet.getString("name"));
				user.setSex(resultSet.getString("sex"));
				user.setAge(resultSet.getInt("age"));
				user.setProfession(resultSet.getString("profession"));
				user.setHobby(resultSet.getString("hobby"));
				user.setPhonenumber(resultSet.getString("phonenumber"));
				user.setPassword(resultSet.getString("password"));
			}
			else
			{
				return null;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("获取该注册用户信息失败！~");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getRegisterUser方法异常！！");
		}
//		返回封装好的user对象
		return user;
	}
//	增加新成员方法   user：待增加的新成员
	public int auditRegister(User user)
	{
//		判断该用户名有没有被注册了
		boolean name_hasExist = true;
//		查询该用户名存在的个数
		String sql = "select count(*) from student where name=?";
		{
			try
			{
				preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//				对占位符赋值
				preparedStatement.setString(1, user.getName());
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next())
				{
					if(resultSet.getInt(1)>0)
					{
						System.out.println("用户已存在！！！");
						return 0;
					}
					else
					{
						name_hasExist = false;
					}
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
				System.out.println("审核用户失败！！！");
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("auditRegister方法异常！！");
			}
			
		}
//		如果不存在则将用户数据保存
		if(!name_hasExist)
		{		
//			判断增加与否的标识
			int insert_result = 0;
//			新用户不能上传头像，当通过注册时用null充当占位符
			user.setImagename("null");
//			增加的SQL语句
			sql = "insert into student values(?,?,?,?,?,?,?,?)";
			try
			{
//				连接数据库并进行SQL语句 的预编译
				preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//				对占位符赋值
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getSex());
				preparedStatement.setInt(3, user.getAge());
				preparedStatement.setString(4, user.getProfession());
				preparedStatement.setString(5, user.getHobby());
				preparedStatement.setString(6, user.getPhonenumber());
				preparedStatement.setString(7, user.getPassword());
				preparedStatement.setString(8, user.getImagename());
//				对标识进行赋值
				insert_result = preparedStatement.executeUpdate();
			}
			catch (SQLException se) {
				se.printStackTrace();
				System.out.println("审核用户失败！！！");
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("auditRegister方法异常！！");
			}
//			返回成功与否的结果
			return insert_result;
		}
		return 0;
	}
//		在待注册的成员列表中删除已经通过审核的成员  name：待删除的成员
		public int deleteRegisterUser(String name)
		{
			try
			{
//				执行删除的SQL语句
				String sql = "delete from register where name = ?";
//				连接数据库并进行SQL语句 的预编译
				preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//				对占位符赋值
				preparedStatement.setString(1, name);
//			   	执行删除
				int delete_result = preparedStatement.executeUpdate();
				if(delete_result == 1)
				{
//				成功则返回1
					return 1;
				}
				else
				{
//				失败则返回0
					return 0;
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
				System.out.println("删除注册数据失败！！！");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("deleteRegisterUser方法异常！！！");
			}
			return 0;
		}

}
