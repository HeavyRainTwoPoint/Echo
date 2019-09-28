package myServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DirectionServlet
 * 该servlet负责用户的页面跳转
 */
@WebServlet("/DirectionServlet")
public class DirectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		当通过地址栏访问时将页面跳转到404页面
		request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		设置编码方式
		request.setCharacterEncoding("utf-8");
//		获取用户操作以及用户名
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		switch(type)
		{
//		U:update 
		case "U":
//			跳转至修改信息页面
			request.setAttribute("name", name);
			request.getRequestDispatcher("/myJsp/updateIndividualUser.jsp").forward(request, response);
			break;
//			点击返回时，返回个人主页
		case "BACK":
			request.setAttribute("name",name);
			request.getRequestDispatcher("myJsp/individualUser.jsp").forward(request, response);
			break;
//			点击注销时，返回登录页
		case "EXIT":
			request.getSession().invalidate();
			request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
		}
	}

}
