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
		//else if(name.equals())
		return re;
	}
	private String OperationName(String name) {
		String re="";
		if(name.equals("getTrain"))
			re+="/getStrtpntAlocFndTrainInfo";
		else if(name.equals("getCityCode"))
			re+="/getCtyCodeList";
		else if(name.equals("getStationInfo"))
			re+="/getCtyAcctoTrainSttnList";
		return re;
	}
	//----------------------------------------------------임시------------------------------------
	public ArrayList<AddrInfoDTO> ParseAddrInfo(String query, HttpServletRequest request){
		ArrayList<AddrInfoDTO> ADTOarray = new ArrayList<AddrInfoDTO>();
		
		return ADTOarray;
	}
	//----------------------------------------------------역에 따른 열차 정보를 TrainArray에 저장------------------------------------
	public ArrayList<TrainInfoDTO> ParseTrainInfo(String query, HttpServletRequest request) {
		ArrayList<TrainInfoDTO> TDTOarray = new ArrayList<TrainInfoDTO>();
		
		String operName = ServiceName("train")+OperationName("getTrain");
		
		if (request.getParameter("year") != null) {
			query += request.getParameter("year") + request.getParameter("month") + request.getParameter("day");
		} else {
			query += request.getParameter("depPlandTime").substring(0,8);
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
	//----------------------------------------------------url의 XML 정보를 name[]태그로 분류해서 노드를 리턴함------------------------------------
	public NodeList[] XMLParse(String url,String[] name){
		NodeList[] nodes = new NodeList[name.length];
		try {
			
			DocumentBuilderFactory docF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = docF.newDocumentBuilder();
			Document doc = docB.parse(url);
			for(int i=0;i<name.length;i++) {
				nodes[i]=doc.getElementsByTagName(name[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodes;
	}
	//----------------------------------------------------공공데이터 포털의 역 정보 데이터를 배열로 바꿔주는 함수------------------------------------
	public ArrayList<StationInfoDTO> ParseStationInfo(String query, HttpServletRequest request){
		System.out.println("PAR IN");
		
		
		String[] names = new String[2];		//DB저장을 위한 컬럼 이름
		String[] cityCode = new String[1];	//도시 번호에서 정보를 빼오기 위한 이름
		String[] cityCodeData = new String[20];//실제 도시코드 데이터
		//String[] 
		String operName = ServiceName("train")+OperationName("getCityCode");	//도시코드 데이터를 얻기위한 오퍼
		String path = TransportURI + operName + key + query;
		cityCode[0]="citycode";
		names[0]="nodeid";
		names[1]="nodename";
		//names[0]="stationName";
		//names[1]="stationCode";
		//names[2]="coordX";
		//names[3]="coordY";
		System.out.println("path is = "+path);
		ArrayList<StationInfoDTO> SDTOArray = new ArrayList<StationInfoDTO>();
		NodeList[] nodes =  XMLParse(path, cityCode);
		NodeList[] cityNodes=null;
		System.out.println("len = "+nodes[0].getLength());
		
		for(int i=0;i<nodes[0].getLength();i++) {
			cityCodeData[i]=nodes[0].item(i).getTextContent();
			System.out.println(nodes[0].item(i).getTextContent());
		}
		System.out.println("!");
		operName=ServiceName("train")+OperationName("getStationInfo");
		for(int i=0;i<nodes[0].getLength();i++) {
			
			path= TransportURI + operName+key+"&cityCode="+cityCodeData[i];
			System.out.println("path = "+path);
			cityNodes = XMLParse(path,names);
			
			for(int ii=0;ii<cityNodes[0].getLength();ii++) {
				//System.out.println("! + "+cityNodes[0].item(ii).getTextContent());
				//System.out.println("! + "+cityNodes[1].item(ii).getTextContent());
				SDTOArray.add(new StationInfoDTO(cityNodes[0].item(ii), cityNodes[1].item(ii), null,null));
				System.out.println("역 이름 = "+SDTOArray.get(ii).getStationName());
			}
		}
		return SDTOArray;
	}
}
