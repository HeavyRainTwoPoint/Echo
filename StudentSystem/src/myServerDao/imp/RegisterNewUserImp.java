package myServerDao.imp;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import myDatabaseUtil.LoginDatabase;
import myEntity.User;
import myServerDao.RegisterNewUserDao;
/**
 * 
 * ע��ӿڵ�ʵ����
 *
 */
public class RegisterNewUserImp implements RegisterNewUserDao {

//	��ע����Ϣ���������ݿ���
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
//				����ɹ�����true
				flag = true;
			}
			else
			{
//				ʧ�ܷ���false
				flag = false;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("�����ݲ������ݿ�ʧ�ܣ�����");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("RegisterNewUserDaoImp���쳣������");
		}
//		���������
		return flag;
	}

}
