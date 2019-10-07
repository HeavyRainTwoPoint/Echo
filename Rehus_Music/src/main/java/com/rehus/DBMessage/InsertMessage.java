package com.rehus.DBMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rehus.DBConnection.DbPoolSource;

import UserMessage.User;

/**
 * ע���ʱ�������Ϣ
 * @author lenovo
 *
 */
public class InsertMessage {
	/**
	 * ������Ҫ�ǵ����ȡ��֤��ʱִ�еĲ��뷽��
	 * @param user �û�����Ϣ
	 * @return ����һ�����ݣ��Ƿ����ɹ�
	 */
	public static int insertAjaxPhone(User user) {
		Connection conn = null;
		int length = -1;
		try {
			conn = DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("insert into loadUser(uname,phone,phone_code) values (?,?,?)");
			pre.setString(1, user.getUname());
			pre.setString(2,user.getPhone());
			pre.setInt(3,user.getPhoneCode());
			length = pre.executeUpdate();
		} catch (SQLException e) {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return length;
	}
	/**
	 * �������յ����ݣ�������������
	 * @param user
	 * @return ���س���0��ʾע��У����֤��ʧ�ܣ�1��ʾ�ɹ�
	 */
	public static int insertPhoneOfLoad(User user) {
		Connection conn = null;
		int length = 0;
		boolean flag = false;  //�ж��û���֤���Ƿ���ȷ
		try {
			conn = DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("select * from loadUser where uname=? and phone=?");
			pre.setString(1, user.getUname());
			pre.setString(2,user.getPhone());
			ResultSet set = pre.executeQuery();
			while(set.next()) {
				if(user.getPhoneCode()==set.getInt("phone_code")) {  //��֤����ͬ
					flag = true;   //��ʾ��֤����֤�ɹ�
					length = 1;
				}
			}
			//�����֤��У��ɹ�������Ҫ���������
			if(flag) {
				PreparedStatement prePwd = conn.prepareStatement("update loaduser set pwd=? where uname=?");
				prePwd.setString(1, user.getPassword());
				prePwd.setString(2, user.getUname());
				prePwd.executeUpdate();  //ִ�и��²���
			}else if(!flag) {  //�����֤��У�鲻�ɹ�����ɾ�����ݿ���û���û�в����û�����ģ����Բ���ʹ��������Ϊɾ�����ϣ�
				PreparedStatement preDl = conn.prepareStatement("delete from loadUser where uname=? and phone=?");
				preDl.setString(1, user.getUname());
				preDl.setString(2, user.getPhone());
				preDl.executeUpdate();  //ִ��ɾ������
			}
		} catch (SQLException e) {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return length;
	}
	
	/**
	 * �������ע���ʱ��ִ�еĲ��뷽��
	 * @param user
	 * @return
	 */
	public static int insertEmail(User user) {
		Connection conn = null;
		int length = -1;
		try {
			conn = DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("insert into loadUser(uname,pwd,email,mail_code,user_status) values (?,?,?,?,?)");
			pre.setString(1, user.getUname());
			pre.setString(2,user.getPassword());
			pre.setString(3,user.getEmail());
			pre.setString(4,user.getEmailCode());
			pre.setInt(5, 0);  //���ü���״̬��Ϊ0��δ����״̬
			length = pre.executeUpdate();
		} catch (SQLException e) {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return length;
	}
}

