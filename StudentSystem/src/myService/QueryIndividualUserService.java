package myService;

/**
 * ��ͨ�û���ѯ����ӿ�
 */
import myEntity.User;
public interface QueryIndividualUserService 
{
	//��ѯ����
	public abstract User getUser(String name);
}
