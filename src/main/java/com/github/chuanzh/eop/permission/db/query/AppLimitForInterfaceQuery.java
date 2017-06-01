package com.github.chuanzh.eop.permission.db.query;

import java.util.List;

import com.github.chuanzh.eop.permission.db.maptable.APP_LIMIT_FOR_INTERFACE;
import com.github.chuanzh.eop.permission.db.rowdata.AppLimitForInterfaceRowData;
import com.github.chuanzh.orm.BaseRowsSet;
import com.github.chuanzh.orm.ConditionOperator;
import com.github.chuanzh.orm.MapTable;
 
public class AppLimitForInterfaceQuery extends BaseRowsSet{

	/**
	 * 主键ID
	 */
	public void ctnId(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_id,value,ConditionOperator.EQ);
	}
	/**
	 * 主键ID
	 */
	public void ctnId(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_id,value,operator);
	}
	/**
	 * 应用ID
	 */
	public void ctnAppId(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_app_id,value,ConditionOperator.EQ);
	}
	/**
	 * 应用ID
	 */
	public void ctnAppId(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_app_id,value,operator);
	}
	/**
	 * 接口方法ID
	 */
	public void ctnMethodId(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_method_id,value,ConditionOperator.EQ);
	}
	/**
	 * 接口方法ID
	 */
	public void ctnMethodId(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_method_id,value,operator);
	}
	/**
	 * 总共访问次数
	 */
	public void ctnTotalVisitsTimes(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_total_visits_times,value,ConditionOperator.EQ);
	}
	/**
	 * 总共访问次数
	 */
	public void ctnTotalVisitsTimes(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_total_visits_times,value,operator);
	}
	/**
	 * 总共限制次数
	 */
	public void ctnTotalLimitTimes(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_total_limit_times,value,ConditionOperator.EQ);
	}
	/**
	 * 总共限制次数
	 */
	public void ctnTotalLimitTimes(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_total_limit_times,value,operator);
	}
	/**
	 * 日访问总次数
	 */
	public void ctnDateVisitsTimes(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_date_visits_times,value,ConditionOperator.EQ);
	}
	/**
	 * 日访问总次数
	 */
	public void ctnDateVisitsTimes(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_date_visits_times,value,operator);
	}
	/**
	 * 日限制总次数
	 */
	public void ctnDateLimitTimes(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_date_limit_times,value,ConditionOperator.EQ);
	}
	/**
	 * 日限制总次数
	 */
	public void ctnDateLimitTimes(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_date_limit_times,value,operator);
	}
	/**
	 * 频率限制（单位/秒）
	 */
	public void ctnLimitRate(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_limit_rate,value,ConditionOperator.EQ);
	}
	/**
	 * 频率限制（单位/秒）
	 */
	public void ctnLimitRate(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_limit_rate,value,operator);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_update_time,value,ConditionOperator.EQ);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_update_time,value,operator);
	}
	/**
	 * 插入时间
	 */
	public void ctnInsertTime(String value) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_insert_time,value,ConditionOperator.EQ);
	}
	/**
	 * 插入时间
	 */
	public void ctnInsertTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_LIMIT_FOR_INTERFACE.f_insert_time,value,operator);
	}
	 
	public List<AppLimitForInterfaceRowData> queryRows() throws Exception{
		return this.queryRows(AppLimitForInterfaceRowData.class);
	} 
	
	@Override
	protected MapTable getMapTable() {
 		return APP_LIMIT_FOR_INTERFACE.instance();
	}
	 
}
