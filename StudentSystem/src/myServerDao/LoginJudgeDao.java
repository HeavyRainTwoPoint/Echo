package myServerDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import myDatabaseUtil.LoginDatabase;
import myEntity.User;

import java.sql.PreparedStatement;
/**
 * 
 * ��¼�ж��ࣺ���ֹ���Ա����ͨ��Ա
 *
 */
public class LoginJudgeDao {
//	�������ݿ��ȡ��֤��Ϣ    user����װ���û���������  flag����ݱ�ʶ��trueΪ����Ա/falseΪ��ͨ��Ա
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
//						�ɹ�����1
						return 1;
					}
				}
//				ʧ�ܷ���0
				return 0;
			}
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
//				�쳣����-1
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
