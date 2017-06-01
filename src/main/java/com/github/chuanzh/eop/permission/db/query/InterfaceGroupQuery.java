package com.github.chuanzh.eop.permission.db.query;

import java.util.List;

import com.github.chuanzh.eop.permission.db.maptable.INTERFACE_GROUP;
import com.github.chuanzh.eop.permission.db.rowdata.InterfaceGroupRowData;
import com.github.chuanzh.orm.BaseRowsSet;
import com.github.chuanzh.orm.ConditionOperator;
import com.github.chuanzh.orm.MapTable;
 
public class InterfaceGroupQuery extends BaseRowsSet{

	/**
	 * 主键ID
	 */
	public void ctnId(String value) {
		conditionTool.addCondition(INTERFACE_GROUP.f_id,value,ConditionOperator.EQ);
	}
	/**
	 * 主键ID
	 */
	public void ctnId(String value,ConditionOperator operator) {
		conditionTool.addCondition(INTERFACE_GROUP.f_id,value,operator);
	}
	/**
	 * 组名
	 */
	public void ctnName(String value) {
		conditionTool.addCondition(INTERFACE_GROUP.f_name,value,ConditionOperator.EQ);
	}
	/**
	 * 组名
	 */
	public void ctnName(String value,ConditionOperator operator) {
		conditionTool.addCondition(INTERFACE_GROUP.f_name,value,operator);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value) {
		conditionTool.addCondition(INTERFACE_GROUP.f_update_time,value,ConditionOperator.EQ);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(INTERFACE_GROUP.f_update_time,value,operator);
	}
	/**
	 * 插入时间
	 */
	public void ctnInsertTime(String value) {
		conditionTool.addCondition(INTERFACE_GROUP.f_insert_time,value,ConditionOperator.EQ);
	}
	/**
	 * 插入时间
	 */
	public void ctnInsertTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(INTERFACE_GROUP.f_insert_time,value,operator);
	}
	/**
	 * 是否被删除（0-否，1-是）
	 */
	public void ctnIsDel(String value) {
		conditionTool.addCondition(INTERFACE_GROUP.f_is_del,value,ConditionOperator.EQ);
	}
	/**
	 * 是否被删除（0-否，1-是）
	 */
	public void ctnIsDel(String value,ConditionOperator operator) {
		conditionTool.addCondition(INTERFACE_GROUP.f_is_del,value,operator);
	}
	 
	public List<InterfaceGroupRowData> queryRows() throws Exception{
		return this.queryRows(InterfaceGroupRowData.class);
	} 
	
	@Override
	protected MapTable getMapTable() {
 		return INTERFACE_GROUP.instance();
	}
	 
}
