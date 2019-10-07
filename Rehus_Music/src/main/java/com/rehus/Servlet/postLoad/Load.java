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
 * 处理登录请求
 * status可以为0可以为1
 * 0表示是使用邮箱登录的
 * 1表示是使用手机号登录的
 * @author lenovo
 *
 */

@WebServlet("/load.do")
public class Load extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		int status = Integer.valueOf(req.getParameter("status"));
		//检测传入值是否为空
		boolean flag = CheckIsNull.check(req.getParameter("SingIn"),req.getParameter("PassWord"));
		if(flag) {   //如果其中之一为空，就直接返回
			return;
		}
		User user = new User();
		if(status==1) {  //使用手机号登录的话，就直接查看手机号和密码是否一致就行了
			String phone = req.getParameter("SingIn");  //获取用户名
			String password = req.getParameter("PassWord"); //获取密码
			user.setPhone(phone);
			user.setPassword(password);
			//检测手机号和密码,返回值为0或者1 0表示没有该用户，1表示登录成功
			int length = SelectMessage.selectPhoneInDB(user);
			resp.getWriter().print(length);
			return;
		}else if(status==0) {
			String email = req.getParameter("SingIn");  //获取用户名
			String password = req.getParameter("PassWord"); //获取密码
			user.setEmail(email);
			user.setPassword(password);
			//检测邮箱和密码，返回值为0，1，2 返回0表示没有该用户 返回2表示邮箱还没有激活 返回1表示登录成功
			int length = SelectMessage.selectEmailInDB(user);
			resp.getWriter().print(length);
			return;
		}
	}
}
