package com.njusc.npm.metadata.query;

public class SecurityQuery {
private String msg;
private String code;
public SecurityQuery(String msg,String code){
	this.msg=msg;
	this.code=code;
}
public SecurityQuery(){}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}


}
