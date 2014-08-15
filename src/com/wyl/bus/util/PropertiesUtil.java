package com.wyl.bus.util;

import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
/**
 * 读取本地的配置文件
 * @author wyl
 *
 */
public class PropertiesUtil {
	
	private static Properties urlProps;
	/**
	 * 
	 * @param content getApplicationContext()
	 * @param filePath ‘/assets/config.properties’为config.properties
	 * @return
	 */
	public static Properties getProperties(Context content, String filePath){
		Properties props = new Properties();
		try {
			
			//方法一：通过activity中的context攻取setting.properties的FileInputStream
			InputStream in = content.getAssets().open(filePath);
			//方法二：通过class获取setting.properties的FileInputStream
//			InputStream in = PropertiesUtil.class.getResourceAsStream("/assets/config.properties "); 
			props.load(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		urlProps = props;
		return urlProps;
	}
}