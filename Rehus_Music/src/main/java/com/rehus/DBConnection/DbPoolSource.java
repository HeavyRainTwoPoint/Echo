package com.rehus.DBConnection;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbPoolSource {
	private static ComboPooledDataSource connection = null;
	
	private static DbPoolSource daPool = null;
	//使用静态初始化块来初始化构造器
	static {
		daPool = new DbPoolSource();  //初始化
	}
	/**
	 * 构造器，用来初始化连接池的基本参数
	 */
	public DbPoolSource() {
		try 
		{
			connection = new ComboPooledDataSource();
			connection.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		connection.setJdbcUrl("jdbc:mysql://localhost:3306/encho");
		connection.setUser("root");
		connection.setPassword("123456");
		connection.setInitialPoolSize(10);  //设置初始连接池大小
		connection.setMaxPoolSize(40);  //设置最大连接池大小
		connection.setMaxIdleTime(50);  //设置连接的最大时间
		connection.setMaxStatements(100);  //设置statement的个数
	}
	/**
	 * 
	 * @return  获取构造器
	 */
	public static DbPoolSource getInstance() {
		return daPool;
	}
	public Connection getDBConnection() {
		Connection conn = null;  //获取连接
		try {
			conn = connection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
