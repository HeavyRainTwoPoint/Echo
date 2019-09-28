package administrator.Dao;

import java.util.List;

import myEntity.User;

/**
 * 
 *  管理员增删改查以及修改头像的Dao层接口
 *
 */
public interface OperateUserDao {
//	未实现的增加成员方法
	public abstract int addUser(User user);
//	未实现的删除方法
	public abstract int deleteUser(String name);
//	未实现的查询方法
	public abstract List<User> getUserlist();
//	未实现的按条件查询方法
	public abstract List<User> getUserByLimit(User user);
//	未实现的更新成员信息方法
	public abstract int updateUser(User user);
//	未实现的上传照片方法
	public abstract int uploadUserImage(String name,String imageName);

}
