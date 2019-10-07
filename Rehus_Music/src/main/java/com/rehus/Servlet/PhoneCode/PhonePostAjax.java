package com.rehus.Servlet.PhoneCode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rehus.DBMessage.InsertMessage;
import com.rehus.noteSend.Send;

import UserMessage.User;
import checklsNullUtil.CheckIsNull;

/**
 * �����ȡ��֤���ʱ�򣬽��û�������Ϣ�������ݿ���
 * @author С����
 *
 */
@WebServlet("/PhonePostAjax.do")
public class PhonePostAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		User user = new User();
		//��ȡע���û���������
		String uname = req.getParameter("Raccount");
		//��ȡ�ֻ���
		String phone = req.getParameter("phone");
		boolean flag = CheckIsNull.check(uname,phone);
		if(flag) {
			System.out.println("�в���ֵΪ��");
			return;
		}
		Send send = new Send(phone);   //���Ͷ�����֤��
		int result = send.sendCode();  //��ȡ���ŷ��͵�״̬
		if(result<=0) {  //�ж϶��ŷ��͵�״̬��С�����ʾ���Ͳ��ɹ�
			System.out.println("�ֻ���֤������ĳ�ַ�ʽ����");
			resp.getWriter().write(result);
			return;
		}
		int PhoneCode = send.code;   //�õ��ֻ�����֤��
		//�����ݴ洢ΪUser����(���ﲻ��Ҫֱ��Ҳ��������룬��ֹ������˻�ȡ��֤��ֱ��ȥ��¼��)
		user.setUname(uname);
		user.setPhone(phone);
		user.setPhoneCode(PhoneCode);
		int length = InsertMessage.insertAjaxPhone(user);
		resp.getWriter().write(PhoneCode);    //������֤��
	}

}
