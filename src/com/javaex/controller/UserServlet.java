package com.javaex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.utill.Webutill;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count=0;
		request.setCharacterEncoding("UTF-8");
		String actionName=request.getParameter("a");

		if("joinform".equals(actionName)) {
			Webutill.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
			
		} else if("join".equals(actionName)) {
			String name= request.getParameter("name");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String gender=request.getParameter("gender");
			UserVo vo=new UserVo(name,email,password,gender);
			
			UserDao dao=new UserDao();
			count=dao.insert(vo);
			Webutill.forward(request, response, "WEB-INF/views/user/joinsuccess.jsp");
			
		} else if("loginform".equals(actionName)) {
			
			Webutill.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
		} else if("login".equals(actionName)){
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			UserDao dao=new UserDao();
			UserVo vo=dao.getUser(email, password);
		
			if(vo==null) {
				Webutill.redirect(request, response, "/mysite/user?a=loginform&result=fail");
				
			} else {
				HttpSession session=request.getSession(true);
				session.setAttribute("authUser", vo);
				Webutill.redirect(request, response, "/mysite/main");
			}
			
			
		} else if("logout".equals(actionName)){
			HttpSession session=request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			Webutill.redirect(request, response, "/mysite/main");//사용자입장
			
		} else if("modifyform".equals(actionName)) {
			HttpSession session=request.getSession();
			UserVo authUser=(UserVo)session.getAttribute("authUser");
			int no=authUser.getNo();
			
			UserDao dao=new UserDao();
			UserVo userVo=dao.getUser(no);
			System.out.println(userVo.toString());
			
			request.setAttribute("userVo", userVo);
			Webutill.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			
		}else if("modify".equals(actionName)){

			String name=request.getParameter("name");
			String pass=request.getParameter("password");
			String gender=request.getParameter("gender");
			
			UserVo vo=new UserVo();
			vo.setNames(name);
			vo.setPasswords(pass);
			vo.setGender(gender);
			
			HttpSession session=request.getSession();
			UserVo authUser=(UserVo)session.getAttribute("authUser");
			
			int no=authUser.getNo();
			vo.setNo(no);
			
			UserDao dao=new UserDao();
			dao.update(vo);
			
			authUser.setNames(name);		
			Webutill.forward(request, response, "/WEB-INF/views/main/index.jsp");			
			
		}else  {
			Webutill.redirect(request, response, "/mysite/main");
			
		}
			
		

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
