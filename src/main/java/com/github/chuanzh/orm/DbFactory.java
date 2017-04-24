package com.github.chuanzh.orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
 
public class DbFactory {
	private static Logger logger = Logger.getLogger ( DbFactory.class) ;
	/**
	 * 注意调用freeResource释放资源
	 * @param connect 数据库连接
	 * @return DbBasicService
	 */
	public static DbBasicService instanceService (DbConnectTool connect ){
		DbBasicService dbBasicService = new DbBasicService();
		dbBasicService.initConnect(connect);
		return dbBasicService;
	}
	
	/**
	 * 注意调用freeResource释放资源
	 * @param connect 数据库连接
	 * @param threadId 线程ID
	 * @return DbBasicService
	 */
	public static DbBasicService instanceService (DbConnectTool connect ,String threadId){
		DbBasicService dbBasicService = new DbBasicService();
		dbBasicService.setThreadId(threadId);
		dbBasicService.initConnect(connect);
		return dbBasicService;
	}
	
	public static <T> T  instance(DbBasicService dbService ,Class<? extends NeedDbBasicService> clazz) throws Exception {
		NeedDbBasicService needConnect = clazz.newInstance();
		needConnect.initNeedDbBasicService(dbService);
		return (T)needConnect;
	}

	public static <T> T find(DbBasicService dbService , Class<? extends OneRowDataInit> clazz, String key) throws Exception{
		return findById(dbService,clazz,key);
	}
	
	public static <T> T find(DbBasicService dbService , Class<? extends OneRowDataInit> clazz, Long key) throws Exception{
		return findById(dbService,clazz,key);
	}
	
	public static <T> T find(DbBasicService dbService , Class<? extends OneRowDataInit> clazz, Integer key) throws Exception{
		return findById(dbService,clazz,key);
	}
	
	public static <T> T find(DbBasicService dbService , Class<? extends OneRowDataInit> clazz, Map map) throws Exception {
		OneRowDataInit rowdata = clazz.newInstance();
		rowdata.initNeedDbBasicService(dbService);
		
		ConditionTool tool = new ConditionTool();
		tool.addCondition(map);
		List<HashMap<String, Object>> list = dbService.queryExec( tool);
		if(list.size() > 0){
			rowdata.putData(list.get(0));
			return (T)rowdata;
		}
		return null;
	}
	
	private static <T> T findById(DbBasicService dbService , Class<? extends OneRowDataInit> clazz, Object key) throws Exception{
		OneRowDataInit findByKey = clazz.newInstance();
		findByKey.initNeedDbBasicService(dbService);
		findByKey.initByKey(key);
		return (T)findByKey;
	}

	 
}