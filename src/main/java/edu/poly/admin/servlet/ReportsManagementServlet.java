package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.FavoriteDao;
import edu.poly.dao.VideoDao;
import edu.poly.domain.FavoriteReport;
import edu.poly.domain.FavoriteUserReport;
import edu.poly.model.Video;

/**
 * Servlet implementation class ReportsManagementServlet
 */
@WebServlet("/ReportsManagementServlet")
public class ReportsManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reportFavoritesByVideos(request,response);
		reportFavoriteUserByVideo(request,response);
		
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void reportFavoriteUserByVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String videoUserId = request.getParameter("videoUserId");
			
			VideoDao vdao = new VideoDao();
			List<Video> vList = vdao.findAll();
			
			if(videoUserId == null && vList.size()>0) {
				videoUserId = vList.get(0).getVideoId();
			}
			
			FavoriteDao dao = new FavoriteDao();
			List<FavoriteUserReport> list = dao.reportFavoriteUsersByVideo(videoUserId);
			
			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vList);
			request.setAttribute("favUsers", list);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error","Error: " + e.getMessage());
		}
	}
	
	protected void reportFavoritesByVideos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FavoriteDao dao = new FavoriteDao();//khai bao favorite
			List<FavoriteReport> list = dao.reportFavoritesByVideos();//thuchienpt khai bao
			
			request.setAttribute("favList", list);//lay dl truyen dl sang views
		}catch(Exception e) {
			e.printStackTrace();//in ra tb loi
			request.setAttribute("error","Error: " + e.getMessage());
		}
	}

}
