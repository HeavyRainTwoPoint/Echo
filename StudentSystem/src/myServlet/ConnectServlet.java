package myServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myEntity.User;
import myServerDao.LoginJudgeDao;

@WebServlet("/ConnectServlet")
public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//处理登录
		String loginUser = request.getParameter("loginUser");
		if(loginUser.equals("普通用户"))
		{
			this.generalLogin(request,response);
		}
//		管理员
		else
		{
			this.administrator(request, response);
		}
	}
	//普通用户登录验证方法
	private void generalLogin(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("userName");
		String password = request.getParameter("userPassword");
		User user = new User(name,password);
//		查询数据库中是否保存有用户信息
		int flag = new LoginJudgeDao().login(user,false);
		if(flag == 1) 
		{
			try 
			{
//				验证通过  将name放在request域中返回
				request.setAttribute("name",name);
				request.getRequestDispatcher("/myJsp/individualUser.jsp").forward(request, response);
			} catch (ServletException e)
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
//		验证不通过
		else if(flag == 0)
		{
			request.setAttribute("message", "用户不存在！");
			try 
			{
				request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
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
		else
		{
			request.setAttribute("message","系统异常！！");
			try {
				request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
//	管理员连接
	private void administrator(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("userName");
		String password = request.getParameter("userPassword");
		User user = new User(name,password);
		int flag = new LoginJudgeDao().login(user,true);
		if(flag == 1) 
		{
			try 
			{
//				跳转到负责分页servlet中
				request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
			} catch (ServletException e)
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else if(flag == 0)
		{
			request.setAttribute("message", "用户不存在！");
			try 
			{
				request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
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
		else
		{
			request.setAttribute("message","系统异常！！");
			try {
				request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
