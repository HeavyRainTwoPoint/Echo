package myDatabaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginDatabase {
	private Connection connection =null;
	private final static String USER = "root";
	private final static String PWD = "1234";
	
	public Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String connectionSQL = "jdbc:mysql://localhost:3306/test";
			connection = DriverManager.getConnection(connectionSQL,USER,PWD);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("���ݿ������쳣������");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("LoginDatabase������쳣������");
		}
		return connection;
	}
	
}
