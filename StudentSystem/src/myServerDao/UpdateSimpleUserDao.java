package myServerDao;

import myEntity.User;
/**
 * 
 * ��ͨ��Ա���¸�����ϢDao��ӿ�
 *
 */
public interface UpdateSimpleUserDao {
//	������ͨ������Ϣ
	public abstract boolean updateUser(User user);
//	����ͷ����Ϣ
	public abstract boolean uploadUserImage(String name,String imagename);
}
