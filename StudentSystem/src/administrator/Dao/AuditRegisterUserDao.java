package administrator.Dao;

import java.util.List;

import myEntity.User;

public interface AuditRegisterUserDao {
//	获取代注册成员的信息
	public abstract List<User> getRegisterUser();
}
