package myService.imp;


import myEntity.User;
/**
 * ��ͨ�û���ѯ����ӿ�ʵ����
 */
import myServerDao.imp.QueryIndividualUserImp;
import myService.QueryIndividualUserService;
public class QueyIndividualUserServiceImp implements QueryIndividualUserService
{
//  ��ѯ
	@Override
	public User getUser(String name) {
		//UserDaoImp userDao = new UserDaoImp();
		return new QueryIndividualUserImp().getUser(name);
	}
	
}
