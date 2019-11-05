package com.aio.ex.comm;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aio.ex.dto.*;
import com.aio.ex.parsing.*;
public class Search_Do implements TCommand {
	@Override
	
	//-------------------------------------------------------열차 정보를 검색
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------In SearchDo----------------------------------------");
		System.out.println("dpe time = "+request.getParameter("depPlandTime"));
		String query = "&depPlaceId=" + request.getParameter("depPlaceId") + "&arrPlaceId="+ request.getParameter("arrPlaceId") + "&depPlandTime=";
		int depTime=Integer.parseInt(request.getParameter("depPlandTime").substring(8,12));
		System.out.println(depTime);
		AIOParsing par=new AIOParsing(); 
		par.ParseTrainInfo(query,request);
		try {
			//response.getWriter().print(request.getAttribute("TrainArray"));
			
			ArrayList<TrainInfoDTO> arr=(ArrayList<TrainInfoDTO>) request.getAttribute("TrainArray");
			
			JSONObject obj = new JSONObject();
			JSONArray jArray=new JSONArray();
			
			for(int i=0;i<arr.size();i++) {
				//System.out.println(" 출발시간= "+arr.get(i).getDepplandtime());
				System.out.println(" 출발시간sub= "+arr.get(i).getDepplandtime().substring(8,12));
				if(Integer.parseInt(arr.get(i).getDepplandtime().substring(8,12))  >= depTime ) {
					JSONObject train = new JSONObject();
					System.out.println(" 출발시간= "+arr.get(i).getDepplandtime());
					train.put("adultcharge", arr.get(i).getAdultcharge());
					train.put("arrplacename", arr.get(i).getArrplacename());
					train.put("arrplandtime", arr.get(i).getArrplandtime());
					train.put("depplacename", arr.get(i).getDepplacename());
					train.put("depplandtime", arr.get(i).getDepplandtime());
					train.put("traingradename", arr.get(i).getTraingradename());
					jArray.add(train);
				}
				
			}
			obj.put("result", jArray);
			response.getWriter().print(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----------------------------Out SearchDo----------------------------------------");
		return;
	}
}
