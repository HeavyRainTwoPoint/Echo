package com.rehus.Servlet.postLoad;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rehus.DBMessage.InsertMessage;
import com.rehus.DBMessage.SelectMessage;
import com.rehus.emailSend.CreateEmailCode;
import com.rehus.emailSend.SendEmail;

import UserMessage.User;
import checklsNullUtil.CheckIsNull;

/**
 * ����ע������
 * 
 * status����Ϊ0 1 2
 * �����1�Ļ�����ʹ���ֻ���ע���
 * �����0�Ļ�����������Ƿ��и��û���
 * �����2�Ļ�����ʹ�������¼��
 * @author lenovo
 *
 */
@WebServlet("/post.do")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post--->>>>>>>>>>>>>>>>>");
		//Ҫ�ж���������ģ�����������ע���
		int status = Integer.valueOf(request.getParameter("status"));
		if(status==1) {  //������ֻ�ע�ᣬ��Ҫ�������ã���Ҫ�ж����ݿ�洢����֤���Ƿ���ȷ
			//��ȡע���û���������
			String uname = request.getParameter("Raccount");
			String password = request.getParameter("RPassWord");
			int verification = Integer.parseInt(request.getParameter("Verification"));
			String phone = request.getParameter("Phone");
			//�ж������Ƿ��п�ֵ
			boolean flag = CheckIsNull.check(uname,password,verification+"",phone);
			if(flag) {
				return;
			}
			User user = new User();
			user.setPassword(password);
			user.setUname(uname);
			user.setPhoneCode(verification);
			user.setPhone(phone);
			//���س���0��ʾע��У����֤��ʧ�ܣ�1��ʾ�ɹ�
			int length = InsertMessage.insertPhoneOfLoad(user);
			response.getWriter().print(length);
		}else if(status==2){  //ʹ������ע��
			//��ȡע���û���������
			String uname = request.getParameter("Raccount2");
			String password = request.getParameter("RPassWord2");
			String emailCode = CreateEmailCode.create();  //�õ�һ��������Կ
			String email = request.getParameter("Email");  //�õ�����
			SendEmail.sendMail(email,emailCode);  //�����ʼ�
			//�ж������Ƿ��п�ֵ
			boolean flag = CheckIsNull.check(uname,password,emailCode,email);
			if(flag) {
				return;
			}
			User user = new User();
			user.setUname(uname);
			user.setPassword(password);
			user.setEmailCode(emailCode);
			user.setEmail(email);
			int length = InsertMessage.insertEmail(user);
			response.getWriter().print(length);
		}else if(status==0){  //ֻ�������ݿ����Ƿ������
			//��ȡ�ǳ�
			String uname = request.getParameter("Raccount")==null?request.getParameter("Raccount2"):request.getParameter("Raccount");
			//�ж������Ƿ��п�ֵ
			boolean flag = CheckIsNull.check(uname);
			if(flag) {
				return;
			}
			User user = new User();
			user.setUname(uname);
			//����ֵΪ3����4  3��ʾ�������û�      4��ʾû��
			int length = SelectMessage.isHaveUser(user);
			response.getWriter().print(length);
		}
	}

}
