package com.aio.DBmng;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aio.ex.parsing.*;
import com.aio.ex.dto.*;
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
				
				AIOParsing p=new AIOParsing();
				ArrayList<StationInfoDTO> arr= p.ParseStationInfo("", request);
				for(int i=0;i<arr.size();i++) {
					System.out.println("insert into station values('"+arr.get(i).getStationName()+"','"+arr.get(i).getStationCode()+"','0','0')");
					stmt.executeQuery("insert into station values('"+arr.get(i).getStationName()+"','"+arr.get(i).getStationCode()+"','0','0')");
				}
				
				
				
			} catch (Exception e) {
			System.out.println("씨발련아");
			e.printStackTrace();
		}
		
		
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
