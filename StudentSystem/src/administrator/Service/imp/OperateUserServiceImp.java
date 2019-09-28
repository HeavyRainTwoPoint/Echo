package administrator.Service.imp;

import java.util.List;

import administrator.Dao.imp.OperateUserDaoImp;
import administrator.Service.OperateUserService;
import myEntity.User;

/**
 * 
 *   管理员删 改 查 修改头像 方法的接口的实现类，负责调用OperateUserDao层实现类
 *
 */
public class OperateUserServiceImp implements OperateUserService {
	
//	增
	@Override
	public int addUser(User user) {
		return new OperateUserDaoImp().addUser(user);
	}

//	删
	@Override
	public int deleteUser(String name) {
		return new OperateUserDaoImp().deleteUser(name);
	}

//	查
	@Override
	public List<User> getUserlist() {
		return new OperateUserDaoImp().getUserlist();
	}
//	按条件查询方法
	@Override
	public List<User> getUserByLimit(User user)
	{
		return new OperateUserDaoImp().getUserByLimit(user);
	}

//	改
	@Override
	public int updateUser(User user) {
		return new OperateUserDaoImp().updateUser(user);
	}

//	修改头像
	@Override
	public int uploadUserImage(String name, String imageName) {
		return new OperateUserDaoImp().uploadUserImage(name, imageName);
	}

}
