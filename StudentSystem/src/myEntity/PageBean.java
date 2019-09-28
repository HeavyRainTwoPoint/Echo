package myEntity;

import java.util.List;

import administrator.Service.imp.QueryUserByPageServiceImp;
/**
 * 
 * PageBean ���ڷ�װ��ҳ��Ϣ
 *
 */
public class PageBean {
//	��ǰҳҳ��
	private int currentPage;
//	ҳ��������
	private int pageSize;
//	�û�����
	private int userCount;
//	��ҳ����
	private int pageCount;
//	��ǰҳ���󼯺�
	private List<User> userlist;
//	�޲ι���
	public PageBean() {};
//	�вι���
	public PageBean(int currentPage, int pageSize, int userCount,List<User> userlist) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.setUserCount(userCount);
		this.userlist = userlist;
		
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
		int pageCount = this.userCount % this.pageSize == 0 ? (this.userCount / this.pageSize):(this.userCount / this.pageSize)+1; 
		this.setPageCount(pageCount);
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	public String getTime()
	{
		return new Time().toString();
	}
	
	
	
}
