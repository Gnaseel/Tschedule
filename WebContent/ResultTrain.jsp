<%@ page import="org.w3c.dom.NodeList" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta >
<title>Insert title here</title>
</head>
<body>
	<%
		NodeList chargeNode = (NodeList)request.getSession().getAttribute("adultcharge");
		NodeList arrplandtime = (NodeList)request.getSession().getAttribute("arrplandtime");
		NodeList depplandtime = (NodeList)request.getSession().getAttribute("depplandtime");
		NodeList traingradename = (NodeList)request.getSession().getAttribute("traingradename");
		NodeList arrplacename = (NodeList)request.getSession().getAttribute("arrplacename");
		NodeList depplacename = (NodeList)request.getSession().getAttribute("depplacename");
		////////////////////////
		
		out.println("<h1>열차 시간표</h1>");
	%>
	<% 
		int pageNum= Integer.parseInt((String)request.getSession().getAttribute("pageNum"));
		String uri = (String)request.getSession().getAttribute("uri");
		System.out.println("new addr = "+uri+"&pageNum="+(pageNum+1));
	%>
	<form action = "Train" method = "get">
		출발지<select name="StartPoint">
			<option value="NAT010000">서울역</option>
			<option value="NAT014445">부산역</option>
			<option value="NAT010971">천안역</option>
			<option value="NAT030879">익산역</option>
		</select>
		도착지<select name="EndPoint">
			<option value="NAT010000">서울역</option>
			<option value="NAT014445">부산역</option>
			<option value="NAT010971">천안역</option>
			<option value="NAT030879">익산역</option>
			<input type="submit" name="Next" value="다음">
		</select>
	</form>
	<%
		out.println("<table border = \"1\" width = \"1000\" height = \"600\">");
				out.println("<th>출발 시간</th>");
				out.println("<th>도착 시간</th>");
				out.println("<th>열차 종류</th>");
				out.println("<th>운임 요금</th>");
			for(int i=0;i<depplandtime.getLength();i++)
			{
				out.println("<tr>");
				out.println("<td>"+depplandtime.item(i).getTextContent().substring(8,10)+"시 "+depplandtime.item(i).getTextContent().substring(10,12)+"분 "+"</td>");
				out.println("<td>"+arrplandtime.item(i).getTextContent().substring(8,10)+"시 "+arrplandtime.item(i).getTextContent().substring(10,12)+"분 "+"</td>");
				out.println("<td>"+traingradename.item(i).getTextContent()+"</td>");
				out.println("<td>"+chargeNode.item(i).getTextContent()+"</td>");
			out.println("</tr>");
			}
		out.println("</table>");
		
	%>
	
</body>
</html>