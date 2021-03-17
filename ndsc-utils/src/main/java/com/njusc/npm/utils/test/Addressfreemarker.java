package com.njusc.npm.utils.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 拼接省市县的数据成固定格式字符串
 * */
public class Addressfreemarker {
	
	public static String getAllAddress() throws ClassNotFoundException, SQLException {
		System.out.println("J".charAt(0)<"G".charAt(0));
		
		StringBuffer x86 = new StringBuffer();
		Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection("jdbc:mysql://10.88.2.8:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","njusc");
    	String sql1 ="select * from yfs_area where parentid = '000000'";
    	PreparedStatement selectstat =  conn.prepareStatement(sql1);
    	ResultSet rs = selectstat.executeQuery();
    	StringBuffer A_G = new StringBuffer("'A-G': [");
    	StringBuffer H_K = new StringBuffer("'H-K': [");
    	StringBuffer L_S = new StringBuffer("'L-S': [");
    	StringBuffer T_Z = new StringBuffer("'T-Z': [");
    	while(rs.next()){
    		if(rs.getString("firstchar").charAt(0)<='G'){
    			A_G.append("{code: '").append(rs.getString("areacode")).append("', address: '").append(rs.getString("areaname")).append("'").append("},");
    		}else if(rs.getString("firstchar").charAt(0)>='H' && rs.getString("firstchar").charAt(0)<='K'){
    			H_K.append("{code: '").append(rs.getString("areacode")).append("', address: '").append(rs.getString("areaname")).append("'").append("},");
    		}else if(rs.getString("firstchar").charAt(0)>='L' && rs.getString("firstchar").charAt(0)<='S'){
    			L_S.append("{code: '").append(rs.getString("areacode")).append("', address: '").append(rs.getString("areaname")).append("'").append("},");
    		}else if(rs.getString("firstchar").charAt(0)>='T'){
    			T_Z.append("{code: '").append(rs.getString("areacode")).append("', address: '").append(rs.getString("areaname")).append("'").append("},");
    		}
    	}
    	
    	A_G.append("]");
    	H_K.append("]");
    	L_S.append("]");
    	T_Z.append("]");
    	x86.append("86: {")
    		.append(A_G.toString().replace(",]", "]")).append(",")
    		.append(H_K.toString().replace(",]", "]")).append(",")
    		.append(L_S.toString().replace(",]", "]")).append(",")
    		.append(T_Z.toString().replace(",]", "]"))
    		.append("}");
    	//依次查询省市县镇的数据，并将各自的子集放入到对象中
    	String sql2 ="select * from yfs_area where cj>0 and cj<3";
    	String sql3 ="select * from yfs_area where parentid=?";
    	PreparedStatement prep =  conn.prepareStatement(sql3);
    	selectstat =  conn.prepareStatement(sql2);
    	rs = selectstat.executeQuery();
    	while(rs.next()){
    		StringBuffer sb = new StringBuffer();
    		sb.append(rs.getString("areacode")).append(":{");
    		prep.setString(1, rs.getString("areacode"));
    		ResultSet rs2 = prep.executeQuery();
    		while(rs2.next()){
    			sb.append(rs2.getString("areacode")).append(": '").append(rs2.getString("areaname")).append("',");
    		}
    		sb.append("}");
    		x86.append(",").append(sb.toString().replace(",}", "}"));
    	}
    	String areas = "{"+x86.toString()+"}";
    	return areas;
    	
	}
	
	
	
}
