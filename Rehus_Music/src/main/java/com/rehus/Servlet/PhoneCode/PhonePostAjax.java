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
 * 点击获取验证码的时候，将用户名等信息插入数据库中
 * @author 小胖五
 *
 */
@WebServlet("/PhonePostAjax.do")
public class PhonePostAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		User user = new User();
		//获取注册用户名和密码
		String uname = req.getParameter("Raccount");
		//获取手机号
		String phone = req.getParameter("phone");
		boolean flag = CheckIsNull.check(uname,phone);
		if(flag) {
			System.out.println("有部分值为空");
			return;
		}
		Send send = new Send(phone);   //发送短信验证码
		int result = send.sendCode();  //获取短信发送的状态
		if(result<=0) {  //判断短信发送的状态，小于零表示发送不成功
			System.out.println("手机验证码由于某种方式错误");
			resp.getWriter().write(result);
			return;
		}
		int PhoneCode = send.code;   //得到手机的验证码
		//将数据存储为User对象(这里不需要直接也将密码插入，防止他点击了获取验证码直接去登录。)
		user.setUname(uname);
		user.setPhone(phone);
		user.setPhoneCode(PhoneCode);
		int length = InsertMessage.insertAjaxPhone(user);
		resp.getWriter().write(PhoneCode);    //返回验证码
	}

}
