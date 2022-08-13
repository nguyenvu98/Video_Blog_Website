package com.poly.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.entity.Video;
import com.poly.service.VideoService;
import com.poly.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = "/index")
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1517115637537021552L;
	private static final int VIDEO_MAX_PAGESIZE = 2;
	private VideoService videoService = new VideoServiceImpl();
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		List<Video> videos = videoService.findAll();
//		req.setAttribute("videos", videos);
//		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/index.jsp");
//		requestDispatcher.forward(req, resp);
//	}
	
	protected void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> countVideo = videoService.findAll();
		int maxPage = (int)Math.ceil(countVideo.size() / (double)VIDEO_MAX_PAGESIZE);
		req.setAttribute("maxPage", maxPage);
		
		List<Video> videos;
		String pageNumber =req.getParameter("page");
		
		if (pageNumber == null) {
			videos = videoService.findAll(1, VIDEO_MAX_PAGESIZE);
			req.setAttribute("currentPage", 1);
		}else {
			videos = videoService.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGESIZE);
			req.setAttribute("currentPage", Integer.valueOf(pageNumber));
		}
		req.setAttribute("videos", videos);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/index.jsp");
		requestDispatcher.forward(req, resp);
	}
}
