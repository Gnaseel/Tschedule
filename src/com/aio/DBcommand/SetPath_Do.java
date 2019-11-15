package com.aio.DBcommand;

import java.io.IOException;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;
import com.aio.ex.comm.TCommand;
import java.util.Map;
@WebServlet("/SetPath_Do")
public class SetPath_Do implements TCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("-----------------------------In SetPathDo----------------------------------------");	
		
		try {
		
		String startStr=request.getParameter("startPath");
		String midStr = request.getParameter("middlePath");
		Object obj=JSONValue.parse(startStr);
		Object midobj=JSONValue.parse(midStr);
		JSONObject jobj=(JSONObject)obj;
		JSONObject jMidObj=(JSONObject)midobj;
		
		System.out.println(" 시작경로"+startStr);
		System.out.println(" 광역경로"+midStr);
		System.out.println(" 도착경로"+request.getParameter("endPath"));
		System.out.println(" arr ddd "+jobj.toString());
		System.out.println(" get info "+jobj.get("info"));
		
		JSONArray jarr=(JSONArray)jobj.get("subPath");
		System.out.println(" jarr "+jarr.toString());
		
		JSONObject sub1 = (JSONObject)jarr.get(2);
		System.out.println(" sub2 "+sub1.toString());
		Connection con;
		Statement stmt;
		ResultSet rSet;
		HttpSession session=request.getSession();
		int ran = (int)(Math.random()*10000);
		
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyMMddhhmm");
		String date = s.format(d);
		
		
		String queryToRsList="insert into reservationList values('"
							+ran+"','"
							+session.getAttribute("id")+"','"
							+date+"')";
		String queryToPList="insert into reservationpath values('"
							+ran+"','"
							+"0','"
							+"Train','"
							+jMidObj.get("startTime")+"','"
							+jMidObj.get("startTime")+"','"
							+jMidObj.get("firstStartStation")+"','"
							+jMidObj.get("lastEndStation")+"')";
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1511");
		stmt=con.createStatement();
		System.out.println("to Rs = "+queryToRsList);
		System.out.println("to P =  "+queryToPList);
		stmt.executeQuery(queryToRsList);
		stmt.executeQuery(queryToPList);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------Out SetPathDo----------------------------------------");	
	}
	
}
