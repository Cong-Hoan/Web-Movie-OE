package edu.poly.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	public static String get(String name, HttpServletRequest request) {//lay gia tri cookie
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for (Cookie cookie : cookies) { //su dung vong lap vo ich
				if(cookie.getName().equals(name)) {// bang voi ten truyen vao 
					return cookie.getValue();// tra ve ten cookie
				}
			}
		}
		return null;// tim khong thay thi tra ve null
	}
	public static Cookie add(String name, String value, int hours, HttpServletResponse response) {//luu gia tri cookie
		Cookie cookie = new Cookie(name,value);// tao ra cac doi tuong cookie 
		cookie.setMaxAge(60 * 60 * hours);//thiet lap thoi gian ton tai cho cookie
		cookie.setPath("/");// thiet lap duong dan goc de luu cookie 
		response.addCookie(cookie);
		
		return cookie;// tra lai thong tin cookie de luu doi tuong cookie
	}
}
