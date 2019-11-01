package com.aio.ex.dto;

public class StationInfoDTO {
	String stationCode;
	String stationName;
	float coordX;
	float coordY;
	public StationInfoDTO(String stationCode, String stationName, float coordX, float coordY) {
		this.stationCode=stationCode;
		this.stationName=stationName;
		this.coordX=coordX;
		this.coordY=coordY;
	}
	public StationInfoDTO() {
		this.stationCode=null;
		this.stationName=null;
		this.coordX=0;
		this.coordY=0;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public float getCoordX() {
		return coordX;
	}
	public void setCoordX(float coordX) {
		this.coordX = coordX;
	}
	public float getCoordY() {
		return coordY;
	}
	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}

}
