package myService.imp;

import myEntity.User;
import myServerDao.imp.UpdateIndividualUserImp;
import myService.UpdateIndividualUserService;
/**
 * 
 * 更新服务接口实现类
 *
 */
public class UpdateIndividualUserServiceImp implements UpdateIndividualUserService {

//	更新个人信息
	@Override
	public boolean updateUser(User user) {
		return new UpdateIndividualUserImp().updateUser(user);
	}
//	更新头像信息
	public boolean uploadUserImage(String name,String imagename)
	{
		return new UpdateIndividualUserImp().uploadUserImage(name, imagename);
	}
}
