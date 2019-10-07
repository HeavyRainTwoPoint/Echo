package com.rehus.DBMessage;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rehus.DBConnection.DbPoolSource;

import UserMessage.User;

/**
 * 从数据库中查询信息
 * @author lenovo
 *
 */

public class SelectMessage {
	/**
	 * 
	 * @param user
	 * @return  返回信息0表示没有该用户，1表示登录成功
	 */
	public static int selectPhoneInDB(User user) {
		int length = 0;
		Connection conn = null;
		try {
			conn = com.rehus.DBConnection.DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("select * from where phone=? and pwd=?");
			pre.setString(1, user.getPhone());
			pre.setString(2, user.getPassword());
			ResultSet set = pre.executeQuery();//执行
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
	 * @return  返回0表示没有该用户 返回2表示邮箱还没有激活 返回1表示登录成功
	 */
	public static int selectEmailInDB(User user) {
		int length = 0;
		Connection conn = null;
		try {
			conn = DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("select * from where email=? and pwd=?");
			pre.setString(1, user.getEmail());
			pre.setString(2, user.getPassword());
			ResultSet set = pre.executeQuery();//执行
			while(set.next()) {
				int userStatus = set.getInt("user_status");
				if(userStatus==0) {  //为0表示邮箱还没有激活
					length = 2;
				}else  //否则激活了
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
	 * @return  判断是否有重名用户  3表示有重名用户      4表示没有
	 */
	public static int isHaveUser(User user) {
		int length = 4;
		Connection conn = null;
		try {
			conn = DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("select * from loaduser where uname=?");
			pre.setString(1, user.getUname());
			ResultSet set = pre.executeQuery();  //执行
			while(set.next()) {
				length = 3;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return length;
	}
}
