package administrator.Service;

import java.util.List;

import myEntity.User;
/**
 * 
 *  �������صķ����Ľӿ�
 *
 */
public interface AuditRegisterUserService {
//  ��ȡ��ע����û�����
	public abstract List<User> getRegisterUser();
//	��ȡͨ����˵��û���Ϣ
	public abstract User getRegisterUser(String name);
//	��ͨ����˵��û���Ϣд�����ݿ���
	public abstract int auditRegisterUser(User user);
//	ɾ��ע���û���Ϣ��¼
	public abstract int deleteRegisterUser(String name);
	
}
