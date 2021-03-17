package com.njusc.npm.service.dto.converter;

public class GrantView {

	public GrantView() {
	}

	public GrantView(String strDate, String proName, int proId, int grantBatch,
			String detail, String total) {
		this.strDate = strDate;
		this.proName = proName;
		this.proId = proId;
		this.grantBatch = grantBatch;
		this.detail = detail;
		this.total = total;
	}

	private String fName;
	private String fSex;
	private String idNo;
	private String enName;
	private String grName;

	private String strDate; // 发放日期

	private String proName; // 补贴项目

	private int proId; // 项目id，不展示

	private int grantBatch; // 发放批次

	private String detail; // 明细

	private String total; // 合计

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getGrantBatch() {
		return grantBatch;
	}

	public void setGrantBatch(int grantBatch) {
		this.grantBatch = grantBatch;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getGrName() {
		return grName;
	}

	public void setGrName(String grName) {
		this.grName = grName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getfSex() {
		return fSex;
	}

	public void setfSex(String fSex) {
		this.fSex = fSex;
	}

}
