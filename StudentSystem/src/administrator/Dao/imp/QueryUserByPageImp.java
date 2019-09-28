package administrator.Dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import administrator.Dao.QueryUserByPageDao;
import myDatabaseUtil.LoginDatabase;
import myEntity.User;

/**
 * 
 * 分页查询Dao层的实现类
 *
 */
public class QueryUserByPageImp implements QueryUserByPageDao {
//	自定义的连接数据库工具类
	LoginDatabase databaseUtil = new LoginDatabase();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	@Override	//获取数据总数
	public int getUserCount() {
//		数据总数
		int userCount = 0;
		try
		{
			String sql = "select count(*) from student";
//			预编译
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			执行查询
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
//				赋值
				userCount = resultSet.getInt(1);
			}
		}
		catch (SQLException se) 
		{
			se.printStackTrace();
			System.out.println("查询数据总数失败！！！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getUserCount方法出现异常！！");
		}
		System.out.println("userCount="+userCount);
		return userCount;
	}

	@Override			//获取当前页对象集合      currentPage：当前页     pageSize：当前页数据容量
	public List<User> getCurrentPageUser(int currentPage, int pageSize) {
		List<User> userlist = new ArrayList<User>();
//		开始查询的索引
		int start_index = (currentPage-1)*pageSize;
		try
		{
//			分页查询SQL语句
			String sql = "select * from student order by age limit ?,?";
//			预编译
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
//			对占位符赋值
			preparedStatement.setInt(1, start_index);
			preparedStatement.setInt(2, pageSize);
//			执行查询
			resultSet = preparedStatement.executeQuery();
//			进行数据封装
			while(resultSet.next())
			{
				User user = new User(resultSet.getString("name"),
						resultSet.getString("sex"),
						resultSet.getInt("age"),
						resultSet.getString("profession"),
						resultSet.getString("hobby"),
						resultSet.getString("phonenumber"),
						resultSet.getString("password"));
				userlist.add(user);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("查询当前页数据失败！！！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("getCurrentPageUser方法异常！！！");
		}
//		返回当前页对象集合
		return userlist;
	}


}
