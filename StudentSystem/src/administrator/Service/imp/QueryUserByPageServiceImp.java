package administrator.Service.imp;

import java.util.List;

import administrator.Dao.imp.QueryUserByPageImp;
import myEntity.User;
/**
 * 
 * ��ҳ��ѯ��Dao�������
 *
 */
public class QueryUserByPageServiceImp {
//	��ȡ��������
	public int getUserCount() 
	{
		return new QueryUserByPageImp().getUserCount();
	}
//	��ȡ��ǰҳ���󼯺�       currentPage����ǰҳҳ��   pageSize����ǰҳ��Ҫ��ʾ����������
	public List<User> getCurrentUserList(int currentPage,int pageSize)
	{
		return new QueryUserByPageImp().getCurrentPageUser(currentPage, pageSize);
	}
}
