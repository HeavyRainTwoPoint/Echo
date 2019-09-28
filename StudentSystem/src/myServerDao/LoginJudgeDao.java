package myServerDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import myDatabaseUtil.LoginDatabase;
import myEntity.User;

import java.sql.PreparedStatement;
/**
 * 
 * 登录判断类：区分管理员和普通成员
 *
 */
public class LoginJudgeDao {
//	连接数据库获取验证信息    user：封装着用户名和密码  flag：身份标识，true为管理员/false为普通成员
	public int login(User user,boolean flag)
	{
		PreparedStatement preparedStatement;
		LoginDatabase databaseUtil = new LoginDatabase();
		ResultSet resultSet;
		String name;
		String password;
		if(!flag) 
		{
			try
			{
				String sql = "select name,password from student";
				preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					name = resultSet.getString("name");
					password = resultSet.getString("password");
					if(name.equals(user.getName())&&password.equals(user.getPassword())) 
					{
//						成功返回1
						return 1;
					}
				}
//				失败返回0
				return 0;
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
//				异常返回-1
				return -1;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return -1;
			}
		}
		else
		{
			try
			{
				String sql = "select * from administrator";
				preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					name = resultSet.getString("name");
					password = resultSet.getString("password");
					if(name.equals(user.getName())&&password.equals(user.getPassword())) 
					{
						return 1;
					}
				}
				return 0;
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
				return -1;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return -1;
			}
		}
		
	}
}
