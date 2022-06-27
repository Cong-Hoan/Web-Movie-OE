package edu.poly.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {//ho tro cac pt 
	public static void add(HttpServletRequest request, String name, Object value) {//cho phep luu thuoc tinh sss
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}
	
	public static Object get(HttpServletRequest request, String name) {//truyen vao doi tuong ss
		HttpSession session = request.getSession();
		
		return session.getAttribute(name);
	}
	
	public static void invalidate(HttpServletRequest request) {//huy bo ss
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();//goi thuc hien ivl cua ss
	}
	
	public static boolean isLogin(HttpServletRequest request) {//kiem tra ngd da dn hay chua
		return get(request,"username") != null;// lay gt thuoc tinh username trong ss
	}
	
	public static String getLoginedUsername(HttpServletRequest request) {//goi thuc hien pt get usernaem
		Object username = get(request,"username");
		return username == null? null: username.toString();	
	}
}
