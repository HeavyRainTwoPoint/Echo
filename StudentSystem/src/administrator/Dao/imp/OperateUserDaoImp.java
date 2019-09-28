package administrator.Dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import administrator.Dao.OperateUserDao;
import myDatabaseUtil.LoginDatabase;
import myEntity.User;

/**
 * 
 *  实现管理员删、改、查、功能的Dao层实现类
 *
 */
public class OperateUserDaoImp implements OperateUserDao {

	//自定义的连接数据库工具类
	LoginDatabase databaseUtil = new LoginDatabase();
	//声明一个PreparedStatement对象
	PreparedStatement preparedStatement = null;
	@Override     //删除方法   name：待删除的成员
	public int deleteUser(String name) {
		try
		{
			
			String sql = "delete from student where name = ?";
//			此处获取connection并进行SQL的预编译
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			给占位符赋值
			preparedStatement.setString(1, name);
//			执行删除，并获取删除结果
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
			System.out.println("管理员删除数据失败！！！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("deleteUser方法异常！！！");
		}
		return 0;
	}

	@Override		//   查询全部成员方法，并按照年龄大小排序
	public List<User> getUserlist() {
		ResultSet resultSet = null;
		List<User> userlist = new ArrayList<User>();
		try
		{
			String sql = "select * from student order by age";
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			执行查询
			resultSet = preparedStatement.executeQuery();
//			对数据进行封装
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
				userlist.add(user);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("管理员查询数据库数据异常！！！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getAllUser方法异常！！！");
		}
		return userlist;
	}

	@Override    //更改用户信息方法
	public int updateUser(User user) {
		try
		{
//			进行更新成员信息操作的SQL语句
			String sql = "update student set sex=?,age=?,profession=?,"+ 
				"phonenumber=?,hobby=?, password=? where name=?";
//			预编译
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			对占位符进行赋值
			preparedStatement.setString(1,user.getSex());
			preparedStatement.setInt(2,(int)user.getAge());
			preparedStatement.setString(3,user.getProfession());
			preparedStatement.setString(4,user.getPhonenumber());
			preparedStatement.setString(5,user.getHobby());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getName());
//			执行更新，并返回一个结果
			int update_result = preparedStatement.executeUpdate();
			if(update_result == 1)
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
			System.out.println("管理员修改数据异常！！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("UpdaetUser类异常！！！");
		}
		return 0;
	}

	@Override	//  上传照片的方法      name：需要上传头像的成员     imageName：图片的文件名
	public int uploadUserImage(String name, String imageName) {
		try
		{
//			进行更新成员信息操作的SQL语句
			String sql = "update student set imagename=? where name=?";
//			预编译
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			对占位符进行赋值
			preparedStatement.setString(1,imageName);
			preparedStatement.setString(2, name);
//			执行上传，并返回一个结果
			int upload_result = preparedStatement.executeUpdate();
			if(upload_result == 1)
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
			System.out.println("管理员修改数据异常！！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("UpdaetUser类异常！！！");
		}
		return 0;
	}
//	按条件查询数据方法
	@Override
	public List<User> getUserByLimit(User user) {
		List<User> userlist = new ArrayList<User>();
		StringBuffer sql = new StringBuffer("select * from student where 1=1 ");
		if(user.getName()!=null&&user.getName() != "")
		{
			sql.append("and name like '%"+user.getName()+"%' ");
		}
		if(user.getAge() != 0)
		{
			sql.append("and age="+user.getAge()+" ");
		}
		if(user.getSex()!=null&&user.getSex() != "")
		{
			sql.append("and sex='"+user.getSex()+"' ");
		}
		if(user.getProfession() != null && user.getProfession() !="" )
		{
			sql.append("and profession like '%"+user.getProfession()+"%' ");
		}
		if(user.getPhonenumber() != null && user.getPhonenumber() !="" )
		{
			sql.append("and phonenumber like '%"+user.getPhonenumber()+"%' ");
		}
		if(user.getHobby() != null && user.getHobby()!="" )
		{
			sql.append("and hobby like '%"+user.getHobby()+"%' ");
		}
		if(user.getPassword() != null && user.getPassword() !="" )
		{
			sql.append("and password='"+user.getPassword()+"'");
		}
		String SQL = sql.toString();
		try
		{
			preparedStatement = databaseUtil.getConnection().prepareStatement(SQL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				User u = new User();
				u.setName(resultSet.getString("name"));
				u.setAge(resultSet.getInt("age"));
				u.setSex(resultSet.getString("sex"));
				u.setProfession(resultSet.getString("profession"));
				u.setPhonenumber(resultSet.getString("phonenumber"));
				u.setHobby(resultSet.getString("hobby"));
				u.setPassword(resultSet.getString("password"));
				userlist.add(u);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("按条件查询方法异常");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getUserByLimit方法异常");
		}
		return userlist;
		
	}

//	管理员添加新成员的方法
	@Override
	public int addUser(User user) {
		try
		{
			String sql = "insert into student(name,sex,age,profession,phonenumber,hobby,password,imageName"
					+ ") values(?,?,?,?,?,?,?,?)";
//			预编译sql语句，并给占位符赋值
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSex());
			preparedStatement.setInt(3, user.getAge());
			preparedStatement.setString(4, user.getProfession());
			preparedStatement.setString(5, user.getPhonenumber());
			preparedStatement.setString(6, user.getHobby());
			preparedStatement.setString(7, user.getPassword());
			preparedStatement.setString(8, user.getImagename());
//			获取添加结果
			int add_result = preparedStatement.executeUpdate();
			if(add_result == 1)
			{
				return 1 ;
			}
			else
			{
				return 0;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.print("管理员添加新成员失败!!！");
			return -1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("addUser方法异常！！！");
			return -1;
		}
		
	}

}
