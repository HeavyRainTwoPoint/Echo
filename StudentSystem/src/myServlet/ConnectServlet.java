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
		//�����¼
		String loginUser = request.getParameter("loginUser");
		if(loginUser.equals("��ͨ�û�"))
		{
			this.generalLogin(request,response);
		}
//		����Ա
		else
		{
			this.administrator(request, response);
		}
	}
	//��ͨ�û���¼��֤����
	private void generalLogin(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("userName");
		String password = request.getParameter("userPassword");
		User user = new User(name,password);
//		��ѯ���ݿ����Ƿ񱣴����û���Ϣ
		int flag = new LoginJudgeDao().login(user,false);
		if(flag == 1) 
		{
			try 
			{
//				��֤ͨ��  ��name����request���з���
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
//		��֤��ͨ��
		else if(flag == 0)
		{
			request.setAttribute("message", "�û������ڣ�");
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
			request.setAttribute("message","ϵͳ�쳣����");
			try {
				request.getRequestDispatcher("/myJsp/login.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
//	����Ա����
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
//				��ת�������ҳservlet��
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
			request.setAttribute("message", "�û������ڣ�");
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
			request.setAttribute("message","ϵͳ�쳣����");
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
