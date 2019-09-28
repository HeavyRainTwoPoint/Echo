package C3P0;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Demo {
	public static DataSource datasource;
	static
	{
		ComboPooledDataSource c3p0 = new ComboPooledDataSource();
		try
		{
			c3p0.setDriverClass("com.mysql.jdbc.Driver");
			c3p0.setJdbcUrl("jdbc:mysql://localhost:3306/test");
			c3p0.setUser("root");
			c3p0.setPassword("1234");
		} 
		catch (PropertyVetoException e) {
			e.printStackTrace();
			System.out.println("���ݿ����ӳ��쳣������");
		}
		datasource = c3p0;
	}
	public static Connection getConnection()throws Exception
	{
		return datasource.getConnection();
	}
	public static void close(PreparedStatement pstmt,Connection connection)
	{
		close(pstmt, connection);
	}
	public static void close(ResultSet resultSet,PreparedStatement pstmt,Connection connection)
	{
		if(resultSet != null)
		{
			try
			{
				resultSet.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("�ر�resultSet�쳣����");
			}
		}
		if(pstmt != null)
		{
			try
			{
				pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("�ر�pstmt�쳣����");
			}
		}
		if(connection != null)
		{
			try
			{
				connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("�ر�connection�쳣����");
			}
		}
	}
}
