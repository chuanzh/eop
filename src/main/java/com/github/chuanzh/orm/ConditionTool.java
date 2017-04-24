package com.github.chuanzh.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.chuanzh.util.FuncStatic;

public class ConditionTool {
	private static Logger logger = Logger.getLogger ( ConditionTool.class ) ;
	private int startIndex = 0;
	private int readLength = 0;
	private List<AndCondition> listCondition = null;
	private List<OrCondition> listOrCondition = null;
	private String orderStr = "";
	
	public String getOrderStr(){
		return this.orderStr;
	}
	public void addOrderDesc(String columnName){
		if(!FuncStatic.checkIsEmpty(this.orderStr) )
			this.orderStr += ",";
		this.orderStr += " " + columnName + " desc ";
	}
	public void addOrderAsc(String columnName){
		if(!FuncStatic.checkIsEmpty(this.orderStr) )
			this.orderStr += ",";
		this.orderStr += " " + columnName + " asc ";
	}
	public void clearOrder(){
		this.orderStr = "";
	}
	public List<AndCondition> getListCondition(){
		if(listCondition == null){
			this.listCondition = new ArrayList<AndCondition>();
		}
		return this.listCondition;
	}
	
	public List<OrCondition> getListOrCondition(){
		if(listOrCondition == null){
			this.listOrCondition = new ArrayList<OrCondition>();
		}
		return this.listOrCondition;
	}
	
	/**
	 * @param columnName 列名
	 * @param value 值
	 * @param conditionOperator 操作符
	 */
	public void addCondition(String columnName, Object value,
			ConditionOperator conditionOperator) {
		if(value == null){
			logger.error(columnName+" 数据库查询条件值为空。");
			return;
		}
		getListCondition().add(new AndCondition(columnName, conditionOperator,
				value.toString()));
	}
	
	public void addCondition(Map map) {
		for(Object columnName : map.keySet()){
			if(map.get(columnName) == null){
				logger.error(columnName+" 数据库查询条件值为空。");
			}else{
				getListCondition().add(new AndCondition(columnName.toString(),ConditionOperator.EQ,
						map.get(columnName).toString()));
			}
		}
	}
	/**
	 * @param columnName 列名
	 * @param value 值
	 */
	public void addCondition(String columnName, String value) {
		this.addCondition(columnName, value, ConditionOperator.EQ);
	}
	
	public void setCondition(String columnName, Object value,
			ConditionOperator conditionOperator){
		this.removeCondition(columnName) ;
		this.addCondition(columnName, value, conditionOperator);
	}
	public void setCondition(String columnName, String value) {
		this.setCondition(columnName, value, ConditionOperator.EQ);
	}
	public void setCondition(String columnName, Object value) {
		this.setCondition(columnName, value, ConditionOperator.EQ);
	}
	public void addOrCondition(OrCondition orCondition){
		getListOrCondition().add(orCondition);
	}
	public void clearCondition(){
		getListCondition().clear();
		getListOrCondition().clear();
		this.orderStr = null;
		this.queryLimit(0,0);
	}
	
	public void removeCondition(String columnName) {
		if (getListCondition() != null) {
			Iterator<AndCondition> it = getListCondition().iterator();
			while(it.hasNext()){
				AndCondition and = it.next();
				if(and.column.equals(columnName)){
					it.remove();
				}
			}
		}
		if (getListOrCondition() != null) {
			Iterator<OrCondition> it = getListOrCondition().iterator();
			while(it.hasNext()){
				OrCondition or = it.next();
				if(or.column.equals(columnName)){
					it.remove();
				}
			}
		}
	}
	
	public boolean hasCondition(){
		return this.listCondition != null || this.listOrCondition != null;
	}
	
	/**
	 * @param startIndex 起始位置
	 * @param length 长度
	 */
	public void queryLimit(int startIndex, int length) {
		this.startIndex = startIndex;
		this.readLength = length;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getReadLength() {
		return readLength;
	}
}
