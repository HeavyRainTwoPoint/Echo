package com.rehus.noteSend;

public class Send {
	//�û���
	private final static String id = "��������";
	//���Ͷ��ŵ�����
	private final static String key = "d41d8cd98f00b204e980";
	//��Ҫ����ĵ绰����
	private String number;
	//��Ҫ������ı�
	private String contentText;
	//��֤��
	public int code = 0;
	
	public Send(String number) {
		this.number = number;
		code = CreateCode.create();
		this.contentText = "������"+number+"��֤�룺"+code;
	}
	public int sendCode() {
		CodeUtil code = CodeUtil.getInstance();  //�õ���������
		int result = code.sendMessage(id,key,number,contentText);
		return result;
	}
}