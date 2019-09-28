package administrator.Dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import administrator.Dao.QueryUserByPageDao;
import myDatabaseUtil.LoginDatabase;
import myEntity.User;

/**
 * 
 * ��ҳ��ѯDao���ʵ����
 *
 */
public class QueryUserByPageImp implements QueryUserByPageDao {
//	�Զ�����������ݿ⹤����
	LoginDatabase databaseUtil = new LoginDatabase();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	@Override	//��ȡ��������
	public int getUserCount() {
//		��������
		int userCount = 0;
		try
		{
			String sql = "select count(*) from student";
//			Ԥ����
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			ִ�в�ѯ
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
//				��ֵ
				userCount = resultSet.getInt(1);
			}
		}
		catch (SQLException se) 
		{
			se.printStackTrace();
			System.out.println("��ѯ��������ʧ�ܣ�����");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getUserCount���������쳣����");
		}
		System.out.println("userCount="+userCount);
		return userCount;
	}

	@Override			//��ȡ��ǰҳ���󼯺�      currentPage����ǰҳ     pageSize����ǰҳ��������
	public List<User> getCurrentPageUser(int currentPage, int pageSize) {
		List<User> userlist = new ArrayList<User>();
//		��ʼ��ѯ������
		int start_index = (currentPage-1)*pageSize;
		try
		{
//			��ҳ��ѯSQL���
			String sql = "select * from student order by age limit ?,?";
//			Ԥ����
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			��ռλ����ֵ
			preparedStatement.setInt(1, start_index);
			preparedStatement.setInt(2, pageSize);
//			ִ�в�ѯ
			resultSet = preparedStatement.executeQuery();
//			�������ݷ�װ
			while(resultSet.next())
			{
				User user = new User(resultSet.getString("name"),
						resultSet.getString("sex"),
						resultSet.getInt("age"),
						resultSet.getString("profession"),
						resultSet.getString("hobby"),
						resultSet.getString("phonenumber"),
						resultSet.getString("password"));
				userlist.add(user);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("��ѯ��ǰҳ����ʧ�ܣ�����");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getCurrentPageUser�����쳣������");
		}
//		���ص�ǰҳ���󼯺�
		return userlist;
	}


}
