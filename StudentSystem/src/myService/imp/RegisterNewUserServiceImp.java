package myService.imp;

import myEntity.User;
import myServerDao.imp.RegisterNewUserImp;
import myService.RegisterNewUserService;
/**
 * 
 * ע�����ӿ�ʵ����
 *
 */
public class RegisterNewUserServiceImp implements RegisterNewUserService {
	@Override
	public boolean registerNewUser(User user) {
		return new RegisterNewUserImp().registerNewUser(user);
	}

}
