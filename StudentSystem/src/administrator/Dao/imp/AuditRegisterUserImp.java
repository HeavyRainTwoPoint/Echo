package administrator.Dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import administrator.Dao.AuditRegisterUserDao;
import myDatabaseUtil.LoginDatabase;
import myEntity.User;

public class AuditRegisterUserImp implements AuditRegisterUserDao
{
//	�Զ�����������ݿ⹤����
	LoginDatabase databaseUtil = new LoginDatabase();
//	����һ��PreparedStatement����
	PreparedStatement preparedStatement = null;
	@Override   //��ȡ��ע���ע������Ϣ
	public List<User> getRegisterUser() 
	{
//		����һ��List����
		List<User> userlist = new ArrayList<User>();
//		��ѯSQL���
		String sql = "select * from register";
		try
		{
//			�������ݿⲢ��SQL������Ԥ����
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			��ȡ��ѯ�Ľ����
			ResultSet resultSet = preparedStatement.executeQuery();
//			�������������ĳ�Ա����һһ��װ��Javabean
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
//				����װ�õ����ݷ���List��������
				userlist.add(user);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("��ѯע������Ϣʧ�ܣ�~");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getRegister�����쳣����");
		}
		return userlist;
	}
//	��ȡ����ע���ߵ�ע����Ϣ      name��ע��֤����
	public User getUser(String name)
	{
//		��ѯSQL���
		String sql = "select * from register where name=?";
//		����װ���ݵ�Javabean
		User user = new User();
		try
		{
//			�������ݿⲢ����SQL��� ��Ԥ����
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			��ռλ����ֵ
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
//			��ȡ���������Ϣ�������з�װ
			if(resultSet.next())
			{
				user.setName(resultSet.getString("name"));
				user.setSex(resultSet.getString("sex"));
				user.setAge(resultSet.getInt("age"));
				user.setProfession(resultSet.getString("profession"));
				user.setHobby(resultSet.getString("hobby"));
				user.setPhonenumber(resultSet.getString("phonenumber"));
				user.setPassword(resultSet.getString("password"));
			}
			else
			{
				return null;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("��ȡ��ע���û���Ϣʧ�ܣ�~");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getRegisterUser�����쳣����");
		}
//		���ط�װ�õ�user����
		return user;
	}
//	�����³�Ա����   user�������ӵ��³�Ա
	public int auditRegister(User user)
	{
//		�жϸ��û�����û�б�ע����
		boolean name_hasExist = true;
//		��ѯ���û������ڵĸ���
		String sql = "select count(*) from student where name=?";
		{
			try
			{
				preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//				��ռλ����ֵ
				preparedStatement.setString(1, user.getName());
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next())
				{
					if(resultSet.getInt(1)>0)
					{
						System.out.println("�û��Ѵ��ڣ�����");
						return 0;
					}
					else
					{
						name_hasExist = false;
					}
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
				System.out.println("����û�ʧ�ܣ�����");
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("auditRegister�����쳣����");
			}
			
		}
//		������������û����ݱ���
		if(!name_hasExist)
		{		
//			�ж��������ı�ʶ
			int insert_result = 0;
//			���û������ϴ�ͷ�񣬵�ͨ��ע��ʱ��null�䵱ռλ��
			user.setImagename("null");
//			���ӵ�SQL���
			sql = "insert into student values(?,?,?,?,?,?,?,?)";
			try
			{
//				�������ݿⲢ����SQL��� ��Ԥ����
				preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//				��ռλ����ֵ
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getSex());
				preparedStatement.setInt(3, user.getAge());
				preparedStatement.setString(4, user.getProfession());
				preparedStatement.setString(5, user.getHobby());
				preparedStatement.setString(6, user.getPhonenumber());
				preparedStatement.setString(7, user.getPassword());
				preparedStatement.setString(8, user.getImagename());
//				�Ա�ʶ���и�ֵ
				insert_result = preparedStatement.executeUpdate();
			}
			catch (SQLException se) {
				se.printStackTrace();
				System.out.println("����û�ʧ�ܣ�����");
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("auditRegister�����쳣����");
			}
//			���سɹ����Ľ��
			return insert_result;
		}
		return 0;
	}
//		�ڴ�ע��ĳ�Ա�б���ɾ���Ѿ�ͨ����˵ĳ�Ա  name����ɾ���ĳ�Ա
		public int deleteRegisterUser(String name)
		{
			try
			{
//				ִ��ɾ����SQL���
				String sql = "delete from register where name = ?";
//				�������ݿⲢ����SQL��� ��Ԥ����
				preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//				��ռλ����ֵ
				preparedStatement.setString(1, name);
//			   	ִ��ɾ��
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
				System.out.println("ɾ��ע������ʧ�ܣ�����");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("deleteRegisterUser�����쳣������");
			}
			return 0;
		}

}
