package administrator.Dao;

import java.util.List;

//import myEntity.PageBean;
import myEntity.User;

public interface QueryUserByPageDao {
//	获取已注册的成员总数
	public abstract int getUserCount();
//	按分页的获取成员集合
	public abstract List<User> getCurrentPageUser(int currentPage,int pageSize);
}
