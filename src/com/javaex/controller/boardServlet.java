package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.boardDao;
import com.javaex.utill.Webutill;
import com.javaex.vo.UserVo;
import com.javaex.vo.boardVo;


@WebServlet("/bs")
public class boardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("컨트롤러");
		String action=request.getParameter("a");
		
		if("read".equals(action)) {
			
			int no;
			int hit;
			
			no=Integer.valueOf(request.getParameter("num"));			
			hit=Integer.valueOf(request.getParameter("hit"));
			
			boardVo vo=new boardVo();
			boardDao dao=new boardDao();
			
			vo=dao.getUser(no);
			dao.count(hit, no);
			request.setAttribute("vo", vo);
			
			Webutill.forward(request, response, "/WEB-INF/views/board/read.jsp");			
		} else if("writeform".equals(action)) {

			Webutill.forward(request, response, "/WEB-INF/views/board/writeform.jsp");
		} else if("write".equals(action)) {
		
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			
			HttpSession session=request.getSession();
			UserVo authUser=(UserVo)session.getAttribute("authUser");
			int user_no=authUser.getNo();
			
			boardDao dao=new boardDao();
			dao.insert(title, content, user_no);
			Webutill.redirect(request, response, "/mysite/bs");
			
		} else if("modifyform".equals(action)) {
		
			Webutill.forward(request, response, "/WEB-INF/views/board/modifyform.jsp");
		} else if("modify".equals(action)) {
				
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			int hit=Integer.valueOf(request.getParameter("hit"));		
			int num=Integer.valueOf(request.getParameter("num"));
			
			boardDao dao=new boardDao();
			dao.update(title, content, num);
			
			String url = "/mysite/bs?a=read&num="+num;
			String url2 =url+"&hit="+hit;
			
			Webutill.redirect(request, response, url2);
		} else if("delete".equals(action)) {
			
			int num=Integer.valueOf(request.getParameter("num"));	
			boardDao dao=new boardDao();
			dao.delete(num);
			
			Webutill.redirect(request, response, "/mysite/bs");
		} else if("search".equals(action)) {
			String kwd=request.getParameter("kwd");
			
			boardDao dao=new boardDao();
			List<boardVo> list=dao.search(kwd);
			request.setAttribute("list", dao.setting(list));
			
			Webutill.forward(request, response, "/WEB-INF/views/board/list.jsp");
			
		} else {

			boardDao dao=new boardDao();
			List<boardVo> list=dao.getlist();
	
			request.setAttribute("list",dao.setting(list));			
			Webutill.forward(request, response, "/WEB-INF/views/board/list.jsp");
			
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
