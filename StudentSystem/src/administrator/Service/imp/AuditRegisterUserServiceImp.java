package administrator.Service.imp;

import java.util.List;

import administrator.Dao.imp.AuditRegisterUserImp;
import administrator.Service.AuditRegisterUserService;
import myEntity.User;
/**
 * 
 * 实现审核注册用户的类，负责调用Dao实现类
 *
 */
public class AuditRegisterUserServiceImp implements AuditRegisterUserService{
//	获取待审核的用户信息
	@Override
	public List<User> getRegisterUser()
	{
		return new AuditRegisterUserImp().getRegisterUser();
	}
//	获取通过审核的用户信息
	@Override
	public User getRegisterUser(String name)
	{
		return new AuditRegisterUserImp().getUser(name);
	}
//	将用户信息保存起来
	@Override
	public int auditRegisterUser(User user)
	{
		return new AuditRegisterUserImp().auditRegister(user); 
	}
//	删除注册用户记录
	@Override
	public int deleteRegisterUser(String name)
	{
		return new AuditRegisterUserImp().deleteRegisterUser(name);
	}
}
