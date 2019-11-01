package com.aio.ex.comm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aio.ex.parsing.AIOParsing;

/**
 * Servlet implementation class SearchAddr_Do
 */
@WebServlet("/SearchAddr_Do")
public class SearchAddr_Do implements TCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------In SearchAddrDo----------------------------------------");
		String query = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=1&countPerPage=10&keyword=";
		query+=request.getParameter("juso");
		query+="&confmKey=devU01TX0FVVEgyMDE5MTAxODExNDA0MDEwOTA5OTg=&resultType=json";
		AIOParsing par=new AIOParsing(); 
		par.ParseTrainInfo(query,request);
		System.out.println("-----------------------------Out SearchAddrDo----------------------------------------");
		return;
	}
	
}
