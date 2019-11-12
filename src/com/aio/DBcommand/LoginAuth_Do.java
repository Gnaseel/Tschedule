package com.aio.DBcommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aio.ex.comm.TCommand;


@WebServlet("/LoginAuth")
public class LoginAuth_Do implements TCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------In LoginAuthDo----------------------------------------");
		HttpSession session=request.getSession();
		try {
			Connection con;
			Statement stmt;
			ResultSet rSet;
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1511");
			stmt=con.createStatement();
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			String query="select PASSWORD from userlist where id='"+
						 id+"'";
			System.out.println("query= "+query);
			rSet=stmt.executeQuery(query);
			rSet.next();
			String getPW=rSet.getString("PASSWORD");
			System.out.println("getPW= "+getPW);
			System.out.println("inputPW = "+password);
			
			if(getPW.equals(password)) {
				session.setAttribute("id", id);
				request.setAttribute("viewPage", "/mainWindow.jsp");
			}else {
				request.setAttribute("viewPage", "/Login.html");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----------------------------Out LoginAuthDo----------------------------------------");	
	}
	
}
