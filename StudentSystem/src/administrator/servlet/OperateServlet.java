package administrator.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administrator.Service.imp.AuditRegisterUserServiceImp;
import administrator.Service.imp.OperateUserServiceImp;
import myEntity.User;
import myService.imp.QueyIndividualUserServiceImp;

/**
 * 
 * 负责管理员增删改查、审核的servlet
 *
 */
@WebServlet("/OperateServlet")
public class OperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		设置编码方式
		request.setCharacterEncoding("utf-8");
//		获取需要操作的对象名  
		String name = request.getParameter("name");
//		操作的类型
		String type = request.getParameter("type");
//		switch结构进行相应操作的实现或者页面跳转
		switch(type)
		{
//		SM：显示更多细节信息
		case "SM":
//			该方法会跳转到修改用户信息的页面   name：待修改信息的用户名
			this.showMoreContext(request, response, name);
			break;
//			U：Update修改
		case "U":
//			通过该方法对name下的个人信息进行更改
			this.update(request, response, name);
			break;
//			D：Delete删除
		case "D":
//			该方法会将name对应的记录从数据库中删除
			this.delete(request, response, name);
			break;
//			SR:展示待审核的新用户
		case "SR":
//			该方法会跳转到查看注册用户的页面
			this.showRegisterUser(request, response);
			break;
//			P:Pass通过审核
		case "P":
//			该方法将name下的信息保存到数据库中
			this.auditReginsterUser(request, response, name);
			break;
//			NP：不通过审核
		case "NP":
//			将name下的信息从注册记录中删去
			AuditRegisterUserServiceImp operator = new AuditRegisterUserServiceImp();
			int delete_result = operator.deleteRegisterUser(name);
			if(delete_result == 1)
			{
				System.out.println("已经删除不通过的注册者："+name);
				List<User> userlist = new AuditRegisterUserServiceImp().getRegisterUser();
//				如果审核的数据是最后一条，完成之后跳转返回首页，
//				否则继续审核
				if(userlist.isEmpty())
				{
					
					request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
				}
				else
				{	
					request.setAttribute("userlist", userlist);
					request.getRequestDispatcher("/administratorJsp/auditUser.jsp").forward(request, response);
				}
			}
			break;
//			QUB:跳转页面到按条件查找页面
		case "QUB":
			this.turnToQuery(request, response);
			break;
//			SEARCH:查找
		case "SEARCH":
//			按条件筛选对象
			this.getUserWithLimit(request,response);
			break;
//			返回首页
		case"R":
			this.backToPageOne(request,response);
			break;
		case "A":
			request.getRequestDispatcher("/administratorJsp/addUser.jsp").forward(request, response);
			break;
		case "ADD":
			this.addUser(request,response);
			break;
//			注销登录，返回登录页
		case "EXIT":
			this.exit(request,response);
			break;
		}
	
	}
	
//	负责跳转到展示详细信息页面    name：用户名，作为查询索引
	private void showMoreContext(HttpServletRequest request,HttpServletResponse response,String name)
	{
//		获取数据，
		QueyIndividualUserServiceImp queyIndividualUser = new QueyIndividualUserServiceImp();
		User user = queyIndividualUser.getUser(name);
		try 
		{
//			将Javabean对象放在request域中
			request.setAttribute("user", user);
//			跳转页面
			request.getRequestDispatcher("/administratorJsp/getUpdateUser.jsp").forward(request, response);
		} 
		catch (ServletException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

//	负责更新信息的方法
	private void update(HttpServletRequest request,HttpServletResponse response,String name)
	{
		User user = new User();
//		从前端页面获取到信息并进行封装
		user.setName(request.getParameter("name"));
		user.setSex(request.getParameter("sex"));
		user.setAge(Integer.parseInt(request.getParameter("age")));
		user.setProfession(request.getParameter("profession"));
		user.setPhonenumber(request.getParameter("phonenumber"));
		user.setHobby(request.getParameter("hobby"));
		user.setPassword(request.getParameter("password"));
//		执行更新
		int update_result = new OperateUserServiceImp().updateUser(user);
		if(update_result == 1)
		{
//			更新成功返回首页
			try {
				request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
				System.out.println("数据更新异常！！！");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("未知异常！！！");
			}	
		}
		else
		{
			System.out.println("修改失败！！");
		}
	}
//	删除操作  name：用户名，作为查询索引
	private void delete(HttpServletRequest request,HttpServletResponse response,String name)
	{
		int delete_result = new OperateUserServiceImp().deleteUser(name);
		if(delete_result == 1)
		{
//			删除成功则跳转到首页，刷新
			try {
				request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else
		{
			System.out.println("删除失败！！");
		}
	}
//	展示待审核的用户
	private void showRegisterUser(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			List<User> userlist = new AuditRegisterUserServiceImp().getRegisterUser();
//			如果没有待审核的用户，不跳转
			if(userlist.isEmpty())
			{
				request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
			}
			else
			{
				request.setAttribute("userlist", userlist);
				request.getRequestDispatcher("/administratorJsp/auditUser.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
//	将通过审核的用户信息保存起来   name：用户名，作为查询索引
	private void auditReginsterUser(HttpServletRequest request,HttpServletResponse response,String name)
	{
//		声明一个AuditRegisterUserServiceImp对象进行信息保存以及删除
		AuditRegisterUserServiceImp operator = new AuditRegisterUserServiceImp();
		int result = operator.auditRegisterUser(operator.getRegisterUser(name));
		if(result == 1)
		{
			try
			{
				int delete_result = operator.deleteRegisterUser(name);
				if(delete_result == 1 )
				{
					System.out.println("删除了一条记录！！");
				}
//				如果还有数据则继续跳转回审核页面，否则回到首页
				List<User> userlist = new AuditRegisterUserServiceImp().getRegisterUser();
				if(userlist.isEmpty())
				{
					request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
				}
				else
				{	
					request.setAttribute("userlist", userlist);
					request.getRequestDispatcher("/administratorJsp/auditUser.jsp").forward(request, response);
				}
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else
		{
			try
			{
				List<User> userlist = new AuditRegisterUserServiceImp().getRegisterUser();
				request.setAttribute("userlist", userlist);
				request.getRequestDispatcher("/administratorJsp/auditUser.jsp").forward(request, response);
			}catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
//	注销登录
	private void exit(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
//			将session无效化
			request.getSession().invalidate();
			request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
	
		}catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	private void turnToQuery(HttpServletRequest request,HttpServletResponse response)
	{
//		跳转到按条件查询页面
		try
		{
			request.setAttribute("data", "");
			request.getRequestDispatcher("/administratorJsp/QueryUserByLimit.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
//	将用户输入的数据封装，进行模糊查询，并跳转显示结果
	private void getUserWithLimit(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setAge(Integer.parseInt(request.getParameter("age")));
			user.setSex(request.getParameter("sex"));
			user.setProfession(request.getParameter("profession"));
			user.setPhonenumber(request.getParameter("phonenumber"));
			user.setHobby(request.getParameter("hobby"));
			user.setPassword(request.getParameter("password"));
			List<User> userlist = new OperateUserServiceImp().getUserByLimit(user);
			if(userlist.isEmpty())
			{
//				无符合条件的数据的跳转
				request.setAttribute("data", "");
				request.getRequestDispatcher("/administratorJsp/QueryUserByLimit.jsp").forward(request, response);
			}
			else
			{
//				有符合条件的数据的跳转
				request.setAttribute("userlist", userlist);
				request.getRequestDispatcher("/administratorJsp/showQueryResult.jsp").forward(request, response);
			}
		}catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
//	返回首页
	private void backToPageOne(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
		}catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
//	添加新成员
	private void addUser(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setAge(Integer.parseInt(request.getParameter("age")));
			user.setSex(request.getParameter("sex"));
			user.setProfession(request.getParameter("profession"));
			user.setHobby(request.getParameter("hobby"));
			user.setPassword(request.getParameter("password"));
			user.setPhonenumber(request.getParameter("phonenumber"));
			user.setImagename("null");
			int add_result = new OperateUserServiceImp().addUser(user);
			if(add_result == 1)
			{
				request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
			}else
			{
				System.out.println("新增失败！！！");
				request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
			}
		}catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
