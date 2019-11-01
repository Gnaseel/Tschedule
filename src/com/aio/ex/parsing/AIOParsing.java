package com.aio.ex.parsing;

import java.io.IOException;
import com.aio.ex.dto.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

@WebServlet("/AIOParsing")


public class AIOParsing extends HttpServlet {
	
	String TransportURI="http://openapi.tago.go.kr/openapi/service";
	private String key = "?ServiceKey=QcyS50QK1xUNZiLtOoHuI5R%2BX12rdkAdBtCB8MCD0lX1tAnQ2TdGq0teaAJ4MShmhIMwziiY7tm5Cd1B3vXTFg%3D%3D";

	private String ServiceName(String name) {
		String re="";
		// 열차 정보 관련 서비스
		if(name.equals("train"))		
			re+="/TrainInfoService";
		return re;
	}
	private String OperationName(String name) {
		String re="";
		// 출발지, 목적지, 시간을 사용해서 열차를 조회해주는 오퍼레이션
		if(name.equals("getTrain"))
			re+="/getStrtpntAlocFndTrainInfo";
		return re;
	}
	public ArrayList<AddrInfoDTO> ParseAddrInfo(String query, HttpServletRequest request){
		ArrayList<AddrInfoDTO> ADTOarray = new ArrayList<AddrInfoDTO>();
		
		return ADTOarray;
	}
	
	//----------------------------------------------------역에 따른 열차 정보------------------------------------
	public ArrayList<TrainInfoDTO> ParseTrainInfo(String query, HttpServletRequest request) {
		ArrayList<TrainInfoDTO> TDTOarray = new ArrayList<TrainInfoDTO>();
		
		String operName = ServiceName("train")+OperationName("getTrain");
		
		if (request.getParameter("year") != null) {
			query += request.getParameter("year") + request.getParameter("month") + request.getParameter("day");
		} else {
			query += request.getParameter("depPlandTime");
		}
		if (request.getParameter("pageNo") == null) {
			request.setAttribute("pageNo", 1);
		}
		query += "&numOfRows=10&pageNo=" + request.getAttribute("pageNo");
		String path = TransportURI + operName + key + query;
		System.out.println("path = " + path);
		NodeList adultcharge = null; // 운임
		NodeList arrplandtime = null;// 도착시간
		NodeList depplandtime = null;// 출발시간
		NodeList traingradename = null;// 차량종류
		NodeList arrplacename = null;// 도착지
		NodeList depplacename = null;// 출발지

		try {
			DocumentBuilderFactory docF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = docF.newDocumentBuilder();
			Document doc = docB.parse(path);
			adultcharge = doc.getElementsByTagName("adultcharge");
			arrplandtime = doc.getElementsByTagName("arrplandtime");
			depplandtime = doc.getElementsByTagName("depplandtime");
			traingradename = doc.getElementsByTagName("traingradename");
			arrplacename = doc.getElementsByTagName("arrplacename");
			depplacename = doc.getElementsByTagName("depplacename");
			for (int i = 0; i < adultcharge.getLength(); i++)
				TDTOarray.add(new TrainInfoDTO(arrplandtime, depplandtime, adultcharge, traingradename, arrplacename,
						depplacename, i));
			request.setAttribute("TrainArray", TDTOarray);
			System.out.println("array size = " + TDTOarray.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TDTOarray;
	}
	/*
	public ArrayList<StationDTO> PraseStationInfo(String query, HttpServletRequest request){
		s
	}
	*/
	
}
