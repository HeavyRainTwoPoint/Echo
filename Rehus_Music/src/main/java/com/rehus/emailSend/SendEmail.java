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
	 * @param to   //�����ߵ�����
	 * @param code  //������
	 */
	public static void sendMail(String to,String code) {
		Properties pro = System.getProperties();  //��ȡϵͳ��Ĭ������
		//���ӵ�������
		pro.put("mail.smtp.host","smtp.qq.com");
		//���ӵ������Ķ˿ں�
		pro.put("mail.smtp.port",25);
		//�����ʼ��Ƿ�����Ȩ�ķ�ʽȥ�����ʼ�
		pro.put("mail.smtp.auth","true");
		//����session����
		Session session = Session.getInstance(pro,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("878128414@qq.com","btrgikefbjgnbfcj");  //����һ���û�������Կ
			}
		});
		//�����ʼ�����
		Message message = new MimeMessage(session);
		try {
			//���÷�����
			message.setFrom(new InternetAddress("878128414@qq.com"));
			//��������������������HTTPЭ���������
			//�����ռ���
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//�����ļ�����
			message.setSubject("����rehus��վ���ʼ�");
			//�����ļ�������
			message.setContent("<h1>����rehus��վ���ʼ�</h1><h3><a href='http://localhost:8080/test-Email11/active?code="+code+"'>http://localhost:8080/rehus/active?code="+code+"</a></h3>","text/html;charset=utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//�����ʼ�
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
