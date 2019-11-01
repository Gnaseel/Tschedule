package com.aio.DBmng;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aio.ex.parsing.*;
/**
 * Servlet implementation class DBmanager
 */
@WebServlet("/DBmanager")
public class DBmanager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("get"+request.getParameter("a"));
		
		Connection con;
		Statement stmt;
		ResultSet rSet = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1511");
			stmt=con.createStatement();
			//String query=request.getParameter("a");
			//if(query.equals("111")) {
				System.out.println("in");
				//stmt.executeUpdate("insert into Station values('이름2', '코드2', 1, 3)");
				rSet = stmt.executeQuery("SELECT * FROM station");
				rSet = stmt.executeQuery("SELECT * FROM station");rSet = stmt.executeQuery("SELECT * FROM station");rSet = stmt.executeQuery("SELECT * FROM station");
				while(rSet.next()) {
					String name=rSet.getString(1);
					System.out.println("이름 = "+name);
				}
			
			
			
			
			
			} catch (Exception e) {
			System.out.println("씨발련아");
			e.printStackTrace();
		}
		AIOParsing p=new AIOParsing();
		p.ParseStationInfo("", request);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("post"+request.getAttribute("a"));
	}

}
