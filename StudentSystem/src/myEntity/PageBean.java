package myEntity;

import java.util.List;

import administrator.Service.imp.QueryUserByPageServiceImp;
/**
 * 
 * PageBean 用于封装分页信息
 *
 */
public class PageBean {
//	当前页页码
	private int currentPage;
//	页面数据量
	private int pageSize;
//	用户总数
	private int userCount;
//	分页总数
	private int pageCount;
//	当前页对象集合
	private List<User> userlist;
//	无参构造
	public PageBean() {};
//	有参构造
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
