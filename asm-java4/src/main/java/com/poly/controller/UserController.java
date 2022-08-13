package com.poly.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.constant.SessionAttr;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.UserService;
import com.poly.service.VideoService;
import com.poly.service.impl.UserServiceImpl;
import com.poly.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = {"/login", "/logout", "/register"})
public class UserController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1517115637537021552L;
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		String path = req.getServletPath();
		switch (path) {
		case "/login":
			doGetLogin( req, resp);
			break;
		case "/register":
			doGetRegister( req, resp);
			break;
		case "/logout":
			doGetLogout(session, req, resp);
			break;
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/login":
			doPostLogin(session,req, resp);
			break;
		case "/register":
			doPostLogin(session,req, resp);
			break;
		}
	}

	protected void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/login.jsp");
		requestDispatcher.forward(req, resp);
	}

	protected void doPostLogin(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = userService.login(username, password);
		if (user != null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		}else {
			resp.sendRedirect("login");
		}	
	}
	
	protected void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/register.jsp");
		requestDispatcher.forward(req, resp);
	}

	protected void doPostRegister(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		User user = userService.create(username, password, email);
		if (user != null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		}else {
			resp.sendRedirect("register");
		}	
	}
	
	protected void doGetLogout(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		session.removeAttribute(SessionAttr.CURRENT_USER);
		resp.sendRedirect("index");
	}

}
