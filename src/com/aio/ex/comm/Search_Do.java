package com.aio.ex.comm;

import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aio.ex.dto.TrainInfoDTO;
import com.aio.ex.parsing.*;
public class Search_Do implements TCommand {
	@Override
	
	//-------------------------------------------------------열차 정보를 검색
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------In SearchDo----------------------------------------");
		String query = "&depPlaceId=" + request.getParameter("depPlaceId") + "&arrPlaceId="+ request.getParameter("arrPlaceId") + "&depPlandTime=";
		
		AIOParsing par=new AIOParsing(); 
		par.ParseTrainInfo(query,request);
		System.out.println("-----------------------------Out SearchDo----------------------------------------");
		return;
	}
}
