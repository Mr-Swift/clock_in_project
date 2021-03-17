package com.njusc.npm.utils.util;

public class CodeUtil {
	/**
	 * @description:生成6为验证码
	 * */
	public final static String getRandomCode(){
		// 字符集合(除去易混淆的数字0、数字1、字母l、字母o、字母O)
	    /*char[] CHAR_CODE = { '1', '2', '3', '4', '5', '6',
	            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
	            'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
	            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
	            'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };*/
		char[] CHAR_CODE = { '0','1', '2', '3', '4', '5', '6','7', '8', '9'};
	    String code="";
	    for (int i = 0; i < 6; i++) {
            // 索引 0 and n-1
            int r = (int) (Math.random() * 10);
            // 将result中的第i个元素设置为codes[r]存放的数值
            code += CHAR_CODE[r];
        }
	        return code;
	}
	/**
	 * 获取三位随机数
	 * @return
	 */
	public final static String getRandomCodeForThree(){
	    String code="";
	    for (int i = 0; i < 3; i++) {
            int r = (int) (Math.random() * 10);
            code += r;
        }
	        return code;
	}
}
