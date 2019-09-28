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
 * �������Ա��ɾ�Ĳ顢��˵�servlet
 *
 */
@WebServlet("/OperateServlet")
public class OperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/myJsp/lost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		���ñ��뷽ʽ
		request.setCharacterEncoding("utf-8");
//		��ȡ��Ҫ�����Ķ�����  
		String name = request.getParameter("name");
//		����������
		String type = request.getParameter("type");
//		switch�ṹ������Ӧ������ʵ�ֻ���ҳ����ת
		switch(type)
		{
//		SM����ʾ����ϸ����Ϣ
		case "SM":
//			�÷�������ת���޸��û���Ϣ��ҳ��   name�����޸���Ϣ���û���
			this.showMoreContext(request, response, name);
			break;
//			U��Update�޸�
		case "U":
//			ͨ���÷�����name�µĸ�����Ϣ���и���
			this.update(request, response, name);
			break;
//			D��Deleteɾ��
		case "D":
//			�÷����Ὣname��Ӧ�ļ�¼�����ݿ���ɾ��
			this.delete(request, response, name);
			break;
//			SR:չʾ����˵����û�
		case "SR":
//			�÷�������ת���鿴ע���û���ҳ��
			this.showRegisterUser(request, response);
			break;
//			P:Passͨ�����
		case "P":
//			�÷�����name�µ���Ϣ���浽���ݿ���
			this.auditReginsterUser(request, response, name);
			break;
//			NP����ͨ�����
		case "NP":
//			��name�µ���Ϣ��ע���¼��ɾȥ
			AuditRegisterUserServiceImp operator = new AuditRegisterUserServiceImp();
			int delete_result = operator.deleteRegisterUser(name);
			if(delete_result == 1)
			{
				System.out.println("�Ѿ�ɾ����ͨ����ע���ߣ�"+name);
				List<User> userlist = new AuditRegisterUserServiceImp().getRegisterUser();
//				�����˵����������һ�������֮����ת������ҳ��
//				����������
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
//			QUB:��תҳ�浽����������ҳ��
		case "QUB":
			this.turnToQuery(request, response);
			break;
//			SEARCH:����
		case "SEARCH":
//			������ɸѡ����
			this.getUserWithLimit(request,response);
			break;
//			������ҳ
		case"R":
			this.backToPageOne(request,response);
			break;
		case "A":
			request.getRequestDispatcher("/administratorJsp/addUser.jsp").forward(request, response);
			break;
		case "ADD":
			this.addUser(request,response);
			break;
//			ע����¼�����ص�¼ҳ
		case "EXIT":
			this.exit(request,response);
			break;
		}
	
	}
	
//	������ת��չʾ��ϸ��Ϣҳ��    name���û�������Ϊ��ѯ����
	private void showMoreContext(HttpServletRequest request,HttpServletResponse response,String name)
	{
//		��ȡ���ݣ�
		QueyIndividualUserServiceImp queyIndividualUser = new QueyIndividualUserServiceImp();
		User user = queyIndividualUser.getUser(name);
		try 
		{
//			��Javabean�������request����
			request.setAttribute("user", user);
//			��תҳ��
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

//	���������Ϣ�ķ���
	private void update(HttpServletRequest request,HttpServletResponse response,String name)
	{
		User user = new User();
//		��ǰ��ҳ���ȡ����Ϣ�����з�װ
		user.setName(request.getParameter("name"));
		user.setSex(request.getParameter("sex"));
		user.setAge(Integer.parseInt(request.getParameter("age")));
		user.setProfession(request.getParameter("profession"));
		user.setPhonenumber(request.getParameter("phonenumber"));
		user.setHobby(request.getParameter("hobby"));
		user.setPassword(request.getParameter("password"));
//		ִ�и���
		int update_result = new OperateUserServiceImp().updateUser(user);
		if(update_result == 1)
		{
//			���³ɹ�������ҳ
			try {
				request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
				System.out.println("���ݸ����쳣������");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("δ֪�쳣������");
			}	
		}
		else
		{
			System.out.println("�޸�ʧ�ܣ���");
		}
	}
//	ɾ������  name���û�������Ϊ��ѯ����
	private void delete(HttpServletRequest request,HttpServletResponse response,String name)
	{
		int delete_result = new OperateUserServiceImp().deleteUser(name);
		if(delete_result == 1)
		{
//			ɾ���ɹ�����ת����ҳ��ˢ��
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
			System.out.println("ɾ��ʧ�ܣ���");
		}
	}
//	չʾ����˵��û�
	private void showRegisterUser(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			List<User> userlist = new AuditRegisterUserServiceImp().getRegisterUser();
//			���û�д���˵��û�������ת
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
//	��ͨ����˵��û���Ϣ��������   name���û�������Ϊ��ѯ����
	private void auditReginsterUser(HttpServletRequest request,HttpServletResponse response,String name)
	{
//		����һ��AuditRegisterUserServiceImp���������Ϣ�����Լ�ɾ��
		AuditRegisterUserServiceImp operator = new AuditRegisterUserServiceImp();
		int result = operator.auditRegisterUser(operator.getRegisterUser(name));
		if(result == 1)
		{
			try
			{
				int delete_result = operator.deleteRegisterUser(name);
				if(delete_result == 1 )
				{
					System.out.println("ɾ����һ����¼����");
				}
//				������������������ת�����ҳ�棬����ص���ҳ
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
//	ע����¼
	private void exit(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
//			��session��Ч��
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
//		��ת����������ѯҳ��
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
//	���û���������ݷ�װ������ģ����ѯ������ת��ʾ���
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
//				�޷������������ݵ���ת
				request.setAttribute("data", "");
				request.getRequestDispatcher("/administratorJsp/QueryUserByLimit.jsp").forward(request, response);
			}
			else
			{
//				�з������������ݵ���ת
				request.setAttribute("userlist", userlist);
				request.getRequestDispatcher("/administratorJsp/showQueryResult.jsp").forward(request, response);
			}
		}catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
//	������ҳ
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
//	����³�Ա
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
				System.out.println("����ʧ�ܣ�����");
				request.getRequestDispatcher("/QueryUserByPageServlet?currentPage=1").forward(request, response);
			}
		}catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
