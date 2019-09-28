package myServerDao;
/**
 * 单个用户查询类Dao层接口
 */

import myEntity.User;

public interface QueryIndividualUserDao 
{
	public abstract User getUser(String name);
}
