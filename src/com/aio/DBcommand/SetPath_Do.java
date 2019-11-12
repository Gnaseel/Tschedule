package com.aio.DBcommand;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aio.ex.comm.TCommand;

@WebServlet("/SetPath_Do")
public class SetPath_Do implements TCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------In SignUpDo----------------------------------------");	
		
		System.out.println("-----------------------------Out SignUpDo----------------------------------------");	
	}
	
}
