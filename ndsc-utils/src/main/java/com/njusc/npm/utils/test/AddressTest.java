package com.njusc.npm.utils.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 抓取国家统计局的省市县镇村的区划代码，并写入到数据库中
 * 
 * */
public class AddressTest {
    static String starturl="http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2015";
    static String first="/index";
    static String endpiv=".html";
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//    	province();
//    	city();
//    	county();
//    	System.out.println("120200000000".substring(0, 6));
//    	town();
//    	village();
    }
    
    /**
     * 村
     * */
    public static void village() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","sa");
    	Connection selectconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","sa");
//    	String selectsql = "select * from yfs_area1 where cj=4 and url is not null";
    	String selectsql = "select * from yfs_area1 t where cj =4  and not exists (" +
    			" select 1 from yfs_area1 t2 where cj =5 and t.areaid=t2.parentid)";
    	String sql = "insert into yfs_area1(areaid,areaname,areacode,ordercode,parentid,isdel,url,allpath,cj)" +
    			" values (?,?,?,?,?,?,?,?,?)";
    	PreparedStatement selectstat =  selectconn.prepareStatement(selectsql);
    	PreparedStatement stat =  conn.prepareStatement(sql);
    	ResultSet rs = selectstat.executeQuery();
    	while(rs.next()){
    		Document doc;
    		try {
    			try {
					doc = Jsoup.connect(rs.getString("url")).get();
				} catch (Exception e) {
					doc = Jsoup.connect(rs.getString("url")).get();
				}
    			Elements ListDiv = doc.getElementsByAttributeValue("class","villagetr");
    			int ordercode =0;
    			for (Element element :ListDiv) {
    				Elements links = element.getElementsByTag("td");
    				if(links!=null && links.size()>0){
    					JsoupArea area = new JsoupArea();
    					
    					Element link =links.get(0);
    					String code = link.text().trim();//地区编码
    					link =links.get(links.size()-1);
    					String linkText = link.text().trim();//地区名称
    					
    					area.setAreaid(code);
    					area.setAreaname(linkText);
    					area.setAreacode(code);
    					area.setOrdercode(ordercode);
    					area.setParentid(rs.getString("areacode"));
    					area.setUrl("hasNoUrl");
    					area.setAllpath(rs.getString("allpath")+code+"|");
    					area.setCj(rs.getInt("cj")+1);
    					ordercode++;
    					
    					
    					stat.setObject(1, area.getAreaid());
    					stat.setObject(2, area.getAreaname());
    					stat.setObject(3, area.getAreacode());
    					stat.setObject(4, area.getOrdercode());
    					stat.setObject(5, area.getParentid());
    					stat.setObject(6, 0);
    					stat.setObject(7, area.getUrl());
    					stat.setObject(8, area.getAllpath());
    					stat.setObject(9, area.getCj());
    					stat.execute();
    				}
    			}
    		} catch (IOException e) {
    			System.out.println(rs.getString("areaid")+rs.getString("areaname")+rs.getString("url")+"      失败");
    			e.printStackTrace();
    		}
    	}
		
        selectstat.close();
        selectconn.close();
        stat.close();
        conn.close();
    }
    
    /**
     * 乡镇街道
     * */
    public static void town() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","sa");
    	Connection selectconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","sa");
    	String selectsql = "select * from yfs_area1 where cj=3 and url is not null";
//    	String selectsql = "select * from yfs_area1 t where cj =3 and url is not null and not exists (" +
//    			" select 1 from yfs_area1 t2 where t.areaid=t2.parentid)";
    	String sql = "insert into yfs_area1(areaid,areaname,areacode,ordercode,parentid,isdel,url,allpath,cj)" +
    			" values (?,?,?,?,?,?,?,?,?)";
    	PreparedStatement selectstat =  selectconn.prepareStatement(selectsql);
    	PreparedStatement stat =  conn.prepareStatement(sql);
    	ResultSet rs = selectstat.executeQuery();
    	while(rs.next()){
    		System.out.println(rs.getString("areaid")+rs.getString("areaname"));
    		Document doc;
    		try {
    			doc = Jsoup.connect(rs.getString("url")).get();
    			Elements ListDiv = doc.getElementsByAttributeValue("class","towntr");
    			int ordercode =0;
    			for (Element element :ListDiv) {
    				Elements links = element.getElementsByTag("a");
    				if(links!=null && links.size()>0){
    					JsoupArea area = new JsoupArea();
    					
    					Element link =links.get(0);
    					String code = link.text().trim();//地区编码
    					link =links.get(links.size()-1);
    					String linkText = link.text().trim();//地区名称
    					//市级
    					code = code.substring(0,9);
    					String linkHref = link.attr("href");
    					
    					area.setAreaid(code);
    					area.setAreaname(linkText);
    					area.setAreacode(code);
    					area.setOrdercode(ordercode);
    					area.setParentid(rs.getString("areacode"));
    					area.setUrl(starturl+"/"+rs.getString("areaid").substring(0, 2)+"/"+rs.getString("areaid").substring(2, 4)+"/"+linkHref);//02/320102003.html   /32/01/02/320102003.html
    					area.setAllpath(rs.getString("allpath")+code+"|");
    					area.setCj(rs.getInt("cj")+1);
    					ordercode++;
    					
    					
    					stat.setObject(1, area.getAreaid());
    					stat.setObject(2, area.getAreaname());
    					stat.setObject(3, area.getAreacode());
    					stat.setObject(4, area.getOrdercode());
    					stat.setObject(5, area.getParentid());
    					stat.setObject(6, 0);
    					stat.setObject(7, area.getUrl());
    					stat.setObject(8, area.getAllpath());
    					stat.setObject(9, area.getCj());
    					stat.execute();
    				}
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
		
        selectstat.close();
        selectconn.close();
        stat.close();
        conn.close();
    }
    
    /**
     * 区县
     * */
    public static void county() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","sa");
    	Connection selectconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","sa");
    	String selectsql = "select * from yfs_area1 where cj=2";
    	
    	String sql = "insert into yfs_area1(areaid,areaname,areacode,ordercode,parentid,isdel,url,allpath,cj)" +
    			" values (?,?,?,?,?,?,?,?,?)";
    	PreparedStatement selectstat =  selectconn.prepareStatement(selectsql);
    	PreparedStatement stat =  conn.prepareStatement(sql);
    	ResultSet rs = selectstat.executeQuery();
    	while(rs.next()){
    		System.out.println(rs.getString("areaid"));
    		Document doc;
    		try {
    			doc = Jsoup.connect(rs.getString("url")).get();
    			Elements ListDiv = doc.getElementsByAttributeValue("class","countytr");
    			int ordercode =0;
    			for (Element element :ListDiv) {
    				Elements links = element.getElementsByTag("a");
    				JsoupArea area = new JsoupArea();
    				if(links==null ||links.size()<1){
                    	links = element.getElementsByTag("td");
                    	Element link =links.get(0);
                    	String code = link.text().trim();//地区编码
                    	link =links.get(links.size()-1);
                    	String linkText = link.text().trim();//地区名称
                    	//市级
                    	code = code.substring(0, 6);
                    	area.setAreaid(code);
                    	area.setAreaname(linkText);
                    	area.setAreacode(code);
                    	area.setOrdercode(ordercode);
                    	area.setParentid(rs.getString("areacode"));
                    	area.setAllpath(rs.getString("allpath")+code+"|");
                    	area.setCj(rs.getInt("cj")+1);
                    }else{
                    	Element link =links.get(0);
                    	String code = link.text().trim();//地区编码
                    	link =links.get(links.size()-1);
                    	String linkText = link.text().trim();//地区名称
                    	//市级
                    	code = code.substring(0, 6);
                    	String linkHref = link.attr("href");
                    	area.setAreaid(code);
                    	area.setAreaname(linkText);
                    	area.setAreacode(code);
                    	area.setOrdercode(ordercode);
                    	area.setParentid(rs.getString("areacode"));
                    	area.setUrl(starturl+"/"+rs.getString("parentid").substring(0, 2)+"/"+linkHref);// 01/320102.html   /32/01/320102.html
                    	area.setAllpath(rs.getString("allpath")+code+"|");
                    	area.setCj(rs.getInt("cj")+1);
                    }
    				ordercode++;
                    
    					
					stat.setObject(1, area.getAreaid());
					stat.setObject(2, area.getAreaname());
					stat.setObject(3, area.getAreacode());
					stat.setObject(4, area.getOrdercode());
					stat.setObject(5, area.getParentid());
					stat.setObject(6, 0);
					stat.setObject(7, area.getUrl());
					stat.setObject(8, area.getAllpath());
					stat.setObject(9, area.getCj());
					stat.execute();
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
		
        selectstat.close();
        selectconn.close();
        stat.close();
        conn.close();
    }
    /**
     * 市级
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void city() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","sa");
    	Connection selectconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","sa");
    	String selectsql = "select * from yfs_area1 where cj=1";
    	
    	String sql = "insert into yfs_area1(areaid,areaname,areacode,ordercode,parentid,isdel,url,allpath,cj)" +
    			" values (?,?,?,?,?,?,?,?,?)";
    	PreparedStatement selectstat =  selectconn.prepareStatement(selectsql);
    	PreparedStatement stat =  conn.prepareStatement(sql);
    	ResultSet rs = selectstat.executeQuery();
    	while(rs.next()){
    		System.out.println(rs.getString("areaid"));
    		Document doc;
    		try {
    			doc = Jsoup.connect(rs.getString("url")).get();
    			Elements ListDiv = doc.getElementsByAttributeValue("class","citytr");
    			int ordercode =0;
    			for (Element element :ListDiv) {
    				Elements links = element.getElementsByTag("a");
    				Element link =links.get(0);
                    String code = link.text().trim();//地区编码
                    link =links.get(links.size()-1);
                    String linkText = link.text().trim();//地区名称
                    String linkHref = link.attr("href");
                    JsoupArea area = new JsoupArea();
                	//市级
                	code = code.substring(0, 6);
                	
                    area.setAreaid(code);
                    area.setAreaname(linkText);
                    area.setAreacode(code);
                    area.setOrdercode(ordercode);
                    area.setParentid(rs.getString("areacode"));
                    area.setUrl(starturl+"/"+linkHref);//32/3201.html
                    area.setAllpath(rs.getString("allpath")+code+"|");
                    area.setCj(rs.getInt("cj")+1);
                    ordercode++;
    					
					stat.setObject(1, area.getAreaid());
					stat.setObject(2, area.getAreaname());
					stat.setObject(3, area.getAreacode());
					stat.setObject(4, area.getOrdercode());
					stat.setObject(5, area.getParentid());
					stat.setObject(6, 0);
					stat.setObject(7, area.getUrl());
					stat.setObject(8, area.getAllpath());
					stat.setObject(9, area.getCj());
					stat.execute();
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
		
        selectstat.close();
        selectconn.close();
        stat.close();
        conn.close();
    }
    /**
     * 根据首页地址，解析出省份
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public static void province() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wqlwc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull"
    			,"root","sa");
    	String sql = "insert into yfs_area1(areaid,areaname,areacode,ordercode,parentid,isdel,url,allpath,cj)" +
    			" values (?,?,?,?,?,?,?,?,?)";
    	PreparedStatement stat =  conn.prepareStatement(sql);
		
        Document doc;
        try {
            doc = Jsoup.connect(starturl+first+endpiv).get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","provincetr");
            int ordercode =0;
            for (Element element :ListDiv) {
                Elements links = element.getElementsByTag("a");
                for (Element link : links) {
                    String linkHref = link.attr("href");//请求地址及地区编码
                    String linkText = link.text().trim();//省
                    JsoupArea area = new JsoupArea();
                    String areaid = linkHref.replace(".html", "");
                    area.setAreaid(areaid+"0000");
                    area.setAreaname(linkText);
                    area.setAreacode(areaid+"0000");
                    area.setOrdercode(ordercode);
                    area.setParentid("000000");
                    area.setUrl(starturl+"/"+linkHref);
                    area.setCj(1);
                    area.setAllpath("000000|"+areaid+"0000|");
                    ordercode++;
                    
                    stat.setObject(1, area.getAreaid());
            		stat.setObject(2, area.getAreaname());
            		stat.setObject(3, area.getAreacode());
            		stat.setObject(4, area.getOrdercode());
            		stat.setObject(5, area.getParentid());
            		stat.setObject(6, 0);
            		stat.setObject(7, area.getUrl());
            		stat.setObject(8, area.getAllpath());
            		stat.setObject(9, area.getCj());
            		stat.execute();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stat.close();
        conn.close();
    }

	static class JsoupArea{
		private String areaid;
		private String areaname;
		private String areacode;
		private int ordercode;
		private String parentid;
		private String url;
		private String allpath;
		private int cj;
		
		public JsoupArea() {
		}
		@Override
		public String toString() {
			return areaid+"|"+areaname+"|"+areacode+"|"+ordercode+"|"+parentid+"|"+url+"|"+cj+"|   "+allpath;
		}
		public String getAreaid() {
			return areaid;
		}
		public void setAreaid(String areaid) {
			this.areaid = areaid;
		}
		public String getAreaname() {
			return areaname;
		}
		public void setAreaname(String areaname) {
			this.areaname = areaname;
		}
		public String getAreacode() {
			return areacode;
		}
		public void setAreacode(String areacode) {
			this.areacode = areacode;
		}
		public int getOrdercode() {
			return ordercode;
		}
		public void setOrdercode(int ordercode) {
			this.ordercode = ordercode;
		}
		public String getParentid() {
			return parentid;
		}
		public void setParentid(String parentid) {
			this.parentid = parentid;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public int getCj() {
			return cj;
		}
		public void setCj(int cj) {
			this.cj = cj;
		}
		public String getAllpath() {
			return allpath;
		}
		public void setAllpath(String allpath) {
			this.allpath = allpath;
		}
		
	}
}