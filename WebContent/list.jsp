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
	<h1>���� �ð�ǥ</h1>
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
		<input type="submit" value ="����">
	</form>
	<form action = "nextpage.do?<%out.println(restRequest+"&pageNo="+request.getAttribute("pageNo"));%>" method = "post">
		<input type="submit" value ="����">
	</form>
		<table border = "1" width = "1000" height = "600">
			<th>��� �ð�</th>
			<th>���� �ð�</th>
			<th>���� ����</th>
			<th>���� ���</th>
		<%
		for (int i = 0; i<10; i++) {
			
			out.println("<tr>");
			if(DTOArray.size()<=i){
				out.println("<td colspan=\"4\">���� �����ϴ�.</td>");
				i=9000;
			}else{
			out.println("<td>" + DTOArray.get(i).getDepplandtime().substring(8, 10) + "�� "
					+ DTOArray.get(i).getDepplandtime().substring(10, 12) + "�� " + "</td>");
			out.println("<td>" + DTOArray.get(i).getArrplandtime().substring(8, 10) + "�� "
					+ DTOArray.get(i).getArrplandtime().substring(10, 12) + "�� " + "</td>");
			out.println("<td>" + DTOArray.get(i).getTraingradename() + "</td>");
			out.println("<td>" + DTOArray.get(i).getAdultcharge() + "</td>");
			}
			out.println("</tr>");
			
		}
		%>
		</table>
	
</body>
</html>