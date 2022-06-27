package edu.poly.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import edu.poly.common.SessionUtils;

/**
 * Servlet Filter implementation class AuthFitler
 */
@WebFilter(urlPatterns= "/*")
public class AuthFitler extends HttpFilter implements Filter {
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute("isLogin", SessionUtils.isLogin((HttpServletRequest) request));//thiet lap thuoc tinh islogin de phuc vu cac muc tuong ung
		
		chain.doFilter(request, response);
	}


}
