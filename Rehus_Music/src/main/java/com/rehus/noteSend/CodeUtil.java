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
 * 工具类
 * @author lenovo
 *
 */

public class CodeUtil {
	//下面三个分别设置连接池获取到连接的超时时间
	//建立连接的超时时间
	//获取数据的超时时间
	private RequestConfig config = RequestConfig.custom().setSocketTimeout(1500)
			.setConnectTimeout(1500).setConnectionRequestTimeout(1500)
			.build();  //添加代理
	
	private static CodeUtil util = null;
	private CodeUtil() {}  //私有化构造器
	/**
	 * 创建单例类
	 * @return  返回对象
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
		//设置post请求
		HttpPost post = new HttpPost(url);
		//创建参数队列
		List<NameValuePair> ListName = new ArrayList<NameValuePair>();
		for(String key:map.keySet()) {
			ListName.add(new BasicNameValuePair(key,map.get(key)));
		}
		try {
			//设置请求主体
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
            // 创建默认的httpClient实例.  
            httpClient = HttpClients.createDefault();  
            post.setConfig(config);  
            // 执行请求
            response = httpClient.execute(post);  
            entity = response.getEntity();  //收到响应的实体内容
            responseContent = EntityUtils.toString(entity, encode);//将收到内容变为字符串
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // 关闭连接,释放资源  
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
	 * @param errorCode  错误状态码
 	 * @return  返回的错误信息
	 */
	public String getErrorMsg(int errorCode){
	    String content = "";
		if(errorCode==-1){
            content = "没有该用户账户";
		}else if(errorCode==-2){
			content = "接口密钥不正确";
		}else if(errorCode==-3){
			content = "短信数量不足";
		}else if(errorCode==-4){
            content =  "手机号格式不正确";
		}else if(errorCode==-21){
            content =  "MD5接口密钥加密不正确";
		}else if(errorCode==-11){
            content =  "该用户被禁用";
		}else if(errorCode==-14){
            content =  "短信内容出现非法字符";
		}else if(errorCode==-41){
            content =  "手机号码为空";
		}else if(errorCode==-42){
            content =  "短信内容为空";
		}else if(errorCode==-51){
            content =  "短信签名格式不正确";
		}else if(errorCode==-6){
            content =  "IP限制";
		}else{
            content =  "未知错误码:"+errorCode;
		}
		return content;
	}
	
	/**
	 * 防止反序列化使得对象被创建两个
	 * @return   返回当前的对象实例
	 * @throws ObjectStreamException
	 */
	private Object readResolve()throws ObjectStreamException{
		return util;
	}
}
