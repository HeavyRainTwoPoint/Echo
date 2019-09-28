package myService.imp;


import myEntity.User;
/**
 * 普通用户查询服务接口实现类
 */
import myServerDao.imp.QueryIndividualUserImp;
import myService.QueryIndividualUserService;
public class QueyIndividualUserServiceImp implements QueryIndividualUserService
{
//  查询
	@Override
	public User getUser(String name) {
		//UserDaoImp userDao = new UserDaoImp();
		return new QueryIndividualUserImp().getUser(name);
	}
	
}
