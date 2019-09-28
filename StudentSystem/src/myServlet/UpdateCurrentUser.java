package myServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myEntity.User;
import myService.imp.UpdateIndividualUserServiceImp;
/**
 * 
 * 负责更新当前用户个人信息的servlet
 *
 */
@WebServlet("/UpdateCurrentUser")
public class UpdateCurrentUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		当用户点击修改时，获取修改后的值
		String name = request.getParameter("name");
		String newSex = request.getParameter("sex");
		int newAge = Integer.parseInt(request.getParameter("age"));
		String newProfession = request.getParameter("profession");
		String newPhonenumber = request.getParameter("phonenumber");
		String newHobby = request.getParameter("hobby");
//		封装
		User user = new User();
		user.setName(name);
		user.setSex(newSex);
		user.setAge(newAge);
		user.setProfession(newProfession);
		user.setPhonenumber(newPhonenumber);
		user.setHobby(newHobby);
//		对数据库信息进行更新
		UpdateIndividualUserServiceImp updateSimpleUser = new UpdateIndividualUserServiceImp();
		boolean result = updateSimpleUser.updateUser(user);
//		检验更新是否成功
		if(result)
		{
			request.setAttribute("name", name);
			request.getRequestDispatcher("/myJsp/individualUser.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("name", name);
			request.setAttribute("message", "修改个人信息失败！请重试！");
			request.getRequestDispatcher("/myJsp/error.jsp").forward(request, response);
			System.out.println("修改失败！！！");
		}
	}

}
