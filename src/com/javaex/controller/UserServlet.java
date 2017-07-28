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
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count=0;
		request.setCharacterEncoding("UTF-8");
		String actionName=request.getParameter("a");
		System.out.println("컨트롤러");
		if("joinform".equals(actionName)) {
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp");
			rd.forward(request, response);
		} if("join".equals(actionName)) {
			String name= request.getParameter("name");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String gender=request.getParameter("gender");
			UserVo vo=new UserVo(name,email,password,gender);
			
			UserDao dao=new UserDao();
			count=dao.insert(vo);
			
			RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/views/user/joinsuccess.jsp");
			rd.forward(request, response);
			
		} else if("loginform".equals(actionName)) {
			
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
			rd.forward(request, response);
			
	
		} else if("login".equals(actionName)){
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			UserDao dao=new UserDao();
			UserVo vo=dao.getUser(email, password);
		
			if(vo==null) {
				System.out.println("실패");
				response.sendRedirect("/mysite/user?a=loginform&result=fail");
				return;
			} else {
				System.out.println("성공");
				HttpSession session=request.getSession(true);
				session.setAttribute("authUser", vo);
				
				response.sendRedirect("/mysite/main");
				return;
			}
			
			
			
			
		} else if("logout".equals(actionName)){
			System.out.println("들어왔엉");
			HttpSession session=request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			response.sendRedirect("/mysite/main");
			
		} else if("modifyform".equals(actionName)) {
			HttpSession session=request.getSession();
			UserVo authUser=(UserVo)session.getAttribute("authUser");
			int no=authUser.getNo();
			
			UserDao dao=new UserDao();
			UserVo userVo=dao.getUser(no);
			System.out.println(userVo.toString());
			
			request.setAttribute("userVo", userVo);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/user/modifyform.jsp");
			rd.forward(request, response);
			
		}else {
			response.sendRedirect("/mysite/main");
		}
			
		

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
