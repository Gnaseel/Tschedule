package com.aio.DBcommand;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aio.ex.comm.TCommand;
import com.aio.ex.dto.*;
import com.google.gson.*;

@WebServlet("/GetPath_Do")
public class GetPath_Do implements TCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-----------------------------In GetPathDo----------------------------------------");
		JsonArray jArr = new JsonArray();
		try {
			HttpSession session = request.getSession();
			Connection con;
			Statement stmt;
			Statement subStmt;
			ResultSet rSet;
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1511");
			stmt = con.createStatement();
			subStmt=con.createStatement();
			String getListQuery = "select num from reservationList where id = '" + session.getAttribute("id") + "'";
			System.out.println("query = " + getListQuery);
			rSet = stmt.executeQuery(getListQuery);

			int i = 0;
			
			while (rSet.next()) {
				ReservationDTO rDTO = new ReservationDTO();
				String rsvNum = rSet.getString("num");
				ResultSet subRSet;
				
				String subQuery = "select * from reservationPath where num = '" + rsvNum + "'";
				System.out.println("subQuery = " + rsvNum);
				subRSet = subStmt.executeQuery(subQuery);

				subRSet.next();
				rDTO.setReservationNum(subRSet.getString(1));
				rDTO.setTrafficType(subRSet.getString(3));
				rDTO.setArrTime(subRSet.getString(4));
				rDTO.setArrStationName(subRSet.getString(5));
				rDTO.setDepStationName(subRSet.getString(6));

				Gson gson = new Gson();

				String json = gson.toJson(rDTO);
				System.out.println(json);
				jArr.add(json);
			}
			System.out.println(jArr.toString());
		} catch (Exception e) {
			System.out.println("¾ÈµÅ~ "+e.getMessage());
		}
		request.setAttribute("pathLog", jArr);
		System.out.println("-----------------------------out GetPathDo----------------------------------------");
	}

}
