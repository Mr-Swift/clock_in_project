package com.njusc.npm.utils.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 金额转换工具类
 * @author  jzf
 *
 */
public class MoneyFormatUtils {
	public static final DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
	/***
	 * 将金额转换成 逗号分隔,并保留两位小数  1,111.00,如果金额为空，则返回空
	 * @param money
	 * @return
	 */
	public static String parseMoney(BigDecimal money){
		if(money==null){
			return null;
		}
		return decimalFormat.format(money);
	}
	
	/***
	 * 将金额转换成 逗号分隔,并保留两位小数 例如  1,111.00,
	 * @param money
	 * @return
	 */
	public static String parseMoney(double money){
		BigDecimal bigDecimal = new BigDecimal(money);
		return parseMoney(bigDecimal);
	}
}
