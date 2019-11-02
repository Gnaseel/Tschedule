package com.aio.ex.dto;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StationInfoDTO {
	String stationCode=null;
	String stationName=null;
	String coordX=null;
	String coordY=null;
	public StationInfoDTO(String stationCode, String stationName, String coordX, String coordY) {
		this.stationCode=stationCode;
		this.stationName=stationName;
		this.coordX=coordX;
		this.coordY=coordY;
	}
	public StationInfoDTO(Node stationCode, Node stationName, Node coordX, Node coordY) {
		if(stationCode!=null)
		this.stationCode=stationCode.getTextContent();
		if(stationName!=null)
		this.stationName=stationName.getTextContent();
		if(coordX!=null)
		this.coordX=coordX.getTextContent();
		if(coordY!=null)
		this.coordY=coordY.getTextContent();
	}
	public StationInfoDTO() {
		this.stationCode=null;
		this.stationName=null;
		this.coordX=null;
		this.coordY=null;
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
	public String getCoordX() {
		return coordX;
	}
	public void setCoordX(String coordX) {
		this.coordX = coordX;
	}
	public String getCoordY() {
		return coordY;
	}
	public void setCoordY(String coordY) {
		this.coordY = coordY;
	}

}
