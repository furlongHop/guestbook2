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


@WebServlet("/gbc")
public class guestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String act = request.getParameter("action");
		
		if("addList".equals(act)) {
			
			System.out.println("action=addList");
			
			GuestbookDao gbDao = new GuestbookDao();
			List<GuestbookVo> guestbookbList = gbDao.getList();
			
			request.setAttribute("gbList", guestbookbList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			
		}else if("add".equals(act)) {
		
			System.out.println("action=add");	
			
			GuestbookDao guestbookDao = new GuestbookDao();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			//String regDate = request.getParameter("redDate");
			
			GuestbookVo guestbookVo = new GuestbookVo(name,password,content); 
			
			guestbookDao.guestInsert(guestbookVo);
			
			response.sendRedirect("/guestbook2/gbc?action=addList");
			
		}else if("delete".equals(act)) {
			
			System.out.println("action=delete");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			//List<GuestbookVo> gbList = guestbookDao.getList();
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("pass");
			String pass = guestbookDao.getGuest(no).getPassword();
			
			if(pass.equals(password)){
				guestbookDao.guestDelete(no);
				
			}else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
			
			response.sendRedirect("/guestbook2/gbc?action=addList");
		
		}else if("deleteForm".equals(act)) {
			
			System.out.println("action=deleteForm");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			request.setAttribute("num", no);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
