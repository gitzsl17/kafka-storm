/**
 * Project Name:stream-analyse
 * File Name:StormProUtils.java
 * Package Name:com.surfilter.stream.analyse.utils
 * Date:2016年3月8日下午5:26:32
 *
*/

package com.act.kafka.storm.util;

import java.util.Properties;
import java.util.Set;

public class PropertiesUtils {

	//文件名称
	private static final String CONFIG_FILE = "config.properties";
	
	private static PropertiesUtils instance = null;
	
	private static Properties pro;
	
	private PropertiesUtils(){};
	
	/**
	 * newInstance:构建默认对象. <br/>
	 */
	public static PropertiesUtils newInstance(){
		try {
			if( instance == null){
				pro = new Properties();
				
				pro.load(ClassLoader.getSystemResourceAsStream(CONFIG_FILE));
				
				instance = new PropertiesUtils();
				
				System.out.println("解析总配置信息如下：");
				Set<Object> keys = pro.keySet();
				for(Object oKey: keys){
					System.out.println(oKey + "=" + pro.get(oKey));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * 
	 * getParam:获取配置文件中的参数. <br/>
	 *
	 * @author quanli
	 * @param key			主键
	 * @param defaultVal	默认值
	 * @return
	 * @since JDK 1.6
	 */
	public String getParam(String key, String defaultVal){
		String param = pro.getProperty(key);
		if( param == null || "".equals(param) ){
			return defaultVal;
		}else{
			return param;
		}
	}
}

