package com.rehus.emailSend;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SendEmail {
	/**
	 * 
	 * @param to   //接收者的邮箱
	 * @param code  //激活码
	 */
	public static void sendMail(String to,String code) {
		Properties pro = System.getProperties();  //获取系统的默认设置
		//连接到主机号
		pro.put("mail.smtp.host","smtp.qq.com");
		//连接到主机的端口号
		pro.put("mail.smtp.port",25);
		//发送邮件是否以授权的方式去访问邮件
		pro.put("mail.smtp.auth","true");
		//创建session对象
		Session session = Session.getInstance(pro,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("878128414@qq.com","btrgikefbjgnbfcj");  //设置一下用户名和密钥
			}
		});
		//创建邮件对象
		Message message = new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress("878128414@qq.com"));
			//下面这三个都是设置了HTTP协议的请求行
			//设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//设置文件主题
			message.setSubject("来自rehus网站的邮件");
			//设置文件的正文
			message.setContent("<h1>来自rehus网站的邮件</h1><h3><a href='http://localhost:8080/test-Email11/active?code="+code+"'>http://localhost:8080/rehus/active?code="+code+"</a></h3>","text/html;charset=utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
