package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDao;
import edu.poly.model.User;


@WebServlet("/EditProfile")
public class EditProfileServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);//doc tt username nguoidung dang nhap
		
		if (username == null) {//kt neu username = null chua dang vao ht
			request.getRequestDispatcher("/Login").forward(request, response);//fw toi trang login
			return;
		}
		try {
			UserDao dao = new UserDao();
			User user = dao.findById(username);//dnthanhcongtimkiemtheousernametraveuser
			
			request.setAttribute("user", user);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());//su dug pt de lay tt do nd gui toi tu form 
			
			String username = SessionUtils.getLoginedUsername(request);
			UserDao dao = new UserDao();
			User oldUser = dao.findById(username);//tim kiem username 0 csdl
			
			user.setAdmin(oldUser.getAdmin());// khong duoc chinh sua thay doi 
			dao.update(user);//goi tt update user
			request.setAttribute("message", "User profile update!!!");//dua ra tb
			request.setAttribute("user", user);//ht lai tt
		}catch(Exception e) {
			e.printStackTrace();//in ra loi 
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}

}
