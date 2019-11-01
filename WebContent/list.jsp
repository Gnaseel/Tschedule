<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.aio.ex.dto.TrainInfoDTO" %>
<%@ page import="com.aio.ex.dao.TrainInfoDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>listPage</h1>
	<%
		ArrayList<TrainInfoDTO> DTOArray = (ArrayList<TrainInfoDTO>)request.getAttribute("TrainArray");
	%>
	<h1>열차 시간표</h1>
	<%
		String restRequest = "&depPlaceId=" + request.getParameter("depPlaceId")+"&arrPlaceId="+request.getParameter("arrPlaceId")+"&depPlandTime=";
		if(request.getParameter("year")!=null)
		{
			System.out.println("ONE");
			restRequest+=request.getParameter("year")+request.getParameter("month")+request.getParameter("day");
		}
		else
		{
			System.out.println("TWO");
			restRequest+=request.getParameter("depPlandTime");
		}
		//restRequest+="&pageNo="+request.getAttribute("pageNo");
		System.out.println("RR is "+restRequest);

	%>
	<form action = "nextpage.do?<%out.println(restRequest+"&pageNo="+((int)request.getAttribute("pageNo")-2));%>" method = "post">
		<input type="submit" value ="이전">
	</form>
	<form action = "nextpage.do?<%out.println(restRequest+"&pageNo="+request.getAttribute("pageNo"));%>" method = "post">
		<input type="submit" value ="다음">
	</form>
		<table border = "1" width = "1000" height = "600">
			<th>출발 시간</th>
			<th>도착 시간</th>
			<th>열차 종류</th>
			<th>운임 요금</th>
		<%
		for (int i = 0; i<10; i++) {
			
			out.println("<tr>");
			if(DTOArray.size()<=i){
				out.println("<td colspan=\"4\">값이 없습니다.</td>");
				i=9000;
			}else{
			out.println("<td>" + DTOArray.get(i).getDepplandtime().substring(8, 10) + "시 "
					+ DTOArray.get(i).getDepplandtime().substring(10, 12) + "분 " + "</td>");
			out.println("<td>" + DTOArray.get(i).getArrplandtime().substring(8, 10) + "시 "
					+ DTOArray.get(i).getArrplandtime().substring(10, 12) + "분 " + "</td>");
			out.println("<td>" + DTOArray.get(i).getTraingradename() + "</td>");
			out.println("<td>" + DTOArray.get(i).getAdultcharge() + "</td>");
			}
			out.println("</tr>");
			
		}
		%>
		</table>
	
</body>
</html>