package com.aio.ex.dto;

import org.w3c.dom.NodeList;

public class TrainInfoDTO {
	String arrplandtime;
	String depplandtime;
	String adultcharge;
	String traingradename;
	String arrplacename;
	String depplacename;
	public TrainInfoDTO(String arrplandtime, String depplandtime, String adultcharge, String traingradename, String arrplacename, String depplacename) {
		this.arrplacename=arrplacename;
		this.depplacename=depplacename;
		this.adultcharge = adultcharge;
		this.arrplandtime=arrplandtime;
		this.depplandtime=depplandtime;
		this.traingradename=traingradename;
	}
	public TrainInfoDTO(NodeList arrplandtime, NodeList depplandtime, NodeList adultcharge, NodeList traingradename,
			NodeList arrplacename, NodeList depplacename,int i) {
		
		this.arrplacename=arrplacename.item(i).getTextContent();
		this.depplacename=depplacename.item(i).getTextContent();
		this.adultcharge = adultcharge.item(i).getTextContent();
		this.arrplandtime=arrplandtime.item(i).getTextContent();
		this.depplandtime=depplandtime.item(i).getTextContent();
		this.traingradename=traingradename.item(i).getTextContent();
	}
	public String getArrplandtime() {
		return arrplandtime;
	}
	public void setArrplandtime(String arrplandtime) {
		this.arrplandtime = arrplandtime;
	}
	public String getDepplandtime() {
		return depplandtime;
	}
	public void setDepplandtime(String depplandtime) {
		this.depplandtime = depplandtime;
	}
	public String getAdultcharge() {
		return adultcharge;
	}
	public void setAdultcharge(String adultcharge) {
		this.adultcharge = adultcharge;
	}
	public String getTraingradename() {
		return traingradename;
	}
	public void setTraingradename(String traingradename) {
		this.traingradename = traingradename;
	}
	public String getArrplacename() {
		return arrplacename;
	}
	public void setArrplacename(String arrplacename) {
		this.arrplacename = arrplacename;
	}
	public String getDepplacename() {
		return depplacename;
	}
	public void setDepplacename(String depplacename) {
		this.depplacename = depplacename;
	}
}
