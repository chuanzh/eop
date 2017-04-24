package com.github.chuanzh.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.github.chuanzh.util.PagerTool;
 

public abstract class BaseRowsSet implements NeedDbBasicService{
	private static Logger logger = Logger.getLogger ( BaseRowsSet.class ) ;
	private DbBasicService dbService = null;
	protected ConditionTool conditionTool = new ConditionTool();
	
	protected DbBasicService getDbService(){
		this.dbService.setMapTable(getMapTable());
		return dbService;
	}
	private void freeDbService() throws Exception{
		
	}
	
	@Override
	public void initNeedDbBasicService(DbBasicService service){
		this.dbService = service;
	}
	
	public void addOrderAsc(String columnName){
		conditionTool.addOrderAsc(columnName);
	}
	public void addOrderDesc(String columnName){
		conditionTool.addOrderDesc(columnName);
	}
	
	public void clearCondition(){
		conditionTool.clearCondition();
	}
	
	public void removeCodition(String column){
		conditionTool.removeCondition(column);
	}
	
	public void setPagerDto(PagerTool var) {
		conditionTool.queryLimit(var.getStartIndex(), var.getPageSize());
 	}
	
	public void setConditionTool(ConditionTool tool){
		this.conditionTool = tool;
	}
	public long queryCount() throws Exception{
		try {
			DbBasicService dbservice = getDbService();
			return dbservice.queryExecCount(conditionTool);
		} finally{
			freeDbService();
		}
		
	}
	
	public List queryRows(Class<? extends OneRowDataInit> clazz) throws Exception{
		try {
			DbBasicService dbservice = getDbService();
			List<HashMap<String, Object>> list = dbservice.queryExec(conditionTool);
			List<OneRowDataInit> relist = new ArrayList<OneRowDataInit>();
			for(HashMap<String, Object> map : list){
				OneRowDataInit row = DbFactory.instance(dbservice, clazz);
				row.putData(map);
				relist.add(row);
			}
			return relist;
		} finally{
			freeDbService();
		}
	}
	
	public void updateExec(HashMap<String, Object> newData) throws Exception{
		try {
			DbBasicService dbservice = getDbService();
			dbservice.setNewData(newData);
			dbservice.updateExec(conditionTool); 
		} finally{
			freeDbService();
		}
	}
	
	public void deleteExec() throws Exception{
		try {
			DbBasicService dbservice = getDbService();
			dbservice.deleteExec(conditionTool);
		} finally{
			freeDbService();
		}
	}
	
	protected abstract MapTable getMapTable() ;
}
