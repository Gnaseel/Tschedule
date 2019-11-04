<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="UTF-8">
    <title>11</title>
  </head>
   <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <body>
 	
 	<a href="DBmanager?a=111">go</a>
 	<input type="button" id="target" value="button" />
 	<% 
 		Date d = new Date();
 		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmm");
 		System.out.println("time"+s.format(d));
 		String time=s.format(d);
 	%>
 	<script>
 	var time="<%=time%>";
 	
 	var i =3;	
 	document.getElementById("target").addEventListener("click",function(){temp(3);});
 	function temp(data){
 		alert(data);
 	}
 		$(document).ready(function(){
 			$("#resultJuso").append("</br>date = "+time);
 			//alert("in");
 			goWbs("서울역","용산역");
 			var str=$("#frm").serialize();
 			var uri="http://localhost:805/Tschedule/FrontController/getCityCode.do";
 			
 			//alert(str);
 			$.ajax({
 				type:"post",
 				data:str,
 				url:uri,
 				dataType:"json",
 				async:false,
 				success:function(data){
 					alert("success");
 					$("#resultJuso").append("</br>data = "+data.result);
 					$("#resultJuso").append("</br>datacode = "+data.depPlaceCode);
 					$("#resultJuso").append("</br>datacode = "+data.arrPlaceCode);
 					//String query = "&depPlaceId=" + request.getParameter("depPlaceId") + "&arrPlaceId="+ request.getParameter("arrPlaceId") + "&depPlandTime=";
 					$('input[name="depPlaceId"]').val(data.depPlaceCode);
 					$('input[name="arrPlaceId"]').val(data.arrPlaceCode);
 					$('input[name="depPlaceTime"]').val(time);
 				},
 				error:function(e,error){
 					alert(e.responseText);
 					alert(error);
 				}
 			});
 			$.ajax({
 				type:"POST",
 				url:"/FrontController/search.do"
 			});
 			
 			
 		});
		
 		function goWbs(x, y){ 
 			
 			
 			$('input[name="depPlaceName"]').val(x);
 			$('input[name="arrPlaceName"]').val(y);
 			$('input[name="depPlandTime"]').val(y);
 			$('input[name="stationName"]').val("NAMENAME");
 			}

 			
 	</script>
 	<form id="frm" method="POST">
 			    <input type="hidden" name="depPlaceName" />
 			    <input type="hidden" name="arrPlaceName" />

 			    <input type="hidden" name="depPlaceId" />
 			    <input type="hidden" name="arrPlaceId" />
 			    <input type="hidden" name="depPlandTime" />
 			    
 			</form>
 	<div id = "resultJuso"></div>
  </body>
</html>




