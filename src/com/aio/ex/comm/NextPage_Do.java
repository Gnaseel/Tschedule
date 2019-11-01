package com.aio.ex.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NextPage_Do implements TCommand{
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------In NextPageDo----------------------------------------");
		if(request.getAttribute("pageNum")==null)
			System.out.println("NULL!!!");
		System.out.println("page num = "+request.getAttribute("pageNum").toString());
		int pageNum = (int)request.getAttribute("pageNum");
		pageNum++;
		request.setAttribute("pageNum", pageNum);
		
	}
}
