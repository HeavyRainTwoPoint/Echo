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
 *  ʵ�ֹ���Աɾ���ġ��顢���ܵ�Dao��ʵ����
 *
 */
public class OperateUserDaoImp implements OperateUserDao {

	//�Զ�����������ݿ⹤����
	LoginDatabase databaseUtil = new LoginDatabase();
	//����һ��PreparedStatement����
	PreparedStatement preparedStatement = null;
	@Override     //ɾ������   name����ɾ���ĳ�Ա
	public int deleteUser(String name) {
		try
		{
			
			String sql = "delete from student where name = ?";
//			�˴���ȡconnection������SQL��Ԥ����
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			��ռλ����ֵ
			preparedStatement.setString(1, name);
//			ִ��ɾ��������ȡɾ�����
			int delete_result = preparedStatement.executeUpdate();
			if(delete_result == 1)
			{
//				�ɹ��򷵻�1
				return 1;
			}
			else
			{
//				ʧ���򷵻�0
				return 0;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("����Աɾ������ʧ�ܣ�����");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("deleteUser�����쳣������");
		}
		return 0;
	}

	@Override		//   ��ѯȫ����Ա�����������������С����
	public List<User> getUserlist() {
		ResultSet resultSet = null;
		List<User> userlist = new ArrayList<User>();
		try
		{
			String sql = "select * from student order by age";
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			ִ�в�ѯ
			resultSet = preparedStatement.executeQuery();
//			�����ݽ��з�װ
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
			System.out.println("����Ա��ѯ���ݿ������쳣������");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getAllUser�����쳣������");
		}
		return userlist;
	}

	@Override    //�����û���Ϣ����
	public int updateUser(User user) {
		try
		{
//			���и��³�Ա��Ϣ������SQL���
			String sql = "update student set sex=?,age=?,profession=?,"+ 
				"phonenumber=?,hobby=?, password=? where name=?";
//			Ԥ����
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			��ռλ�����и�ֵ
			preparedStatement.setString(1,user.getSex());
			preparedStatement.setInt(2,(int)user.getAge());
			preparedStatement.setString(3,user.getProfession());
			preparedStatement.setString(4,user.getPhonenumber());
			preparedStatement.setString(5,user.getHobby());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getName());
//			ִ�и��£�������һ�����
			int update_result = preparedStatement.executeUpdate();
			if(update_result == 1)
			{
//				�ɹ��򷵻�1
				return 1;
			}
			else
			{
//				ʧ���򷵻�0
				return 0;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("����Ա�޸������쳣����");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("UpdaetUser���쳣������");
		}
		return 0;
	}

	@Override	//  �ϴ���Ƭ�ķ���      name����Ҫ�ϴ�ͷ��ĳ�Ա     imageName��ͼƬ���ļ���
	public int uploadUserImage(String name, String imageName) {
		try
		{
//			���и��³�Ա��Ϣ������SQL���
			String sql = "update student set imagename=? where name=?";
//			Ԥ����
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			��ռλ�����и�ֵ
			preparedStatement.setString(1,imageName);
			preparedStatement.setString(2, name);
//			ִ���ϴ���������һ�����
			int upload_result = preparedStatement.executeUpdate();
			if(upload_result == 1)
			{
//				�ɹ��򷵻�1
				return 1;
			}
			else
			{
//				ʧ���򷵻�0
				return 0;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("����Ա�޸������쳣����");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("UpdaetUser���쳣������");
		}
		return 0;
	}
//	��������ѯ���ݷ���
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
			System.out.println("��������ѯ�����쳣");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getUserByLimit�����쳣");
		}
		return userlist;
		
	}

//	����Ա����³�Ա�ķ���
	@Override
	public int addUser(User user) {
		try
		{
			String sql = "insert into student(name,sex,age,profession,phonenumber,hobby,password,imageName"
					+ ") values(?,?,?,?,?,?,?,?)";
//			Ԥ����sql��䣬����ռλ����ֵ
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSex());
			preparedStatement.setInt(3, user.getAge());
			preparedStatement.setString(4, user.getProfession());
			preparedStatement.setString(5, user.getPhonenumber());
			preparedStatement.setString(6, user.getHobby());
			preparedStatement.setString(7, user.getPassword());
			preparedStatement.setString(8, user.getImagename());
//			��ȡ��ӽ��
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
			System.out.print("����Ա����³�Աʧ��!!��");
			return -1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("addUser�����쳣������");
			return -1;
		}
		
	}

}
