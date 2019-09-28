package myServerDao;

import myEntity.User;
/**
 * 
 * 普通成员更新个人信息Dao层接口
 *
 */
public interface UpdateSimpleUserDao {
//	更新普通个人信息
	public abstract boolean updateUser(User user);
//	更新头像信息
	public abstract boolean uploadUserImage(String name,String imagename);
}
