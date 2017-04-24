package com.github.chuanzh.orm;

import java.util.HashMap;

import com.github.chuanzh.util.FuncStatic;

public class DbConfBean {
	private String ipAndPort;
	private String dbName;
	private String userName;
	private String password;
	private HashMap<String, String> poolConf = new HashMap<String, String>();
	
	 
	public String getIpAndPort() {
		return ipAndPort;
	}

	public void setIpAndPort(String ipAndPort) {
		this.ipAndPort = ipAndPort.trim();
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public HashMap<String, String> getPoolConf() {
		return poolConf;
	}
	
	public void setPoolConfByStr(String conf){
		if(!FuncStatic.checkIsEmpty(conf)){
			String[]  items = conf.trim().split(";");
			for(String item : items){
				if(item.indexOf('=') > -1){
					String[] keyValue = item.trim().split("=");
					poolConf.put(keyValue[0], keyValue[1]);
				}
			}
		}
	}
 
}
