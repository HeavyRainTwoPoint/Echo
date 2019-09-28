package administrator.Service;

import java.util.List;

import myEntity.User;
/**
 * 
 *  审核类相关的方法的接口
 *
 */
public interface AuditRegisterUserService {
//  获取待注册的用户集合
	public abstract List<User> getRegisterUser();
//	获取通过审核的用户信息
	public abstract User getRegisterUser(String name);
//	将通过审核的用户信息写入数据库中
	public abstract int auditRegisterUser(User user);
//	删除注册用户信息记录
	public abstract int deleteRegisterUser(String name);
	
}
