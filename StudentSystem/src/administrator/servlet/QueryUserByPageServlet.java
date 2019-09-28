package administrator.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administrator.Service.imp.QueryUserByPageServiceImp;
import myEntity.PageBean;
import myEntity.User;

@WebServlet("/QueryUserByPageServlet")
public class QueryUserByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		int currentPage;
		String page = request.getParameter("currentPage");
		if(page.equals(null))
		{
			page = "1";
		}
		currentPage = Integer.parseInt(page);
		int pageSize = 5;
		QueryUserByPageServiceImp queryUserByPageService = new QueryUserByPageServiceImp();
		int userCount = queryUserByPageService.getUserCount();
		List<User> userList = queryUserByPageService.getCurrentUserList(currentPage, pageSize);
		PageBean pageBean = new PageBean(currentPage,pageSize,userCount,userList);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/administratorJsp/showAllUser.jsp").forward(request, response);
	}

}
