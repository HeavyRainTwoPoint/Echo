package myServerDao.imp;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import myDatabaseUtil.LoginDatabase;
import myEntity.User;
import myServerDao.UpdateSimpleUserDao;
/**
 * 
 * ��ͨ��Ա���¸�����ϢDao��ӿ�ʵ����
 *
 */
public class UpdateIndividualUserImp implements UpdateSimpleUserDao {

//	�����º����Ϣ���浽���ݿ���
	@Override
	public boolean updateUser(User user) {
		PreparedStatement preparedStatement = null;
		LoginDatabase databaseUtil = new LoginDatabase();
		int update_result = -1;
		boolean flag = false;
		try
		{
			String sql = "update student set sex=?,age=?,profession=?,"
					+ "phonenumber=?,hobby=? where name=?";
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
			preparedStatement.setString(1,user.getSex());
			preparedStatement.setInt(2,(int)user.getAge());
			preparedStatement.setString(3,user.getProfession());
			preparedStatement.setString(4,user.getPhonenumber());
			preparedStatement.setString(5,user.getHobby());
			preparedStatement.setString(6, user.getName());
			update_result = preparedStatement.executeUpdate();
			if(update_result == 1)
			{
//				���³ɹ�����true
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
			System.out.println("�޸����ݿ�����ʧ�ܣ�");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("UpdateSimpleUserDaoImp���쳣����");
		}
//		���������
		return flag;
	}

//	��ͷ����Ϣ���������ݿ���   name���û���     imageName��ͷ����
	@Override
	public boolean uploadUserImage(String name, String imagename) {
		PreparedStatement preparedStatement = null;
		LoginDatabase databaseUtil = new LoginDatabase();
		int upload_result = -1;
		boolean flag = false;
		try
		{
			String sql = "update student set imagename = ? where name = ?";
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
			preparedStatement.setString(1,imagename);
			preparedStatement.setString(2,name);
			upload_result = preparedStatement.executeUpdate();
			if(upload_result == 1)
			{
				flag = true;
				System.out.println("�ϴ����ݿ�ɹ�");
			}
			else
			{
				flag = false;
				System.out.println("�ϴ����ݿ�ʧ��");
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("�ϴ�ͼƬ������ʧ�ܣ�");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("uploadimage�����쳣����");
		}
		return flag;
	}

}
