package com.rehus.noteSend;

public class Send {
	//用户民
	private final static String id = "大雨两点";
	//发送短信的密银
	private final static String key = "d41d8cd98f00b204e980";
	//需要传输的电话号码
	private String number;
	//需要传输的文本
	private String contentText;
	//验证码
	public int code = 0;
	
	public Send(String number) {
		this.number = number;
		code = CreateCode.create();
		this.contentText = "哈哈哈"+number+"验证码："+code;
	}
	public int sendCode() {
		CodeUtil code = CodeUtil.getInstance();  //得到单例对象
		int result = code.sendMessage(id,key,number,contentText);
		return result;
	}
}