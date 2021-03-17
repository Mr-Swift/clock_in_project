package com.njusc.npm.utils.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @author jinzf
 * @date Jul 1, 2014
 * @description 汉字转拼音
 * @version 1.0
 */
public final class PinYinUtils {

	public static final Pattern ZH_ALPHABET = Pattern
			.compile("^[\u4e00-\u9fa5]");
	public static final Pattern EN_ALPHABET = Pattern.compile("^[a-zA-Z]");

	public static boolean isFristHanZi(String s) {
		Matcher m = ZH_ALPHABET.matcher(s);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static boolean isFristAlphabet(String s) {
		Matcher m = EN_ALPHABET.matcher(s);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * @description 首个汉字的拼音，可能是多音字
	 * @param s
	 * @return
	 */
	public static String[] fristHanZi(final String s) {
		String[] sa = PinyinHelper.toMPS2PinyinStringArray(s.charAt(0));
		if (sa == null) {
			if (isFristAlphabet(s)) {
				sa = new String[] { Character.toString(s.charAt(0)) };
			} else {
				sa = new String[0];
			}
		}
		return sa;
	}

	/**
	 * @description 首个汉字的拼音的首字母转换成int a=97 b=98 ... A=65 B=66
	 * @param s
	 * @return
	 */
	public static Integer fristHanZiNumber(final String s) {
		if (s == null || "".equals(s.trim())) {
            return 0;
        }
		String[] sa = PinyinHelper.toMPS2PinyinStringArray(s.charAt(0));
		if (sa == null || sa.length == 0) {
			return isFristAlphabet(s) ? Character.toUpperCase(s.charAt(0)) - 64
					: 0;
		}
		return Character.toUpperCase(sa[0].charAt(0)) - 64;
	}
}
