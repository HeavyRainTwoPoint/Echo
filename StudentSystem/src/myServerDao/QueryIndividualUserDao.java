package myServerDao;
/**
 * �����û���ѯ��Dao��ӿ�
 */

import myEntity.User;

public interface QueryIndividualUserDao 
{
	public abstract User getUser(String name);
}
