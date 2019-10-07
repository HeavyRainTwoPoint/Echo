package com.rehus.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class LoadFilter
 */
@WebFilter("/Load")
public class LoadFilter implements Filter {

    public LoadFilter() {
    }
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String uname = request.getParameter("SingIn");
		int status = 1;
		//�жϵ�¼��һ����ʹ�����仹���ֻ��ŵ�¼��  statusΪ1��ʾ�ֻ��� statusΪ0��ʾ����
		int index = uname.indexOf("@");
		if(index>=0) {
			status = 0;
		}
		request.setAttribute("status",status);  //�������
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
