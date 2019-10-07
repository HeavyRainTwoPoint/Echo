package com.rehus.noteSend;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * ������
 * @author lenovo
 *
 */

public class CodeUtil {
	//���������ֱ��������ӳػ�ȡ�����ӵĳ�ʱʱ��
	//�������ӵĳ�ʱʱ��
	//��ȡ���ݵĳ�ʱʱ��
	private RequestConfig config = RequestConfig.custom().setSocketTimeout(1500)
			.setConnectTimeout(1500).setConnectionRequestTimeout(1500)
			.build();  //��Ӵ���
	
	private static CodeUtil util = null;
	private CodeUtil() {}  //˽�л�������
	/**
	 * ����������
	 * @return  ���ض���
	 */
	public synchronized static CodeUtil getInstance() {
		if(util==null) {
			util = new CodeUtil();
		}
		return util;
	}
	public int sendMessage(String id,String key,String number,String contentText) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("Uid", id);
		map.put("Key", key);
		map.put("smsMob", number);
		map.put("smsText", contentText);
		String result = sendHttpPost("http://utf8.api.smschinese.cn/",map,"utf-8");
		return Integer.valueOf(result);
	}
	public String sendHttpPost(String url,Map<String,String> map,String encode) {
		//����post����
		HttpPost post = new HttpPost(url);
		//������������
		List<NameValuePair> ListName = new ArrayList<NameValuePair>();
		for(String key:map.keySet()) {
			ListName.add(new BasicNameValuePair(key,map.get(key)));
		}
		try {
			//������������
			post.setEntity(new UrlEncodedFormEntity(ListName,encode));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(post,encode);
	}
	public String sendHttpPost(HttpPost post,String encode) {
		CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // ����Ĭ�ϵ�httpClientʵ��.  
            httpClient = HttpClients.createDefault();  
            post.setConfig(config);  
            // ִ������
            response = httpClient.execute(post);  
            entity = response.getEntity();  //�յ���Ӧ��ʵ������
            responseContent = EntityUtils.toString(entity, encode);//���յ����ݱ�Ϊ�ַ���
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // �ر�����,�ͷ���Դ  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
	}
	/**
	 * 
	 * @param errorCode  ����״̬��
 	 * @return  ���صĴ�����Ϣ
	 */
	public String getErrorMsg(int errorCode){
	    String content = "";
		if(errorCode==-1){
            content = "û�и��û��˻�";
		}else if(errorCode==-2){
			content = "�ӿ���Կ����ȷ";
		}else if(errorCode==-3){
			content = "������������";
		}else if(errorCode==-4){
            content =  "�ֻ��Ÿ�ʽ����ȷ";
		}else if(errorCode==-21){
            content =  "MD5�ӿ���Կ���ܲ���ȷ";
		}else if(errorCode==-11){
            content =  "���û�������";
		}else if(errorCode==-14){
            content =  "�������ݳ��ַǷ��ַ�";
		}else if(errorCode==-41){
            content =  "�ֻ�����Ϊ��";
		}else if(errorCode==-42){
            content =  "��������Ϊ��";
		}else if(errorCode==-51){
            content =  "����ǩ����ʽ����ȷ";
		}else if(errorCode==-6){
            content =  "IP����";
		}else{
            content =  "δ֪������:"+errorCode;
		}
		return content;
	}
	
	/**
	 * ��ֹ�����л�ʹ�ö��󱻴�������
	 * @return   ���ص�ǰ�Ķ���ʵ��
	 * @throws ObjectStreamException
	 */
	private Object readResolve()throws ObjectStreamException{
		return util;
	}
}
