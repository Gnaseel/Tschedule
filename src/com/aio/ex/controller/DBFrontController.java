package com.aio.ex.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aio.ex.parsing.*;
import com.aio.ex.comm.TCommand;
import com.aio.ex.dto.*;
import com.aio.DBcommand.*;

/**
 * Servlet implementation class DBmanager
 */
@WebServlet("*.DBdo")
public class DBFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		String viewPage = null;
		TCommand command = null;
		String uri = request.getRequestURI();
		System.out.println("serPath = " + request.getServletPath());
		String conPath = request.getContextPath() + "/DBFrontController/";
		System.out.println("uri = " + uri);
		System.out.println("conPath = " + conPath);
		String com = uri.substring(conPath.length());
		System.out.println("Command = " + com);

		if (com.equals("signUp.DBdo")) {
			System.out.println("signUp.DBdo");
			command = new SignUp_Do();
			command.execute(request, response);
			viewPage = "/Login.html";
		} else if (com.equals("loginAuth.DBdo")) {
			System.out.println("loginAuth.DBdo");
			command = new LoginAuth_Do();
			command.execute(request, response);
			viewPage = (String) request.getAttribute("viewPage");
			System.out.println("view page = " + viewPage);
		} else if (com.equals("setPath.DBdo")) {
			System.out.println("setPath");
			command = new SetPath_Do();
			command.execute(request, response);
			viewPage = "/MainMap.jsp";
		} else if (com.equals("getPath.DBdo")) {
			System.out.println("getPath");
			command = new GetPath_Do();
			command.execute(request, response);
			viewPage="/pathLog.jsp";
		}
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		if (!viewPage.equals("N"))
			dis.forward(request, response);
	}
}
