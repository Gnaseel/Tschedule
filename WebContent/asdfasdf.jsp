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
 		SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
 		System.out.println("time"+s.format(d));
 		String time=s.format(d);
 		
 		SimpleDateFormat ss = new SimpleDateFormat("yyyyMMddhhmmss");
 		String time2=ss.format(d);
 	%>
 	<script>
 	var time="<%=time2%>";
 	var i =3;

 		$("#target").click(function(){
 			var uri="https://accounts.google.com/o/oauth2/v2/auth?"+
 					 "scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive.metadata.readonly%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar&"+
 				 	 "access_type=offline&"+
 				 	 "include_granted_scopes=true&"+
 				 	 "state=state_parameter_passthrough_value&"+
 					 "redirect_uri=http://localhost:805/Tschedule/receiveCode.jsp&"+
 				 	 "response_type=code&"+
 				 	 "client_id=814235019489-jl6sj0r8j6n3v7o9if0m97598c8dkvv4.apps.googleusercontent.com";
 			$('#result').html(uri);
 			location.href=uri;
 		});
 		$(document).ready(function(){
 			
 			
 			
 			/*
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
 			str=$("#frm").serialize();
 			$.ajax({
 				type:"POST",
 				url:"http://localhost:805/Tschedule/FrontController/search.do",
 				dataType:"JSON",
 				data:str,
 				success:function(data){
 					alert("!"+data.result);
 					
 				},
 				error:function(error){
 					alert("error " +error.responseText);
 				}
 			});
 			*/
 			
 			
 		});
		
 		function goWbs(x, y){ 
 			
 			
 			$('input[name="depPlaceName"]').val(x);
 			$('input[name="arrPlaceName"]').val(y);
 			$('input[name="depPlandTime"]').val(time);
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
 	<div id = "result"></div>
  </body>
</html>




