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
 * ������µ�ǰ�û�������Ϣ��servlet
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
//		���û�����޸�ʱ����ȡ�޸ĺ��ֵ
		String name = request.getParameter("name");
		String newSex = request.getParameter("sex");
		int newAge = Integer.parseInt(request.getParameter("age"));
		String newProfession = request.getParameter("profession");
		String newPhonenumber = request.getParameter("phonenumber");
		String newHobby = request.getParameter("hobby");
//		��װ
		User user = new User();
		user.setName(name);
		user.setSex(newSex);
		user.setAge(newAge);
		user.setProfession(newProfession);
		user.setPhonenumber(newPhonenumber);
		user.setHobby(newHobby);
//		�����ݿ���Ϣ���и���
		UpdateIndividualUserServiceImp updateSimpleUser = new UpdateIndividualUserServiceImp();
		boolean result = updateSimpleUser.updateUser(user);
//		��������Ƿ�ɹ�
		if(result)
		{
			request.setAttribute("name", name);
			request.getRequestDispatcher("/myJsp/individualUser.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("name", name);
			request.setAttribute("message", "�޸ĸ�����Ϣʧ�ܣ������ԣ�");
			request.getRequestDispatcher("/myJsp/error.jsp").forward(request, response);
			System.out.println("�޸�ʧ�ܣ�����");
		}
	}

}
