package com.wyl.bus.util;

import java.io.UnsupportedEncodingException;

import android.util.Log;
/**
 * 字符串处理工具
 * @author wyl
 *
 */
public class StringUtil {
	/**
	 * 编码转换，ISO——>utf8
	 * @param str 目标字符串
	 * @return
	 */
	public static String iso2Utf8(String str){
		String resultStr = "";
		try {
			if(str != null && !"".equals(str)){
				resultStr = new String(str.getBytes("ISO-8859-1"),"UTF-8");
			}else {
				Log.w("StringUtil字符串转换错误", "目标字符串为空对象或空字符串");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return resultStr;
	}
}
