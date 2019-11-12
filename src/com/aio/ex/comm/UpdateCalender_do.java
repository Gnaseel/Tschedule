package com.aio.ex.comm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aio.ex.parsing.AIOParsing;
@WebServlet("/UpdateCalender_do")
public class UpdateCalender_do implements TCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------In UpdateCalenderDo----------------------------------------");
	
		System.out.println("-----------------------------Out UpdateCalenderDo----------------------------------------");
	}

}
