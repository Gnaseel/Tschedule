package com.aio.ex.command;

import java.io.IOException;
import com.aio.ex.comm.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	
	
	
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		System.out.println("----------------------------------------In doAction---------------------------------------------------");
		String viewPage=null;
 		TCommand command=null;
		
 		
		String uri=request.getRequestURI();
		String conPath="/FrontController/"+request.getContextPath();
		String com=uri.substring(conPath.length());
		System.out.println("Command = "+com);
		//------------------------------------------------------command-------------------------------------------------------------------------
		if(com.equals("search.do")){
			System.out.println("SearchDo");
			command = new Search_Do();
			command.execute(request,response);
			viewPage="/list.jsp";
			
		}else if(com.equals("nextpage.do")) {
			System.out.println("nextpage.do");
			request.setAttribute("pageNo", Integer.parseInt(request.getParameter("pageNo"))+1);
			command = new Search_Do();
			command.execute(request, response);
			
			viewPage="/list.jsp";
		}else {
			System.out.println("Nothing");
		}
		
		
		//------------------------------------------------------forward-------------------------------------------------------------------------
		RequestDispatcher disp = request.getRequestDispatcher(viewPage);
		disp.forward(request, response);
		
	}
}
