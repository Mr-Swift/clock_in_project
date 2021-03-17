package com.njusc.npm.utils.util;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	/**
	 * 根据银行卡获取银行卡前6位数字
	 * */
	public static String getRegBankCardFrontSix(String bankcard){
		if(bankcard!=null&&!"".equals(bankcard.trim())){
			bankcard = bankcard.trim().replaceAll(" ", "").replaceAll("	", "");
			if(bankcard.length()>10){
				return bankcard.substring(0, 6);
			}
		}
		return null;
	}
	/**
	 * 根据银行卡获取银行卡后四位，其他*标识
	 * */
	public static String getRegBankCard(String bankcard){
		if(bankcard!=null&&!"".equals(bankcard.trim())){
			bankcard = bankcard.trim().replaceAll(" ", "").replaceAll("	", "");
			if(bankcard.length()>10){
				return "**** **** **** "+getBankCardLastFour(bankcard);
			}
		}
		return null;
	}
	/**
	 * 根据银行卡获取银行卡后四位
	 * */
	public static String getBankCardLastFour(String bankcard){
		if(bankcard!=null&&!"".equals(bankcard.trim())){
			bankcard = bankcard.trim().replaceAll(" ", "").replaceAll("	", "");
			if(bankcard.length()>10){
				return bankcard.substring(bankcard.length()-4, bankcard.length());
			}
		}
		return null;
	}
	public static String getRegCard(String card){
	if(card!=null&&!"".equals(card)){
		if(card.length()==15){
			return card.substring(0, 5)+"********"+card.substring(13);
		}
		if(card.length()==18){
			return card.substring(0, 5)+"***********"+card.substring(16);
		}
	}
	return null;
	}
	public static String getRegPhone(String phone){
		if(phone!=null&&!"".equals(phone)){
			if(phone.length()==11){
				return phone.substring(0,3)+"*****"+phone.substring(8);
			}
		}
		return null;	
		}
	public static String getRegMail(String email){
		if(email!=null&&!"".equals(email)){
		String first=email.split("@")[0];
		String last=email.split("@")[1];
		if(first!=null&&!"".equals(first)){
			if(first.length()<=3){
				return first.substring(0,1)+"***@"+last;
			}
			    return first.substring(0,3)+"***@"+last;
		}
		}
		return null;	
		}

	public static String concatStringtoUpperCase(String left, String right) {
		String newstr = left + right;

		return newstr.toUpperCase();
	}

	public static String completionStringtoUpperCase(String oldstr, int length) {
		String newstr = oldstr;
		String completion = "";
		if (newstr != null && !"".equals(newstr)) {
			for (int i = 0; i < length - newstr.length(); i++) {
				completion ="0"+ completion ;
			}
		}
		return completion+(StringUtils.isBlank(newstr)?"":newstr.toUpperCase()) ;
	}
	public static String sNull0(Object str) {
		if (str == null) {
			return "0";
		} else {
			return str.toString().trim();
		}
	}
	public static String sNull(Object str) {
		if (str == null) {
			return "";
		} else {
			return str.toString().trim();
		}
	}
	public static String isNull(Object str) {
		if (str == null) {
			return "";
		} else {
			return str.toString().replaceAll(" ", "");
		}
	}
//	public static String objectToJson(Object obj)
//	{
//		XStream xs=new XStream(new JsonHierarchicalStreamDriver());
//		
//		return xs.toXML(obj);
//	}
	public static boolean isNullOrEmpty(Object obj)
	{
		if (obj instanceof String) {
			if(obj!=null&&!"".equals(((String) obj).trim()))
			{
				return true;
			}
		}else if(obj!=null){
			return true;
		}
		return false;
	}
//	public static String buildRandomNumberID() {
//		Random rdm = new Random();
//		long ct = Math.abs(rdm.nextLong());
//		String nb = String.valueOf(ct);
//		String nm = (nb.substring(nb.length() - 4, nb.length()));
//		String id=(DateUtil.getDateTime("yyMMddkk")
//				+ nm);
//		return id;
//
//	}
	public static boolean compareIDS(String ids,String delim,String id)
	{
		StringTokenizer st=new StringTokenizer(ids,delim);
		while(st.hasMoreElements()){
			if(id.equals(st.nextElement())){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("AlibabaAvoidPatternCompileInMethod")
	public    static String replaceBlank(String str) {
	      String dest = "";
	      if (str!=null) {
	          Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	          Matcher m = p.matcher(str);
	          dest = m.replaceAll("");
	      }
	      return dest;
	  }
	
	/**
	 * 加密显示，如手机号显示前三位和后四位
	 * */
	public static String jmStr(int start,int end,String str){
		String rtnStr = null;
		if(str!=null&&(start+end)<=str.length()){
			String jm = "";
			for(int i=0;i<str.length()-start-end;i++){
				jm = jm + "*";
			}
			rtnStr = str.substring(0, start) + jm + str.substring(str.length()-end);
		}
		return rtnStr;
	}
	/**
	 * 获取uuid
	 * */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	/**
	 * 自动去掉double小数点后面多余的0,如1.20显示1.2
	 * */
	public static String formatDouble(Double num){
		String rtn = null;
		if(num==null){
			return null;
		}
		DecimalFormat df = new DecimalFormat("#.##");   
		rtn = df.format(num);
		return rtn;
	}
	/**
	 * 根据身份证号码获取年龄
	 * @param cardid
	 * @return
	 * @throws ParseException
	 */
	public static int getAge(String cardid) throws ParseException {
		int age = 0;
		if(StringUtils.isNotBlank(cardid)){
			if(cardid.length()==18){//18位身份证
				String birthday=cardid.substring(6,14);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
				Date birthdate = sdf.parse(birthday);
				GregorianCalendar currentDay = new GregorianCalendar();    
                currentDay.setTime(birthdate);
				int year = currentDay.get(Calendar.YEAR); 
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");  
                String nowyear=simpleDateFormat.format(new Date());  
                age=Integer.parseInt(nowyear)-year;  
			}else if(cardid.length()==15){
				String birthday = cardid.substring(6, 12);
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd"); 
				Date birthdate = sdf.parse(birthday);
				GregorianCalendar currentDay = new GregorianCalendar();    
                currentDay.setTime(birthdate);
				int year = currentDay.get(Calendar.YEAR); 
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");  
                String nowyear=simpleDateFormat.format(new Date());  
                age=Integer.parseInt(nowyear)-year;  
			}
			
		}
		return age;
	}
	
	/**
	 * 根据身份证号码，判断年龄是否在22-59之间，在22-59之间返回true，不在则返回false
	 * @throws ParseException 
	 */
	public static boolean getAgeIsValiate(String cardid) throws ParseException {
		int age = getAge(cardid);
		if(age>=22 && age<=59){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws ParseException {
//		System.out.println(getRegPhone("13913961774"));
//		System.out.println(getAge("110105710923582"));
	}
	/**
	 * 将字符串转成日期
	 * @param date 时间
	 * @return 日期
	 */
	public static Date stringToDate(String date)
	{
		try {
			return (new SimpleDateFormat("yyyy-MM-dd").parse(date));
		} catch (ParseException e) {
			try {
				return (new SimpleDateFormat("yyyy/MM/dd").parse(date));


			} catch (ParseException e1) {
				try {
					return(new SimpleDateFormat("yyyyMMdd").parse(date));

				} catch (ParseException e2) {
					try {
						return(new SimpleDateFormat("yyyy.MM.dd").parse(date));

					} catch (ParseException e3) {
						try {
							return(new SimpleDateFormat("yyyy.MM").parse(date));
						} catch (ParseException e4) {
							try {
								return(new SimpleDateFormat("yyyy/MM").parse(date));
							} catch (ParseException e5) {
								try {
									return(new SimpleDateFormat("yyyy").parse(date.replace(".0",".1")));
								} catch (ParseException e6) {
									return null;
								}

							}
						}

					}
				}
			}
		}
	}


}
