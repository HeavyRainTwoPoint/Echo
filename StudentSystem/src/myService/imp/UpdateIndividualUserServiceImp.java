package myService.imp;

import myEntity.User;
import myServerDao.imp.UpdateIndividualUserImp;
import myService.UpdateIndividualUserService;
/**
 * 
 * ���·���ӿ�ʵ����
 *
 */
public class UpdateIndividualUserServiceImp implements UpdateIndividualUserService {

//	���¸�����Ϣ
	@Override
	public boolean updateUser(User user) {
		return new UpdateIndividualUserImp().updateUser(user);
	}
//	����ͷ����Ϣ
	public boolean uploadUserImage(String name,String imagename)
	{
		return new UpdateIndividualUserImp().uploadUserImage(name, imagename);
	}
}
