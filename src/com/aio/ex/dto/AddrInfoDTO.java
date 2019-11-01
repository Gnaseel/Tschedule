package com.aio.ex.dto;

public class AddrInfoDTO {
	String roadAddr;	//��ü ���θ� �ּ�
	String admCd;		//�������� �ڵ�
	String rnMgtSn;		//���θ� �ڵ�
	String udrtYn;		//���Ͽ���
	String buldMnnm;	//�ǹ� ����
	String buldSlno;	//�ǹ� �ι�
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
 * confmKey String Y - ��û�� �߱޹��� ����Ű 
 * admCd String Y - ���������ڵ� 
 * rnMgtSn String Y - ���θ��ڵ� 
 * udrtYn String Y - ���Ͽ���(0 : ����, 1 : ����) 
 * buldMnnm Number Y - �ǹ����� 
 * buldSlno Number Y - �ǹ���*/
