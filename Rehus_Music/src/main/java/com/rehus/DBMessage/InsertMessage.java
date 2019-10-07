package com.rehus.DBMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rehus.DBConnection.DbPoolSource;

import UserMessage.User;

/**
 * 注册的时候插入信息
 * @author lenovo
 *
 */
public class InsertMessage {
	/**
	 * 这里主要是点击获取验证码时执行的插入方法
	 * @param user 用户的信息
	 * @return 返回一个数据，是否插入成功
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
	 * 插入最终的数据，并且起检测作用
	 * @param user
	 * @return 返回长度0表示注册校验验证码失败，1表示成功
	 */
	public static int insertPhoneOfLoad(User user) {
		Connection conn = null;
		int length = 0;
		boolean flag = false;  //判断用户验证码是否正确
		try {
			conn = DbPoolSource.getInstance().getDBConnection();
			PreparedStatement pre = conn.prepareStatement("select * from loadUser where uname=? and phone=?");
			pre.setString(1, user.getUname());
			pre.setString(2,user.getPhone());
			ResultSet set = pre.executeQuery();
			while(set.next()) {
				if(user.getPhoneCode()==set.getInt("phone_code")) {  //验证码相同
					flag = true;   //表示验证码验证成功
					length = 1;
				}
			}
			//如果验证码校验成功，就需要将密码插入
			if(flag) {
				PreparedStatement prePwd = conn.prepareStatement("update loaduser set pwd=? where uname=?");
				prePwd.setString(1, user.getPassword());
				prePwd.setString(2, user.getUname());
				prePwd.executeUpdate();  //执行更新操作
			}else if(!flag) {  //如果验证码校验不成功，就删除数据库的用户（没有插入用户密码的，所以不能使用密码作为删除材料）
				PreparedStatement preDl = conn.prepareStatement("delete from loadUser where uname=? and phone=?");
				preDl.setString(1, user.getUname());
				preDl.setString(2, user.getPhone());
				preDl.executeUpdate();  //执行删除操作
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
	 * 点击邮箱注册的时候，执行的插入方法
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
			pre.setInt(5, 0);  //设置激活状态码为0，未激活状态
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

