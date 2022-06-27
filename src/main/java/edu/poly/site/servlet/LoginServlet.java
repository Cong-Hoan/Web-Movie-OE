package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDao;
import edu.poly.domain.LoginForm;
import edu.poly.model.User;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = CookieUtils.get("username", request);//lay thong tin cua cookie
		
		if(username == null) {//neu cookie username da ton tai 
			PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
			return;
		}
		SessionUtils.add(request, "username", username);//se tiep tuc luu thong tin username
		
		request.getRequestDispatcher("/Homepage").forward(request, response);//chuyen den trang username
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			LoginForm form = new LoginForm();//khai bao doi tuong login form
			
			BeanUtils.populate(form, request.getParameterMap());//pt populate de do du lieu
			
			UserDao dao = new UserDao();//tao ra userdao
			User user = dao.findById(form.getUsername());//tim kiem thong tin user
			
			if(user != null && user.getPassword().equals(form.getPassword())) {//ss neu tim thay doi tuong user
				SessionUtils.add(request, "username", user.getUsername());//sau khi nhap cac thong tin tao ra doi tuong ss
				
				if(form.isRemember()) {//neu nguoi dung kich remmember co time 24h 
					CookieUtils.add("username", form.getUsername(), 24, response);
				}else {//nguoc lai xoa cookie username
					CookieUtils.add("username", form.getUsername(), 0, response);
				}
				request.setAttribute("isLogin", true);
				request.setAttribute("name", user.getFullname());//thiet lap ten day du username
				request.getRequestDispatcher("/Homepage").forward(request, response);//chuyen va goi thanh cong den tran homepage
				return;
			}
			request.setAttribute("error", "invalid username or password");
			
		}catch (Exception e){
			request.setAttribute("error",e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
	}

}
