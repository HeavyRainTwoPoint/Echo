package myServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DirectionServlet
 * ��servlet�����û���ҳ����ת
 */
@WebServlet("/DirectionServlet")
public class DirectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		��ͨ����ַ������ʱ��ҳ����ת��404ҳ��
		request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		���ñ��뷽ʽ
		request.setCharacterEncoding("utf-8");
//		��ȡ�û������Լ��û���
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		switch(type)
		{
//		U:update 
		case "U":
//			��ת���޸���Ϣҳ��
			request.setAttribute("name", name);
			request.getRequestDispatcher("/myJsp/updateIndividualUser.jsp").forward(request, response);
			break;
//			�������ʱ�����ظ�����ҳ
		case "BACK":
			request.setAttribute("name",name);
			request.getRequestDispatcher("myJsp/individualUser.jsp").forward(request, response);
			break;
//			���ע��ʱ�����ص�¼ҳ
		case "EXIT":
			request.getSession().invalidate();
			request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
		}
	}

}
