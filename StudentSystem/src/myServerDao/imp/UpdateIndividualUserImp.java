package myServerDao.imp;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import myDatabaseUtil.LoginDatabase;
import myEntity.User;
import myServerDao.UpdateSimpleUserDao;
/**
 * 
 * 普通成员更新个人信息Dao层接口实现类
 *
 */
public class UpdateIndividualUserImp implements UpdateSimpleUserDao {

//	将更新后的信息保存到数据库中
	@Override
	public boolean updateUser(User user) {
		PreparedStatement preparedStatement = null;
		LoginDatabase databaseUtil = new LoginDatabase();
		int update_result = -1;
		boolean flag = false;
		try
		{
			String sql = "update student set sex=?,age=?,profession=?,"
					+ "phonenumber=?,hobby=? where name=?";
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
			preparedStatement.setString(1,user.getSex());
			preparedStatement.setInt(2,(int)user.getAge());
			preparedStatement.setString(3,user.getProfession());
			preparedStatement.setString(4,user.getPhonenumber());
			preparedStatement.setString(5,user.getHobby());
			preparedStatement.setString(6, user.getName());
			update_result = preparedStatement.executeUpdate();
			if(update_result == 1)
			{
//				更新成功返回true
				flag = true;
			}
			else
			{
//				失败返回false
				flag = false;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("修改数据库数据失败！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("UpdateSimpleUserDaoImp类异常！！");
		}
//		将结果返回
		return flag;
	}

//	将头像信息保存在数据库中   name：用户名     imageName：头像名
	@Override
	public boolean uploadUserImage(String name, String imagename) {
		PreparedStatement preparedStatement = null;
		LoginDatabase databaseUtil = new LoginDatabase();
		int upload_result = -1;
		boolean flag = false;
		try
		{
			String sql = "update student set imagename = ? where name = ?";
			preparedStatement = databaseUtil.getConnection().prepareStatement(sql);
			preparedStatement.setString(1,imagename);
			preparedStatement.setString(2,name);
			upload_result = preparedStatement.executeUpdate();
			if(upload_result == 1)
			{
				flag = true;
				System.out.println("上传数据库成功");
			}
			else
			{
				flag = false;
				System.out.println("上传数据库失败");
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("上传图片名数据失败！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("uploadimage方法异常！！");
		}
		return flag;
	}

}
