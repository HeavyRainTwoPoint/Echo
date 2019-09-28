package myServerDao.imp;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import myDatabaseUtil.LoginDatabase;
import myEntity.User;
import myServerDao.RegisterNewUserDao;
/**
 * 
 * 注册接口的实现类
 *
 */
public class RegisterNewUserImp implements RegisterNewUserDao {

//	将注册信息保存在数据库中
	@Override
	public boolean registerNewUser(User user) {
		PreparedStatement preparedStatement = null;
		LoginDatabase databaseUtil = new LoginDatabase();
		int insert_result = -1;
		boolean flag = false;
		try
		{
			String sql = "insert into register(name,sex,age,profession,phonenumber,hobby,password"
					+ ") values(?,?,?,?,?,?,?)";
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSex());
			preparedStatement.setInt(3, (int)user.getAge());
			preparedStatement.setString(4, user.getProfession());
			preparedStatement.setString(5, user.getPhonenumber());
			preparedStatement.setString(6, user.getHobby());
			preparedStatement.setString(7, user.getPassword());
			insert_result = preparedStatement.executeUpdate();
			if(insert_result == 1)
			{
//				保存成功返回true
				flag = true;
			}
			else
			{
//				失败返回false
				flag = false;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("将数据插入数据库失败！！！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("RegisterNewUserDaoImp类异常！！！");
		}
//		将结果返回
		return flag;
	}

}
