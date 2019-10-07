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
 * 处理注册请求
 * 
 * status可以为0 1 2
 * 如果是1的话就是使用手机号注册的
 * 如果是0的话就是来检测是否有该用户的
 * 如果是2的话就是使用邮箱登录的
 * @author lenovo
 *
 */
@WebServlet("/post.do")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post--->>>>>>>>>>>>>>>>>");
		//要判断是来检验的，还是真正来注册的
		int status = Integer.valueOf(request.getParameter("status"));
		if(status==1) {  //这里的手机注册，主要起检查作用，主要判断数据库存储的验证码是否正确
			//获取注册用户名和密码
			String uname = request.getParameter("Raccount");
			String password = request.getParameter("RPassWord");
			int verification = Integer.parseInt(request.getParameter("Verification"));
			String phone = request.getParameter("Phone");
			//判断其中是否有空值
			boolean flag = CheckIsNull.check(uname,password,verification+"",phone);
			if(flag) {
				return;
			}
			User user = new User();
			user.setPassword(password);
			user.setUname(uname);
			user.setPhoneCode(verification);
			user.setPhone(phone);
			//返回长度0表示注册校验验证码失败，1表示成功
			int length = InsertMessage.insertPhoneOfLoad(user);
			response.getWriter().print(length);
		}else if(status==2){  //使用邮箱注册
			//获取注册用户名和密码
			String uname = request.getParameter("Raccount2");
			String password = request.getParameter("RPassWord2");
			String emailCode = CreateEmailCode.create();  //得到一个邮箱密钥
			String email = request.getParameter("Email");  //得到邮箱
			SendEmail.sendMail(email,emailCode);  //发送邮件
			//判断其中是否有空值
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
		}else if(status==0){  //只是来数据库检测是否会重名
			//获取昵称
			String uname = request.getParameter("Raccount")==null?request.getParameter("Raccount2"):request.getParameter("Raccount");
			//判断其中是否有空值
			boolean flag = CheckIsNull.check(uname);
			if(flag) {
				return;
			}
			User user = new User();
			user.setUname(uname);
			//返回值为3或者4  3表示有重名用户      4表示没有
			int length = SelectMessage.isHaveUser(user);
			response.getWriter().print(length);
		}
	}

}
