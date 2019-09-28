package myService;

/**
 * 普通用户查询服务接口
 */
import myEntity.User;
public interface QueryIndividualUserService 
{
	//查询功能
	public abstract User getUser(String name);
}
