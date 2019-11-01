package com.temp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class Train
 */
@WebServlet("/Train")
public class Train extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Train() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doAction(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=EUC-KR");
		request.setCharacterEncoding("EUC-KR");

		//---------------------------------------------------------------MAPPING------------------------------------------------
		
		
		String url="http://openapi.tago.go.kr/openapi/service";
		String urn="";
		String key="QcyS50QK1xUNZiLtOoHuI5R%2BX12rdkAdBtCB8MCD0lX1tAnQ2TdGq0teaAJ4MShmhIMwziiY7tm5Cd1B3vXTFg%3D%3D";
		String uri;
		String date=request.getParameter("year")+request.getParameter("month")+request.getParameter("day");
		String para;
		

		
		System.out.println("pageNum = "+request.getSession().getAttribute("pageNum"));
		System.out.println("NextValue = "+request.getSession().getAttribute("Next"));
		
		int pageNum=Integer.parseInt((String) request.getSession().getAttribute("pageNum"))+Integer.parseInt((String) request.getSession().getAttribute("Next"));
		if(request.getParameter("Next")!=null)
			{
				pageNum++;
				System.out.println("page Plused");
			}
		System.out.println("pp = "+pageNum);
			//if(길찾기일 경우)
		urn+="/TrainInfoService/getStrtpntAlocFndTrainInfo?ServiceKey=";
		para="&depPlaceId="+request.getParameter("StartPoint")+"&arrPlaceId="+request.getParameter("EndPoint")+"&depPlandTime="+date;
		para+="&pageNum="+Integer.toString(pageNum);
		System.out.println("para = "+para);
		uri=url+urn+key+para;
		request.getSession().setAttribute("uri",uri);
		//---------------------------------------------------------------XML PARSING------------------------------------------------
		NodeList adultcharge= null;	//운임
		NodeList arrplandtime= null;//도착시간
		NodeList depplandtime= null;//출발시간
		NodeList traingradename= null;//차량종류
		NodeList arrplacename= null;//도착지
		NodeList depplacename= null;//출발지
		DocumentBuilderFactory dFac=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBul=dFac.newDocumentBuilder();
			Document doc=dBul.parse(uri);
			adultcharge = doc.getElementsByTagName("adultcharge");
			request.getSession().setAttribute("adultcharge", adultcharge);
			
			arrplandtime = doc.getElementsByTagName("arrplandtime");
			request.getSession().setAttribute("arrplandtime", arrplandtime);
			
			depplandtime = doc.getElementsByTagName("depplandtime");
			request.getSession().setAttribute("depplandtime", depplandtime);
			
			traingradename = doc.getElementsByTagName("traingradename");
			request.getSession().setAttribute("traingradename", traingradename);
			
			arrplacename = doc.getElementsByTagName("arrplacename");
			request.getSession().setAttribute("arrplacename", arrplacename);
			
			depplacename = doc.getElementsByTagName("depplacename");
			request.getSession().setAttribute("depplacename", depplacename);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		//---------------------------------------------------------------DEBUGING------------------------------------------------
		
		System.out.println("출발역 = "+request.getParameter("StartPoint"));
		System.out.println("도착역 = "+request.getParameter("EndPoint"));
		System.out.println("시간 = "+date);
		System.out.println("uri = "+uri);
		response.sendRedirect("ResultTrain.jsp?&depPlaceId="+request.getParameter("StartPoint")+"&arrPlaceId="+request.getParameter("EndPoint")+"&depPlandTime="+date+"&pageNum="+Integer.toString(pageNum));
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/ResultTrain.jsp"); //넘길 페이지 주소
        //dispatcher.forward(request, response);

	}
	

}


/*
	<c:forEach var="dto" items="${DTOArray}">
					<tr>
						<td>${dto.arrplandtime} }</td>
					</tr>
				</c:forEach>
 */