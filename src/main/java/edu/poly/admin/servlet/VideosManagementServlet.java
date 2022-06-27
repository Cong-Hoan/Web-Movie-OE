package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.UploadUtils;
import edu.poly.dao.VideoDao;
import edu.poly.model.Video;


@WebServlet({"/Admin/VideosManagement","/Admin/VideosManagement/create",
	"/Admin/VideosManagement/update","/Admin/VideosManagement/delete",
	"/Admin/VideosManagement/reset","/Admin/VideosManagement/edit"})
@MultipartConfig
public class VideosManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();//lay url la edit
		if(url.contains("edit")) {//kt url chua tp edit goi va edit
			edit(request,response);
			return;
		}
		if(url.contains("delete")) {//kt url chua tp edit goi va delete
			delete(request,response);
			return;
		}
		if(url.contains("reset")) {//kt url chua tp edit goi va reset
			reset(request,response);
			return;
		}
		
		Video video = new Video();//xx tao ra dt video
		video.setPoster("images/Crystal_Project_computer.png");//ht ha trong muc ima
		
		findAll(request,response);
		request.setAttribute("video", video);//thiet lap tt video
		
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//hien thi view
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		//thuc hien so sang
		if(url.contains("create")) {//goi th pt tao 
			create(request,response);
			return;
		}
		if(url.contains("delete")) {
			create(request,response);
			return;
		}
		if(url.contains("update")) {
			update(request,response);
			return;
		}
		if(url.contains("reset")) {
			reset(request,response);
			return;
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoId");//lay tt video id
		
		if(id == null) {
			request.setAttribute("error", "Video id is required");//tb cho nd can truyen vao id
			PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}
		
		try {
			VideoDao dao = new VideoDao();//tao ra dt video
			Video video = dao.findById(id);//timkiemvideo theo id 
			
			if(video == null) {
				request.setAttribute("error", "Video id not  found!");//neu o tim thay video
				PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
				return;
			}
			
			dao.delete(id);//goi th pt video dao 
			request.setAttribute("message","video is deleted!");//tb cho nd video da dc xoa
			request.setAttribute("video", new Video());//tl ht tren fomr rong
			
			findAll(request,response);// ht tat ca video hien co 
		}catch(Exception e) {
			e.printStackTrace();//in ra loi 
			
			request.setAttribute("error", "Error:" + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//ht lai view
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();//cho phep cap nhat tt video 
		
		try {
			BeanUtils.populate(video, request.getParameterMap());//do tt nd nhap vao tren form
			
			VideoDao dao = new VideoDao();//khai bao lop dao 
			Video oldVideo = dao.findById(video.getVideoId());
			
			if(request.getPart("cover").getSize()==0) {// hinh anh cu 
				video.setPoster(oldVideo.getPoster());
			}else {
				video.setPoster("uploads/" + UploadUtils.processUploadField("cover", request, "/uploads", video.getVideoId()));
			}
			
			dao.update(video);//goi thuc hien pt update
			
			request.setAttribute("video", video);//hien thi lai thong tini
			request.setAttribute("message", "Video is update!");//dua ra tb
			findAll(request,response);
		}catch(Exception e) {
			e.printStackTrace();//in ra loi 
			
			request.setAttribute("error", "Error:" + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//cho phep ht view sau khi cap nhat
	}
	
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			
			
			VideoDao dao = new VideoDao();
			
			List<Video> list = dao.findAll();
			
			request.setAttribute("videos", list);
		}catch(Exception e) {
			e.printStackTrace();
			
			request.setAttribute("error", "Error:" + e.getMessage());
		}
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoId");//lay id nd dat toi 
		
		if(id == null) {//neu id = null nap view
			request.setAttribute("error", "Video id is required");
			PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}
		
		try {
			VideoDao dao = new VideoDao();//tao ra dt dao
			Video video = dao.findById(id);
			
			request.setAttribute("video", video);//thiet lap tt dt video
			findAll(request,response);//hien thi tt
			
		}catch(Exception e) {
			e.printStackTrace();
			
			request.setAttribute("error", "Error:" + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}
	
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();//tao ra dt video
		video.setPoster("images/Crystal_Project_computer.png");//tl hinh anh ngam dinh 
		request.setAttribute("video", video);//tl tt video
		findAll(request,response);// lay dl tu csdl 
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//va hien thi views
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();//khai bao dt lop video
		
		try {
			BeanUtils.populate(video, request.getParameterMap());//do du lieu vao video
			
			video.setPoster("uploads/" + UploadUtils.processUploadField("cover", request, "/uploads",video.getVideoId()));
			//thiet lap gt cua poster
			VideoDao dao = new VideoDao();//khai bao lop video dao
			dao.insert(video);//goi thuc hien pt insert
			
			request.setAttribute("video", video);//thiet lap tt video cho view
			request.setAttribute("message", "Video is inserted!");//tl thong bao
		}catch(Exception e) {
			e.printStackTrace();//in tt loi 
			request.setAttribute("error", "Error:" + e.getMessage());//ht tb loi 
		}
		
		findAll(request,response);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//ht lai nd view 
	}

}
