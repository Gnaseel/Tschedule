package com.aio.DBcommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aio.ex.comm.*;

@WebServlet("/SignUp_Do")
public class SignUp_Do implements TCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------In SignUpDo----------------------------------------");	
		try {
			String viewPage = null;
			TCommand command = null;

			Connection con;
			Statement stmt;
			ResultSet rSet;

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1511");
			stmt = con.createStatement();
			String uri = request.getRequestURI();
			System.out.println("serPath = " + request.getServletPath());
			String conPath = request.getContextPath() + "/DBFrontController/";
			System.out.println("uri = " + uri);
			System.out.println("conPath = " + conPath);
			String com = uri.substring(conPath.length());
			System.out.println("Command = " + com);
			System.out.println("signUp.do");
			
			String name = null;
			String id = null;
			String password = null;

			name = request.getParameter("name");
			id = request.getParameter("id");
			password = request.getParameter("password");
			System.out.println("name = " + name);
			System.out.println("id = " + id);
			System.out.println("password = " + password);
			
			String query = "insert into userlist values('"+
							name+"','"+
							id+"','"+
							password+"'"+
							") ";
			System.out.println(query);
			int Success = 0;
			Success=stmt.executeUpdate(query);
			if (Success==1) {
				
			}
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----------------------------Out SignUpDo----------------------------------------");	
	}

}
