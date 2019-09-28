package myServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myEntity.User;
import myService.imp.RegisterNewUserServiceImp;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
//		��ȡע����Ϣ
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		String profession = request.getParameter("profession");
		String phonenumber = request.getParameter("phonenumber");
		String hobby = request.getParameter("hobby");
		String password = request.getParameter("password");
//		��װ
		User user = new User();
		user.setName(name);
		user.setSex(sex);
		user.setAge(age);
		user.setProfession(profession);
		user.setPhonenumber(phonenumber);
		user.setHobby(hobby);
		user.setPassword(password);
//		�ж��Ƿ��ύ��Ϣ�ɹ�
		boolean result = new RegisterNewUserServiceImp().registerNewUser(user);
		if(result)
		{
			request.setAttribute("message", "ע����Ϣ�Ѿ��ύ�ɹ���");
			request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("message", "�ύʧ�ܣ���Ҫ����ע�ᣡ");
			request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
		}
	}

}
