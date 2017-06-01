package com.github.chuanzh.eop.permission.db.query;

import java.util.List;

import com.github.chuanzh.eop.permission.db.maptable.METHOD_CONFIG;
import com.github.chuanzh.eop.permission.db.rowdata.MethodConfigRowData;
import com.github.chuanzh.orm.BaseRowsSet;
import com.github.chuanzh.orm.ConditionOperator;
import com.github.chuanzh.orm.MapTable;
 
public class MethodConfigQuery extends BaseRowsSet{

	/**
	 * 主键ID
	 */
	public void ctnId(String value) {
		conditionTool.addCondition(METHOD_CONFIG.f_id,value,ConditionOperator.EQ);
	}
	/**
	 * 主键ID
	 */
	public void ctnId(String value,ConditionOperator operator) {
		conditionTool.addCondition(METHOD_CONFIG.f_id,value,operator);
	}
	/**
	 * 方法名称
	 */
	public void ctnName(String value) {
		conditionTool.addCondition(METHOD_CONFIG.f_name,value,ConditionOperator.EQ);
	}
	/**
	 * 方法名称
	 */
	public void ctnName(String value,ConditionOperator operator) {
		conditionTool.addCondition(METHOD_CONFIG.f_name,value,operator);
	}
	/**
	 * 别名
	 */
	public void ctnAliasName(String value) {
		conditionTool.addCondition(METHOD_CONFIG.f_alias_name,value,ConditionOperator.EQ);
	}
	/**
	 * 别名
	 */
	public void ctnAliasName(String value,ConditionOperator operator) {
		conditionTool.addCondition(METHOD_CONFIG.f_alias_name,value,operator);
	}
	/**
	 * 方法描述
	 */
	public void ctnDescription(String value) {
		conditionTool.addCondition(METHOD_CONFIG.f_description,value,ConditionOperator.EQ);
	}
	/**
	 * 方法描述
	 */
	public void ctnDescription(String value,ConditionOperator operator) {
		conditionTool.addCondition(METHOD_CONFIG.f_description,value,operator);
	}
	/**
	 * 方法所属组
	 */
	public void ctnGroupId(String value) {
		conditionTool.addCondition(METHOD_CONFIG.f_group_id,value,ConditionOperator.EQ);
	}
	/**
	 * 方法所属组
	 */
	public void ctnGroupId(String value,ConditionOperator operator) {
		conditionTool.addCondition(METHOD_CONFIG.f_group_id,value,operator);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value) {
		conditionTool.addCondition(METHOD_CONFIG.f_update_time,value,ConditionOperator.EQ);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(METHOD_CONFIG.f_update_time,value,operator);
	}
	/**
	 * 插入时间
	 */
	public void ctnInsertTime(String value) {
		conditionTool.addCondition(METHOD_CONFIG.f_insert_time,value,ConditionOperator.EQ);
	}
	/**
	 * 插入时间
	 */
	public void ctnInsertTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(METHOD_CONFIG.f_insert_time,value,operator);
	}
	/**
	 * 是否被删除（0-否，1-是）
	 */
	public void ctnIsDel(String value) {
		conditionTool.addCondition(METHOD_CONFIG.f_is_del,value,ConditionOperator.EQ);
	}
	/**
	 * 是否被删除（0-否，1-是）
	 */
	public void ctnIsDel(String value,ConditionOperator operator) {
		conditionTool.addCondition(METHOD_CONFIG.f_is_del,value,operator);
	}
	 
	public List<MethodConfigRowData> queryRows() throws Exception{
		return this.queryRows(MethodConfigRowData.class);
	} 
	
	@Override
	protected MapTable getMapTable() {
 		return METHOD_CONFIG.instance();
	}
	 
}
