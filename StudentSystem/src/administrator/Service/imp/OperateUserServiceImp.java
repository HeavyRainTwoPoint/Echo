package administrator.Service.imp;

import java.util.List;

import administrator.Dao.imp.OperateUserDaoImp;
import administrator.Service.OperateUserService;
import myEntity.User;

/**
 * 
 *   ����Աɾ �� �� �޸�ͷ�� �����Ľӿڵ�ʵ���࣬�������OperateUserDao��ʵ����
 *
 */
public class OperateUserServiceImp implements OperateUserService {
	
//	��
	@Override
	public int addUser(User user) {
		return new OperateUserDaoImp().addUser(user);
	}

//	ɾ
	@Override
	public int deleteUser(String name) {
		return new OperateUserDaoImp().deleteUser(name);
	}

//	��
	@Override
	public List<User> getUserlist() {
		return new OperateUserDaoImp().getUserlist();
	}
//	��������ѯ����
	@Override
	public List<User> getUserByLimit(User user)
	{
		return new OperateUserDaoImp().getUserByLimit(user);
	}

//	��
	@Override
	public int updateUser(User user) {
		return new OperateUserDaoImp().updateUser(user);
	}

//	�޸�ͷ��
	@Override
	public int uploadUserImage(String name, String imageName) {
		return new OperateUserDaoImp().uploadUserImage(name, imageName);
	}

}
