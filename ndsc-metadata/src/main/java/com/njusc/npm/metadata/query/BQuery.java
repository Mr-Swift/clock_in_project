package com.njusc.npm.metadata.query;

public class BQuery {
 private String unitid;//单位id
 private String unitname;//
 private String unitallpath;//单位allpath
 private String djlx;//登记类型
 private String jzs;//鉴证书
 private String lzht;//流转合同
 private String qlzs;//权利证书
 private String dydjh;//抵押登记号
 private String dyr;//抵押人
 private String dyrzjh;//抵押人证件号
 private String status;//状态
 private String starttime;//开始时间
 private String endtime;//结束时间
 private String mortgageName; //登记名称
 
 
 //历史抵押物信息
 private String dysj;//抵押时间
 private String dyyh;//抵押银行
 private String dyje;//抵押金额
 
 //历史抵押还款情况
 private String lshkqk;//历史还款情况
 
 
public String getDysj() {
	return dysj;
}
public void setDysj(String dysj) {
	this.dysj = dysj;
}
public String getDyyh() {
	return dyyh;
}
public void setDyyh(String dyyh) {
	this.dyyh = dyyh;
}
public String getDyje() {
	return dyje;
}
public void setDyje(String dyje) {
	this.dyje = dyje;
}
public String getLshkqk() {
	return lshkqk;
}
public void setLshkqk(String lshkqk) {
	this.lshkqk = lshkqk;
}
public String getUnitid() {
	return unitid;
}
public void setUnitid(String unitid) {
	this.unitid = unitid;
}
public String getUnitallpath() {
	return unitallpath;
}
public void setUnitallpath(String unitallpath) {
	this.unitallpath = unitallpath;
}
public String getDjlx() {
	return djlx;
}
public void setDjlx(String djlx) {
	this.djlx = djlx;
}
public String getJzs() {
	return jzs;
}
public void setJzs(String jzs) {
	this.jzs = jzs;
}
public String getLzht() {
	return lzht;
}
public void setLzht(String lzht) {
	this.lzht = lzht;
}
public String getQlzs() {
	return qlzs;
}
public void setQlzs(String qlzs) {
	this.qlzs = qlzs;
}
public String getDydjh() {
	return dydjh;
}
public void setDydjh(String dydjh) {
	this.dydjh = dydjh;
}
public String getDyr() {
	return dyr;
}
public void setDyr(String dyr) {
	this.dyr = dyr;
}
public String getDyrzjh() {
	return dyrzjh;
}
public void setDyrzjh(String dyrzjh) {
	this.dyrzjh = dyrzjh;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getStarttime() {
	return starttime;
}
public void setStarttime(String starttime) {
	this.starttime = starttime;
}
public String getEndtime() {
	return endtime;
}
public void setEndtime(String endtime) {
	this.endtime = endtime;
}
public String getUnitname() {
	return unitname;
}
public void setUnitname(String unitname) {
	this.unitname = unitname;
}
public String getMortgageName() {
	return mortgageName;
}
public void setMortgageName(String mortgageName) {
	this.mortgageName = mortgageName;
}


 
}
