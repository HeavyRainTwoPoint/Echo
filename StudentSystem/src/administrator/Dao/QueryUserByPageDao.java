package administrator.Dao;

import java.util.List;

//import myEntity.PageBean;
import myEntity.User;

public interface QueryUserByPageDao {
//	��ȡ��ע��ĳ�Ա����
	public abstract int getUserCount();
//	����ҳ�Ļ�ȡ��Ա����
	public abstract List<User> getCurrentPageUser(int currentPage,int pageSize);
}
