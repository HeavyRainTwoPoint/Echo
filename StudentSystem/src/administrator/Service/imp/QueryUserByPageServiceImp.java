package administrator.Service.imp;

import java.util.List;

import administrator.Dao.imp.QueryUserByPageImp;
import myEntity.User;
/**
 * 
 * 分页查询的Dao层调用类
 *
 */
public class QueryUserByPageServiceImp {
//	获取数据总数
	public int getUserCount() 
	{
		return new QueryUserByPageImp().getUserCount();
	}
//	获取当前页对象集合       currentPage：当前页页码   pageSize：当前页需要显示的数据总量
	public List<User> getCurrentUserList(int currentPage,int pageSize)
	{
		return new QueryUserByPageImp().getCurrentPageUser(currentPage, pageSize);
	}
}
