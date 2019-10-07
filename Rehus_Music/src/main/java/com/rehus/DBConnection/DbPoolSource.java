package com.rehus.DBConnection;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbPoolSource {
	private static ComboPooledDataSource connection = null;
	
	private static DbPoolSource daPool = null;
	//ʹ�þ�̬��ʼ��������ʼ��������
	static {
		daPool = new DbPoolSource();  //��ʼ��
	}
	/**
	 * ��������������ʼ�����ӳصĻ�������
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
		connection.setInitialPoolSize(10);  //���ó�ʼ���ӳش�С
		connection.setMaxPoolSize(40);  //����������ӳش�С
		connection.setMaxIdleTime(50);  //�������ӵ����ʱ��
		connection.setMaxStatements(100);  //����statement�ĸ���
	}
	/**
	 * 
	 * @return  ��ȡ������
	 */
	public static DbPoolSource getInstance() {
		return daPool;
	}
	public Connection getDBConnection() {
		Connection conn = null;  //��ȡ����
		try {
			conn = connection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
