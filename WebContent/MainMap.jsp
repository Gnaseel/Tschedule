<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date"%>
<!doctype html>
<head>
<meta charset="utf-8">
<title>네이버 map 오픈api사용</title>
</head>
<script
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=87yk72x38d"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=87yk72x38d&submodules=geocoder"></script>

<body>
	<div id="tomain">
	메인화면으로
	</div>
	<div id="map" style="width: 100%; height: 400px;"></div>
	<input type="text" id="addr">
	<input type="button" id="button1" onclick="button_click();" value="검색">
	<form id="frm" method="POST">
 			<input type="hidden" name="depPlaceName">
 			<input type="hidden" name="arrPlaceName">
 			<input type="hidden" name="depPlaceId">
 			<input type="hidden" name="arrPlaceId">
 		    <input type="hidden" name="depPlandTime">	    
 	</form>
 	<form action ="http://localhost:805/Tschedule/DBFrontController/setPath.DBdo" id="path" method="POST" accept-charset="UTF-8">
 			<input type="hidden" name="startPath">
 			<input type="hidden" name="middlePath">
 			<input type="hidden" name="endPath">	    
 	</form>
 	<%
 		Date d = new Date();
 		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmmss");
 		
 		String time=s.format(d);
 		//SimpleDateFormat
 	%>
	<script>
					$('#tomain').click(function(){
						window.location("http://localhost:805/Tschedule/mainWindow.jsp");
					});
					$("#addr").keyup(function(e){
						if(e.keyCode==13){
							$("#button1").click();
						}					
					});
					var map = new naver.maps.Map('map', {
						zoom:4,
						zoomControl : true,
						center : new naver.maps.LatLng(37,127)
					});
					var to = $('#resultJuso');
					$(document).ready(function(){
					})
					var p=null;
					var AddrDep=null;
					var AddrArv=null;
					var tempTrain=null;
					var laln= new naver.maps.LatLng(0,0);
					//---------------------------------------AJAX사용해서 검색어에 대한 주소들을 반환해줌--------------------------------
					function button_click(){
						var uri="http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=1&countPerPage=10&keyword=";
						uri+=encodeURI(document.getElementById('addr').value);
						uri+="&confmKey=devU01TX0FVVEgyMDE5MTAxODExNDA0MDEwOTA5OTg=&resultType=json";
						     $.ajax({
						           type:"post",
						           url: uri,
						           dataType:"JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
						           data:$("#form").serialize() ,
						           success : function(data) {
						        	   var d = JSON.stringify(data);
						        	   $('#resultJuso').html("");
						        	   p = JSON.parse(d);
						        	   //-------------------------------------------결과 출력--------------------------------
						        	   for(var i=0;i<p.results.common.totalCount;i++){
						        		   $('#resultJuso').append("<div id=\"result"+i+"\">");
						        		   $('#resultJuso').append("</br> 주소</br>");
						        	   		if(p.results.juso[i].bdNm!=null)
						        	   			$('#resultJuso').append(p.results.juso[i].bdNm+"</br>");
						        	   		$('#resultJuso').append(p.results.juso[i].roadAddr);
						        	   		$('#resultJuso').append("<input type=\"button\" value=\"선택\" onclick=\"SelectAddr("+i+");\"></div>");
						        	   }
						           },
						           
						           complete : function(data) {
						                 // 통신이 실패했어도 완료가 되었을 때 이 함수를 타게 된다.
						                 // TODO
						           },
						           error : function(xhr, status, error) {
						                 alert("에러발생");
						           }
						     });
					
					}
					//--------------------------------원하는 주소를 선택했을 때 상세 주소를 출력하고 목적지,출발지로 선택할건지 입력받음---------------
					function SelectAddr(i){
						var uri="http://www.juso.go.kr/addrlink/addrCoordApi.do?";
						uri+="admCd="+p.results.juso[i].admCd;
						uri+="&rnMgtSn="+p.results.juso[i].rnMgtSn;
						uri+="&udrtYn="+p.results.juso[i].udrtYn;
						uri+="&buldMnnm="+p.results.juso[i].buldMnnm;
						uri+="&buldSlno="+p.results.juso[i].buldSlno;
						uri+="&confmKey=devU01TX0FVVEgyMDE5MTAyMDEzMTgxOTEwOTEyNTg=&resultType=json";
						uri=encodeURI(uri);		
						
						var myCOORD=new naver.maps.Point();
						var aaa;
						$('#resultJuso').html("");
						
						$.ajax({
							type:"post",
							url:uri,
							dataType:"JSON",
							async:false,
							success:function(data){
							var d=JSON.stringify(data);
							var dp=JSON.parse(d);
							
							
							$('#resultJuso').append("<div id=\"COORDresult"+i+"\">");
			        		$('#resultJuso').append("</br> 주소</br>");
			        	   	if(dp.results.juso[0].bdNm!=null)
			        	   		$('#resultJuso').append("빌딩 이름 = "+dp.results.juso[0].bdNm+"</br>");
			        	   	$('#resultJuso').append("위도 = "+dp.results.juso[0].entY+"   ");
			        	   	$('#resultJuso').append("X좌표 = "+dp.results.juso[0].entX+" \(이 좌표값은 위도경도값이 아닌 UTM투영 좌표임\)</br>");
			        	   	$('#resultJuso').append(p.results.juso[0].roadAddr+"</br>");
			        	   	//$('#resultJuso').append(uri+"</br>");
			        	   	$('#resultJuso').append("<input type=\"button\" value=\"출발지 설정\" onclick=\"setPath("+i+",\'depa\');\">");
			        	   	$('#resultJuso').append("<input type=\"button\" value=\"도착지 설정\" onclick=\"setPath("+i+",\'arri\');\"></div>");
			        	   	myCOORD.add(dp.results.juso[0].entX,dp.results.juso[0].entY);
							}
						});
						//alert("위치 = "+myCOORD.toString());
						
						laln=naver.maps.UTMKCoord.fromCoordToLatLng(myCOORD);
						map.updateBy(laln,9);
						
						var aaa=new naver.maps.LatLng(28,135);
						var marker = new naver.maps.Marker({
							position:laln,
							map:map
						});
						
					}
					//--------------------------------------------------------경로 설정-----------------------
					function setPath(i,desti){
						if(desti=="depa"){
							$('#resultJuso').html("");
							$('#depaAddr').html("출발지 = "+p.results.juso[i].roadAddr);
							AddrDep=p.results.juso[i];
							AddrDep.entX=laln.x;
							AddrDep.entY=laln.y;
						}else if(desti=="arri"){
							$('#resultJuso').html("");
							$('#arriAddr').html("목적지 = "+p.results.juso[i].roadAddr);
							AddrArv=p.results.juso[i];
							AddrArv.entX=laln.x;
							AddrArv.entY=laln.y;
						}
						if(AddrDep!=null&&AddrArv!=null){
							$('#depaAddr').html("");
							$('#depaAddr').html("");
							$('#resultJuso').html("출발지 = "+AddrDep.roadAddr   + '</br>도착지 =  '+AddrArv.roadAddr+"<input type=\"button\" value=\"경로 찾기\" onclick=\"searchPath();\">");
							
						}
						
					}
					//--------------------------------------------------------------------경로 탐색--------------------------
					//------------------------광역 경로
					var sx,sy,ex,ey;
					var shortPath;
					var trainPath;
					var times = new Array();
					function searchPath(){
						$('#depaAddr').html("");
						$('#arriAddr').html("");
						$('#resultJuso').html("출발 좌표 = "+AddrDep.entX+"  "+AddrDep.entY);
						$('#resultJuso').append("</br>도착 좌표 = "+AddrArv.entX+"  "+AddrArv.entY);
						
						var uri="https://api.odsay.com/v1/api/searchPubTransPath?&SX="+AddrDep.entX+"&SY="+AddrDep.entY+"&EX="+AddrArv.entX+"&EY="+AddrArv.entY+"&apiKey=vKj%2FKXP67Uh63gVLXld7CXB%2BFTqXtEuP20r5gpBwPw0";
						
						$.ajax({
							type:"post",
							url:uri,
							dataType:"JSON",
							asnyc:"false",
							success:function(data){
								//if(path.result.searchType==1){
									$("#resultJuso").append(uri);
									var path=JSON.parse(JSON.stringify(data));
									$("#resultJuso").append("기차 개수 = "+path.result.trainRequest.OBJ);
									var minCount=0;
									var minmax=0;
									var min = new Array(0,1,2);
									for(var ii=0;ii<3;ii++){
										if(path.result.trainRequest.OBJ[min[ii]].time>path.result.trainRequest.OBJ[min[minmax]].time){
											minmax=ii;
										}
									}
									for(var i=3;i<path.result.trainRequest.count;i++){
										if(path.result.trainRequest.OBJ[i].time<path.result.trainRequest.OBJ[min[minmax]].time){
											min[minmax]=i;
											for(var ii=0;ii<3;ii++){
												if(path.result.trainRequest.OBJ[min[ii]].time>path.result.trainRequest.OBJ[min[minmax]].time){
													minmax=ii;
												}
											}	
										}		
									}
								shortPath=path.result.trainRequest;
								//}
								$("#resultJuso").append("</br></br>최소 시간 기준 광역 3개</br>");
								for (var i=0;i<3;i++){
									sx=shortPath.OBJ[min[i]].SX; sy=shortPath.OBJ[min[i]].SY; ex=shortPath.OBJ[min[i]].EX; ey=shortPath.OBJ[min[i]].EY;
									times[i]=0;
									$("#resultJuso").append(shortPath.OBJ[min[i]].startSTN+" -> "+shortPath.OBJ[min[i]].endSTN+" 소요시간 = "+shortPath.OBJ[min[i]].time+" 열차 종류 = "+shortPath.OBJ[min[i]].trainType);
									tempTrain=shortPath.OBJ[min[i]].startSTN+" -> "+shortPath.OBJ[min[i]].endSTN+" 열차 종류 = "+shortPath.OBJ[min[i]].trainType;
									trainPath='{"trafficType":1, "totalTime":'+shortPath.OBJ[min[i]].time+
											  ',"firstStartStation":"'+shortPath.OBJ[min[i]].startSTN+
											  '", "lastEndStation":"'+shortPath.OBJ[min[i]].endSTN+
											  '", "startTime":';
									seartStartPath();
									seartEndPath();
									times[i]=tempDo();
									var result = times[i]+shortPath.OBJ[min[i]].time;
									$("#resultJuso").append("</br> 총 시간 = "+result+"</br></br>");
									$("#resultJuso").append("<input type=\"button\" value=\"상세보기\" onClick=\"showDetailedPath("+min[i]+")\" ></br>");
								}
							}
						});
						
					}
					
					var privious=null;
					//-------------------------------------------------경로의 전체 노선을 상세히 보여줌---------------
					function showDetailedPath(i){
						var time=<%= time%>;
						$("#resultJuso").append("</br>date = "+time);
			 			//alert("in");
			 			//goWbs("서울역","용산역");
			 			$('input[name="depPlaceName"]').val(shortPath.OBJ[i].startSTN);
			 			$('input[name="arrPlaceName"]').val(shortPath.OBJ[i].endSTN);
			 			$('input[name="depPlandTime"]').val(time);
			 			var str=$("#frm").serialize();
			 			var uri="http://localhost:805/Tschedule/FrontController/getCityCode.do";
			 			
			 			//alert(str);	
			 			//-------------------------------------------역 이름을 통해 역 코드를 가져옴------------------------
			 			var trainArrTime,trainDepTime;
			 			$.ajax({
			 				type:"post",
			 				data:str,
			 				url:uri,
			 				dataType:"json",
			 				async:false,
			 				success:function(data){
			 					alert("success to city code");
			 					$("#resultJuso").append("</br>data = "+data.result);
			 					$("#resultJuso").append("</br>datacode = "+data.depPlaceCode);
			 					$("#resultJuso").append("</br>datacode = "+data.arrPlaceCode);
			 					//String query = "&depPlaceId=" + request.getParameter("depPlaceId") + "&arrPlaceId="+ request.getParameter("arrPlaceId") + "&depPlandTime=";
			 					$('input[name="depPlaceId"]').val(data.depPlaceCode);
			 					$('input[name="arrPlaceId"]').val(data.arrPlaceCode);
			 				},
			 				error:function(e,error){
			 					alert(e.responseText);
			 					alert(error);
			 				}
			 			});
			 			str=$("#frm").serialize();
			 			//-------------------------------------------역 코드를 통해 기차의 출발, 도착 정보 가져옴------------------------
			 			$.ajax({
			 				type:"POST",
			 				url:"http://localhost:805/Tschedule/FrontController/search.do",
			 				dataType:"JSON",
			 				data:str,
			 				async:false,
			 				success:function(data){
			 					//alert("! success"+data);
			 					//alert("! success"+JSON.stringify(data));
			 					alert("! success 이게 시간임"+JSON.stringify(data.result[0]));
			 					trainDepTime=data.result[0].depplandtime;
			 					trainArrTime=data.result[0].arrplandtime;
			 				},
			 				error:function(error){
			 					alert("error " +error.responseText);
			 				}
			 			});
			 			
						privious= $("#resultJuso").html();
						$("#resultJuso").html("");
						var i=0;
						//----------------------------------------------------시작역->시작
						while(i<3){
							//alert("type = "+startObj.subPath[i].trafficType);
							//------------------------도보일 경우-----------------------------
							if (startObj.subPath[i].trafficType==3){
								$("#resultJuso").append("</br>"+startObj.subPath[i].distance+"m거리 "+startObj.subPath[i].sectionTime+"분 걷기");
							
							//----------------------------------------버스일 경우----------------
							}else if(startObj.subPath[i].trafficType==2){
								$("#resultJuso").append("</br>"+startObj.subPath[i].startName+"부터 "+startObj.subPath[i].endName+"까지"+startObj.subPath[i].lane[0].busNo+"번 버스 이동 <input type=\"button\" value = \"노선보기\">");
							}
							i++;
						}
						//$("#resultJuso").append(shortPath.OBJ[min[i]].startSTN+" -> "+shortPath.OBJ[min[i]].endSTN+" 소요시간 = "+shortPath.OBJ[min[i]].time+" 열차 종류 = "+shortPath.OBJ[min[i]].trainType);
						alert(trainPath);
						alert(trainDepTime);
						trainPath+= trainDepTime.substring(0,12)+"}";
						alert("2");
						$("#resultJuso").append("</br>"+tempTrain);
						alert("3");
						//alert("	time = "+trainDepTime.substring(10,12)+" 도착 = "+trainArrTime.substring(8,10));
						$("#resultJuso").append("</br>      .   "+"출발 시간 = "+trainDepTime.substring(8,10)+"시 "+trainDepTime.substring(10,12)+"분  도착시간 = "+trainArrTime.substring(8,10)+"시 "+trainArrTime.substring(10,12)+"분 ");

						i=0;
						//기차 출발, 도착 정보 받아오기
						//----------------------------------------------------도착역->도착
						while(i<3){
							alert("type = "+endObj.subPath[i].trafficType);
							if (endObj.subPath[i].trafficType==3){
								$("#resultJuso").append("</br>"+endObj.subPath[i].distance+"m거리 "+endObj.subPath[i].sectionTime+"분 걷기");
							}else if(endObj.subPath[i].trafficType==2){
								$("#resultJuso").append("</br>"+endObj.subPath[i].startName+"부터 "+endObj.subPath[i].endName+"까지"+endObj.subPath[i].lane[0].busNo+"번 버스 이동 <input type=\"button\" value = \"노선보기\">");
							}
							i++;
						}
						$("#resultJuso").append("<input type=\"button\" id = \"goBack\" value=\"뒤로가기\"onClick=\"goBack();\" ></br>");
						$("#resultJuso").append("<input type=\"button\" id = \"savePath\" value=\"이 경로 사용하기\"></br>");
				
					}
					function goBack(){
						$("#resultJuso").html(privious);
					}
					$(document).on("click","#savePath",function(){
						alert("in save path");
						$('input[name="startPath"]').val(JSON.stringify(startObj));
						$('input[name="middlePath"]').val(trainPath);
						$('input[name="endPath"]').val(JSON.stringify(endObj));
						//$('#resultJuso').html("");
						//$('#resultJuso').append("시작 = "+$('input[name="startPath"]').val());
						$("#path").submit();
						//var str=$("#path").serialize();
					});
					function tempDo(){
						var time=0;
						var i=0;
						//----------------------------------------------------시작역->시작
						while(i<3){
							//alert("type = "+startObj.subPath[i].trafficType);
							if (startObj.subPath[i].trafficType==3){
								time+=startObj.subPath[i].sectionTime;
								
							}else if(startObj.subPath[i].trafficType==2){
								
								time += startObj.subPath[i].sectionTime;
							}
							i++;
						}
						//$("#resultJuso").append(shortPath.OBJ[min[i]].startSTN+" -> "+shortPath.OBJ[min[i]].endSTN+" 소요시간 = "+shortPath.OBJ[min[i]].time+" 열차 종류 = "+shortPath.OBJ[min[i]].trainType);
						i=0;
						//----------------------------------------------------도착역->도착
						while(i<3){
							//alert("type = "+startObj.subPath[i].trafficType);
							if (endObj.subPath[i].trafficType==3){
								time += endObj.subPath[i].sectionTime;
							}else if(endObj.subPath[i].trafficType==2){
								time += endObj.subPath[i].sectionTime;
							}
							i++;
						}
						//$("#resultJuso").append("</br> <input type=\"button\" value = \"노선선택\" onClick=\"location.href='FrontController/search.do?depPlaceId=\"++\"'\")>");
						return time;
					}
					function selectPath(){}
					var startObj=null;
					var endObj=null;
					function seartStartPath(){
							//------------------------시작->시작역 경로
						var uri="https://api.odsay.com/v1/api/searchPubTransPath?&SX=" +AddrDep.entX+"&SY="+AddrDep.entY+"&EX="+sx+"&EY="+sy+"&apiKey=vKj%2FKXP67Uh63gVLXld7CXB%2BFTqXtEuP20r5gpBwPw0";
						$("#resultJuso").append("</br> 시작 uri  = "+uri);
						$.ajax({
							type:"post",
							dataType:"JSON",
							url:uri,
							async:false,
							success:function(data){
								
								var paths=JSON.parse(JSON.stringify(data));
								var pt = paths.result;
								var min=9000;
								var minIdx=0;
								//alert("됨?"+pt.path[0].info.totalTime);
								for(var i=0;i<paths.result.busCount;i++){
									//alert("됨됨? min = "+min+" path i = "+pt.path[i].info.totalTime);
									if(pt.path[i].info.totalTime<min) {
										min=pt.path[i].info.totalTime;
										minIdx=i;
									}
								}
								startObj=pt.path[minIdx];
								}
	
						});
					}
					function seartEndPath(){
						//------------------------도착역->도착 경로
						var uri="https://api.odsay.com/v1/api/searchPubTransPath?&SX=" +ex+ "&SY="+ey+"&EX="+AddrArv.entX+"&EY="+AddrArv.entY+"&apiKey=vKj%2FKXP67Uh63gVLXld7CXB%2BFTqXtEuP20r5gpBwPw0";
						$("#resultJuso").append("</br> 도착 uri  = "+uri);
						$.ajax({
							type:"post",
							dataType:"JSON",
							url:uri,
							async:false,
							success:function(data){
								
								var paths=JSON.parse(JSON.stringify(data));
								var pt = paths.result;
								var min=9000;
								
								var minIdx=0;
								//alert("됨?"+pt.path[0].info.totalTime);
								for(var i=0;i<paths.result.busCount;i++){
									//alert("됨됨? min = "+min+" path i = "+pt.path[i].info.totalTime);
									if(pt.path[i].info.totalTime<min) {
										min=pt.path[i].info.totalTime;
										minIdx=i;
									}
									}
								endObj=pt.path[minIdx];
								}
						});
					}
					var mapElement = map.getElement();
	
			</script>
	<div id="depaAddr"></div>
	<div id="arriAddr">
	</div>
	
	<div id="resultJuso"></div>
	<div id="temp"></div>
</body>
</html>
