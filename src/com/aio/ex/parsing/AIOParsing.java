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
		// ���� ���� ���� ����
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
	//----------------------------------------------------�ӽ�------------------------------------
	public ArrayList<AddrInfoDTO> ParseAddrInfo(String query, HttpServletRequest request){
		ArrayList<AddrInfoDTO> ADTOarray = new ArrayList<AddrInfoDTO>();
		
		return ADTOarray;
	}
	//----------------------------------------------------���� ���� ���� ������ TrainArray�� ����------------------------------------
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
		NodeList adultcharge = null; // ����
		NodeList arrplandtime = null;// �����ð�
		NodeList depplandtime = null;// ��߽ð�
		NodeList traingradename = null;// ��������
		NodeList arrplacename = null;// ������
		NodeList depplacename = null;// �����

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
	//----------------------------------------------------url�� XML ������ name[]�±׷� �з��ؼ� ��带 ������------------------------------------
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
	//----------------------------------------------------���������� ������ �� ���� �����͸� �迭�� �ٲ��ִ� �Լ�------------------------------------
	public ArrayList<StationInfoDTO> ParseStationInfo(String query, HttpServletRequest request){
		System.out.println("PAR IN");
		
		
		String[] names = new String[2];		//DB������ ���� �÷� �̸�
		String[] cityCode = new String[1];	//���� ��ȣ���� ������ ������ ���� �̸�
		String[] cityCodeData = new String[20];//���� �����ڵ� ������
		//String[] 
		String operName = ServiceName("train")+OperationName("getCityCode");	//�����ڵ� �����͸� ������� ����
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
				System.out.println("�� �̸� = "+SDTOArray.get(ii).getStationName());
			}
		}
		return SDTOArray;
	}
}
