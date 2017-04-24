package com.github.chuanzh.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigRead {
	private static Logger logger = Logger.getLogger(ConfigRead.class.getName());
	private Properties prop = new Properties();
	private HashMap<String, Object> catchMap = new HashMap<String, Object>();
	private static ConfigRead cc = null;
	public static String ROOT_PATH = null;
	private static ConfigRead init() {
		if (ConfigRead.cc == null)
			ConfigRead.cc = new ConfigRead();
		return ConfigRead.cc;
	}
	 
	public static String readValue(String var) {
		if(! ConfigRead.init().catchMap.containsKey(var)){
			ConfigRead.init().catchMap.put(var, ConfigRead.init().getValue(var));
		} 
		return (String)ConfigRead.init().catchMap.get(var);
		
	}
	public static void refresh() {
		ConfigRead.cc = null;
	}
	
	private ConfigRead() {
		InputStream is = null;
		try {
			is = ConfigRead.class.getResourceAsStream("/cfg.properties");
			prop.load(is);
		} catch (Exception ex) {
			logger.error("cfg.properties 获取失败！");
		} finally {
			try {
				if(is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String getValue(String var) {
		return prop.getProperty(var);
	}
	public static Integer readIntValue(String var) {
		try {
			if(! ConfigRead.init().catchMap.containsKey(var)){
				ConfigRead.init().catchMap.put(var, Integer.parseInt(ConfigRead.init().getValue(var)));
			} 
			return (Integer)ConfigRead.init().catchMap.get(var);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}
		return null;
	}
	public static Long readLongValue(String var) {
		try {
			if(! ConfigRead.init().catchMap.containsKey(var)){
				ConfigRead.init().catchMap.put(var, Long.parseLong(ConfigRead.init().getValue(var)));
			} 
			return (Long)ConfigRead.init().catchMap.get(var);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}
		return null;
	}
	public static boolean readBooleanValue(String var){
		
		if(! ConfigRead.init().catchMap.containsKey(var)){
			String val = ConfigRead.init().getValue(var);
			if(val==null){
				ConfigRead.init().catchMap.put(var, Boolean.FALSE);
			}
			if(val.toUpperCase().equals("TRUE")){
				ConfigRead.init().catchMap.put(var, Boolean.TRUE);
			}else{
				ConfigRead.init().catchMap.put(var, Boolean.FALSE);
			}
		} 
		return (Boolean)ConfigRead.init().catchMap.get(var);
	}
}
