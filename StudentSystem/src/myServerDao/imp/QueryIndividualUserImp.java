package myServerDao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myDatabaseUtil.LoginDatabase;
import myEntity.User;
import myServerDao.QueryIndividualUserDao;
/**
 * 单个用户查询类Dao层接口的实现类
 */
public class QueryIndividualUserImp implements QueryIndividualUserDao
{

//	获取详细个人信息  name：用户名，作为查询索引
	@Override
	public User getUser(String name) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
//		自定义的数据库连接工具类
		LoginDatabase databaseUtil = new LoginDatabase();
		User user = new User();
		try
		{
			String sql = "select * from student where name=?";
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, name);
//			执行查询
			resultSet = preparedStatement.executeQuery();
//			封装数据
			while(resultSet.next())
			{
				user.setName(resultSet.getString("name"));
				user.setSex(resultSet.getString("sex"));
				user.setAge(resultSet.getInt("age"));
				user.setProfession(resultSet.getString("profession"));
				user.setHobby(resultSet.getString("hobby"));
				user.setPhonenumber(resultSet.getString("phonenumber"));
				user.setPassword(resultSet.getString("password"));
				user.setImagename(resultSet.getString("imagename"));
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("UserDaoImp数据库连接异常");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("UserDaoIml类出现异常");
		}
//		返回结果
		return user;
	}

}
