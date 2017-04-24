package com.github.chuanzh.orm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
 
 
public abstract class BaseOneRow implements OneRowDataInit{
	private static Logger logger = Logger.getLogger ( BaseOneRow.class ) ;
	protected CatchOneRowData rowData = new CatchOneRowData();
	private DbBasicService dbService = null;
 	 
	protected abstract MapTable getMapTable() ;
	
	@Override
	public void putData(HashMap<String, Object> map){
		rowData.putData(map);
	}
	
	public String getKey(){
		String keyStr = "";
		String[] keys = this.getMapTable().getKeyColumns().split(",");
		if(keys.length == 1){
			keyStr = this.rowData.getValueString(keys[0]);
		}
		return keyStr;
	}
	
	protected DbBasicService getDbService(){
		this.dbService.setMapTable(getMapTable());
		return dbService;
	}
	private void freeDbService() throws Exception{
		 
	}
	 
	@Override
	public void initByKey(Object key) throws Exception {
		try {
			ConditionTool tool = new ConditionTool();
			tool.setCondition(getMapTable().getKeyColumns(), key.toString());
			
			DbBasicService dbservice = this.getDbService();
			List<HashMap<String, Object>> result = dbservice.queryExec(tool);
			if(result.size() > 0){
				rowData.putData(result.get(0));
			}
		}
		finally{
			freeDbService();
		}
	}
	@Override
	public void initNeedDbBasicService(DbBasicService service){
		this.dbService = service;
	}
	 
	public void insert() throws Exception {
		try {
			DbBasicService dbservice = this.getDbService();
			dbservice.setNewData(rowData.getNewData());
			if(getMapTable().getAutoColumn() == null){
				dbservice.insertExec();
			}else{
				String autoValue = dbservice.insertExecReauto();
				rowData.setValue(getMapTable().getAutoColumn(), autoValue);
			}
		} finally{
			freeDbService();
		}
	}
	
	public void update() throws Exception {
		try {
			ConditionTool tool = new ConditionTool();
			tool.setCondition(getMapTable().getKeyColumns(), rowData.getValueString(getMapTable().getKeyColumns()));
			
			DbBasicService dbservice = this.getDbService();
			dbservice.setNewData(rowData.getNewData());
 			dbservice.updateExec(tool);
		} finally{
			freeDbService();
		}
	}
	
	public void delete() throws Exception {
		try {
			ConditionTool tool = new ConditionTool();
			tool.setCondition(getMapTable().getKeyColumns(), rowData.getValueString(getMapTable().getKeyColumns()));
			
			DbBasicService dbservice = this.getDbService();
			dbservice.deleteExec(tool);
		}
		finally{
			freeDbService();
		}
	}
	public void printData(){
		rowData.printData();
	}
}
