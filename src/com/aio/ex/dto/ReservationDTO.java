package com.aio.ex.dto;

public class ReservationDTO {
	String reservationNum;
	String trafficType;
	String arrTime;
	String depTime;
	String arrStationName;
	String depStationName;
	public String getReservationNum() {
		return reservationNum;
	}
	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}
	public String getTrafficType() {
		return trafficType;
	}
	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getArrStationName() {
		return arrStationName;
	}
	public void setArrStationName(String arrStationName) {
		this.arrStationName = arrStationName;
	}
	public String getDepStationName() {
		return depStationName;
	}
	public void setDepStationName(String depStationName) {
		this.depStationName = depStationName;
	}
	
}
