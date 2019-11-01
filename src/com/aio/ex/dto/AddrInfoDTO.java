package com.aio.ex.dto;

public class AddrInfoDTO {
	String roadAddr;	//전체 도로명 주소
	String admCd;		//행정구역 코드
	String rnMgtSn;		//도로명 코드
	String udrtYn;		//지하여부
	String buldMnnm;	//건물 본번
	String buldSlno;	//건물 부번
	public String getRoadAddr() {
		return roadAddr;
	}
	public AddrInfoDTO(String roadAddr, String admCd, String rnMgtSn, String udrtYn, String buldMnnm, String buldSlno) {
		this.roadAddr = roadAddr;
		this.admCd = admCd;
		this.rnMgtSn = rnMgtSn;
		this.udrtYn = udrtYn;
		this.buldMnnm = buldMnnm;
		this.buldSlno = buldSlno;
	}
	public void setRoadAddr(String roadAddr) {
		this.roadAddr = roadAddr;
	}
	public String getAdmCd() {
		return admCd;
	}
	public void setAdmCd(String admCd) {
		this.admCd = admCd;
	}
	public String getRnMgtSn() {
		return rnMgtSn;
	}
	public void setRnMgtSn(String rnMgtSn) {
		this.rnMgtSn = rnMgtSn;
	}
	public String getUdrtYn() {
		return udrtYn;
	}
	public void setUdrtYn(String udrtYn) {
		this.udrtYn = udrtYn;
	}
	public String getBuldMnnm() {
		return buldMnnm;
	}
	public void setBuldMnnm(String buldMnnm) {
		this.buldMnnm = buldMnnm;
	}
	public String getBuldSlno() {
		return buldSlno;
	}
	public void setBuldSlno(String buldSlno) {
		this.buldSlno = buldSlno;
	}
}

/*
 * confmKey String Y - 신청시 발급받은 승인키 
 * admCd String Y - 행정구역코드 
 * rnMgtSn String Y - 도로명코드 
 * udrtYn String Y - 지하여부(0 : 지상, 1 : 지하) 
 * buldMnnm Number Y - 건물본번 
 * buldSlno Number Y - 건물부*/
