package com.rehus.DBMessage;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rehus.DBConnection.DbPoolSource;

import UserMessage.User;

/**
 * �����ݿ��в�ѯ��Ϣ
 * @author lenovo
 *
 */

public class SelectMessage {
	/**
	 * 
	 * @param user
	 * @return  ������Ϣ0��ʾû�и��û���1��ʾ��¼�ɹ�
	 */
	public static int selectPhoneInDB(User user) {
		int length = 0;
		Connection conn = null;
		try {
			conn = com.rehus.DBConnection.DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("select * from where phone=? and pwd=?");
			pre.setString(1, user.getPhone());
			pre.setString(2, user.getPassword());
			ResultSet set = pre.executeQuery();//ִ��
			while(set.next()) {
				length = 1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return length;
	}
	/**
	 * 
	 * @param user
	 * @return  ����0��ʾû�и��û� ����2��ʾ���仹û�м��� ����1��ʾ��¼�ɹ�
	 */
	public static int selectEmailInDB(User user) {
		int length = 0;
		Connection conn = null;
		try {
			conn = DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("select * from where email=? and pwd=?");
			pre.setString(1, user.getEmail());
			pre.setString(2, user.getPassword());
			ResultSet set = pre.executeQuery();//ִ��
			while(set.next()) {
				int userStatus = set.getInt("user_status");
				if(userStatus==0) {  //Ϊ0��ʾ���仹û�м���
					length = 2;
				}else  //���򼤻���
					length = 1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return length;
	}
	/**
	 * 
	 * @param user
	 * @return  �ж��Ƿ��������û�  3��ʾ�������û�      4��ʾû��
	 */
	public static int isHaveUser(User user) {
		int length = 4;
		Connection conn = null;
		try {
			conn = DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("select * from loaduser where uname=?");
			pre.setString(1, user.getUname());
			ResultSet set = pre.executeQuery();  //ִ��
			while(set.next()) {
				length = 3;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return length;
	}
}
