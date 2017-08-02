package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.guestbookDao;
import com.javaex.vo.guestbookVo;

/**
 * Servlet implementation class guestservlet
 */


@WebServlet("/gs")
public class guestservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("a");
		
		if("add".equals(action)) {
			int count=0;
			//request.setCharacterEncoding("UTF-8");	여기에 쓰는건 무의미함 request가 쓰이기전에 써야 함
			String name=request.getParameter("name");
			String password=request.getParameter("pass");
			String content=request.getParameter("content");

			guestbookVo vo = new guestbookVo(name,password,content);
			guestbookDao dao = new guestbookDao();
			count=dao.add(vo);

			response.sendRedirect("/mysite/gs");
			
		} else if("delete".equals(action)) {
			String num=request.getParameter("no");
			request.setAttribute("num", num);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/user/deleteform.jsp");
			rd.forward(request, response);
		} else if("deleteform".equals(action)){
			
			int count=0;
			//request.setCharacterEncoding("UTF-8");	여기에 쓰는건 무의미함 request가 쓰이기전에 써야 함
			String password=request.getParameter("pass");
			String no=request.getParameter("id");
			guestbookVo vo = new guestbookVo(no,password);
			guestbookDao dao = new guestbookDao();
			count=dao.sub(vo);	
			response.sendRedirect("/mysite/gs");	//리다이렉트 -> 다시한번 컨틀롤러를 거쳐서 간다.
													//포워드 -> 컨트롤러를 거치지 않고 바로 간다.
		} else {
			//request.setCharacterEncoding("UTF-8");	여기에 쓰는건 무의미함 request가 쓰이기전에 써야 함
			
			guestbookDao dao=new guestbookDao();
			List<guestbookVo> list=dao.getlist();
			request.setAttribute("list", list);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/user/list.jsp");
			rd.forward(request, response);
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
