package myServerDao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myDatabaseUtil.LoginDatabase;
import myEntity.User;
import myServerDao.QueryIndividualUserDao;
/**
 * �����û���ѯ��Dao��ӿڵ�ʵ����
 */
public class QueryIndividualUserImp implements QueryIndividualUserDao
{

//	��ȡ��ϸ������Ϣ  name���û�������Ϊ��ѯ����
	@Override
	public User getUser(String name) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
//		�Զ�������ݿ����ӹ�����
		LoginDatabase databaseUtil = new LoginDatabase();
		User user = new User();
		try
		{
			String sql = "select * from student where name=?";
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, name);
//			ִ�в�ѯ
			resultSet = preparedStatement.executeQuery();
//			��װ����
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
			System.out.println("UserDaoImp���ݿ������쳣");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("UserDaoIml������쳣");
		}
//		���ؽ��
		return user;
	}

}
