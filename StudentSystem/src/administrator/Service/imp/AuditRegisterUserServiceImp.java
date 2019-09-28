package administrator.Service.imp;

import java.util.List;

import administrator.Dao.imp.AuditRegisterUserImp;
import administrator.Service.AuditRegisterUserService;
import myEntity.User;
/**
 * 
 * ʵ�����ע���û����࣬�������Daoʵ����
 *
 */
public class AuditRegisterUserServiceImp implements AuditRegisterUserService{
//	��ȡ����˵��û���Ϣ
	@Override
	public List<User> getRegisterUser()
	{
		return new AuditRegisterUserImp().getRegisterUser();
	}
//	��ȡͨ����˵��û���Ϣ
	@Override
	public User getRegisterUser(String name)
	{
		return new AuditRegisterUserImp().getUser(name);
	}
//	���û���Ϣ��������
	@Override
	public int auditRegisterUser(User user)
	{
		return new AuditRegisterUserImp().auditRegister(user); 
	}
//	ɾ��ע���û���¼
	@Override
	public int deleteRegisterUser(String name)
	{
		return new AuditRegisterUserImp().deleteRegisterUser(name);
	}
}
