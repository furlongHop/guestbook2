package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

import util.WebUtil;


@WebServlet("/gbc")
public class guestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String act = request.getParameter("action");
		
		if("addList".equals(act)) {
			
			System.out.println("action=addList");
			
			GuestbookDao gbDao = new GuestbookDao();
			List<GuestbookVo> guestbookbList = gbDao.getList();
			
			//guestbookList를 setAttribute 메소드로 gbList라는 이름으로 request에 저장한다. 
			request.setAttribute("gbList", guestbookbList);
			
			//forward
			//1. addList와 연결(이동할 페이지 경로 지정/주소 받은 후 저장)
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			//2. request와 response 두 객체는 해당 주소(1번 주소)에 전달
			rd.forward(request, response);
			
		}else if("add".equals(act)) {
		
			System.out.println("action=add");	
			
			GuestbookDao guestbookDao = new GuestbookDao();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			//String regDate = request.getParameter("redDate");
			
			//guestbookVo로 하나로 묶어 보낸다(묶는 게 일반적)
			GuestbookVo guestbookVo = new GuestbookVo(name,password,content); 
			guestbookDao.guestInsert(guestbookVo);
			
			//response.sendRedirect("/guestbook2/gbc?action=addList");
			
			//리다이렉트
			WebUtil.redirect(request, response, "/guestbook2/gbc");//url
			
		}else if("delete".equals(act)) {
			
			System.out.println("action=delete");
			
			//GuestbookDao guestbookDao = new GuestbookDao();
			//List<GuestbookVo> gbList = guestbookDao.getList();
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("pass");

			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);

			GuestbookDao dao = new GuestbookDao();
			dao.delete(vo);

			
			//리다이렉트
			WebUtil.redirect(request, response, "/guestbook2/gbc");
		
		}else if("deleteForm".equals(act)) {
			
			System.out.println("action=deleteForm");
			
			//request를 이용하지 않고 파라미터 값을 이용
			//int no = Integer.parseInt(request.getParameter("no"));
			//request.setAttribute("num", no);
			
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			//rd.forward(request, response);
			
			//포워드-파일 경로
			WebUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
			
		}else {//리스트를 기본값으로
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> gList = dao.getList();

			request.setAttribute("guestList", gList);
			
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
