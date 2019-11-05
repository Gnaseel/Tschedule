package com.aio.ex.comm;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.json.simple.JSONObject;
import com.aio.ex.parsing.*;
@WebServlet("/GetCityCode_Do")
public class GetCityCode_Do implements TCommand {
	//----------------------------------------------------역의 이름을 입력하면 공공데이터 포털 검색에 필요한 역 코드를 찾아 반환해주는 함수------------------------------------
    public void execute(HttpServletRequest request, HttpServletResponse response) {
    	
    	
    	try {
    		request.setCharacterEncoding("UTF-8");
    		response.setContentType("text/html;charset=UTF-8"); 
    		System.out.println("-----------------------------In GetCityCodeDo----------------------------------------");
        	String stationName="";
        	String depPlaceName="";
        	String arrPlaceName="";
        	stationName=request.getParameter("stationName");
        	depPlaceName=request.getParameter("depPlaceName");
        	arrPlaceName=request.getParameter("arrPlaceName");
        	//stationName=stationName.substring(0,stationName.length()-1);
        	depPlaceName=depPlaceName.substring(0,depPlaceName.length()-1);
        	arrPlaceName=arrPlaceName.substring(0,arrPlaceName.length()-1);
        	//System.out.println("st name = "+stationName);
    		  
    		  JSONObject  obj = new JSONObject();
    		  obj.put("result", "raa");
    		  
    		  response.setContentType("application/x-json; charset=EUC-KR");
    		  
    		  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1511");
    		  Statement stmt = con.createStatement();
    		  ResultSet rSet;
    		  
    		  String query1="select * from station where stationName = '"+depPlaceName+"'";
    		  String query2="select * from station where stationName = '"+arrPlaceName+"'";
    		  System.out.println("query1 = "+query1);
    		  System.out.println("query2 = "+query2);
    		  rSet = stmt.executeQuery(query1);
    		  while(rSet.next()) {
    			  String str=rSet.getString("stationCode");
    			  obj.put("depPlaceCode",str);
    			  System.out.println("put "+str);
    			
    		  }
    		 
    		  rSet = stmt.executeQuery(query2);
    		  while(rSet.next()) {
    			  String str=rSet.getString("stationCode");
    			  obj.put("arrPlaceCode",str);
    			  System.out.println("put "+str);
    			 
    		  }
    		  response.getWriter().print(obj);
    		  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return;
    }
}
