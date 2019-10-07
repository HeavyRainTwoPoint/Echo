package com.rehus.Servlet.postLoad;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rehus.DBMessage.SelectMessage;

import UserMessage.User;
import checklsNullUtil.CheckIsNull;

/**
 * �����¼����
 * status����Ϊ0����Ϊ1
 * 0��ʾ��ʹ�������¼��
 * 1��ʾ��ʹ���ֻ��ŵ�¼��
 * @author lenovo
 *
 */

@WebServlet("/load.do")
public class Load extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		int status = Integer.valueOf(req.getParameter("status"));
		//��⴫��ֵ�Ƿ�Ϊ��
		boolean flag = CheckIsNull.check(req.getParameter("SingIn"),req.getParameter("PassWord"));
		if(flag) {   //�������֮һΪ�գ���ֱ�ӷ���
			return;
		}
		User user = new User();
		if(status==1) {  //ʹ���ֻ��ŵ�¼�Ļ�����ֱ�Ӳ鿴�ֻ��ź������Ƿ�һ�¾�����
			String phone = req.getParameter("SingIn");  //��ȡ�û���
			String password = req.getParameter("PassWord"); //��ȡ����
			user.setPhone(phone);
			user.setPassword(password);
			//����ֻ��ź�����,����ֵΪ0����1 0��ʾû�и��û���1��ʾ��¼�ɹ�
			int length = SelectMessage.selectPhoneInDB(user);
			resp.getWriter().print(length);
			return;
		}else if(status==0) {
			String email = req.getParameter("SingIn");  //��ȡ�û���
			String password = req.getParameter("PassWord"); //��ȡ����
			user.setEmail(email);
			user.setPassword(password);
			//�����������룬����ֵΪ0��1��2 ����0��ʾû�и��û� ����2��ʾ���仹û�м��� ����1��ʾ��¼�ɹ�
			int length = SelectMessage.selectEmailInDB(user);
			resp.getWriter().print(length);
			return;
		}
	}
}
