package administrator.Service;

import java.util.List;

import myEntity.User;
/**
 * 
 *   ����Աɾ �� �� �޸�ͷ�� �����Ľӿ�
 *
 */
public interface OperateUserService {
//	δʵ�ֵ����ӳ�Ա����
	public abstract int addUser(User user);
//	δʵ�ֵ�ɾ������
	public abstract int deleteUser(String name);
//	δʵ�ֵĲ�ѯ����
	public abstract List<User> getUserlist();
//	δʵ�ֵİ�������ѯ����
	public abstract List<User> getUserByLimit(User user);
//	δʵ�ֵĸ��³�Ա��Ϣ����
	public abstract int updateUser(User user);
//	δʵ�ֵ��ϴ���Ƭ����
	public abstract int uploadUserImage(String name,String imageName);

}
