package myService.imp;

import myEntity.User;
import myServerDao.imp.RegisterNewUserImp;
import myService.RegisterNewUserService;
/**
 * 
 * 注册服务接口实现类
 *
 */
public class RegisterNewUserServiceImp implements RegisterNewUserService {
	@Override
	public boolean registerNewUser(User user) {
		return new RegisterNewUserImp().registerNewUser(user);
	}

}
