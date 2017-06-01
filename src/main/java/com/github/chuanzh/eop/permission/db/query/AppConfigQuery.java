package com.github.chuanzh.eop.permission.db.query;

import java.util.List;

import com.github.chuanzh.eop.permission.db.maptable.APP_CONFIG;
import com.github.chuanzh.eop.permission.db.rowdata.AppConfigRowData;
import com.github.chuanzh.orm.BaseRowsSet;
import com.github.chuanzh.orm.ConditionOperator;
import com.github.chuanzh.orm.MapTable;
 
public class AppConfigQuery extends BaseRowsSet{

	/**
	 * 
	 */
	public void ctnId(String value) {
		conditionTool.addCondition(APP_CONFIG.f_id,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnId(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_id,value,operator);
	}
	/**
	 * 应用名称
	 */
	public void ctnName(String value) {
		conditionTool.addCondition(APP_CONFIG.f_name,value,ConditionOperator.EQ);
	}
	/**
	 * 应用名称
	 */
	public void ctnName(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_name,value,operator);
	}
	/**
	 * appKey
	 */
	public void ctnAppKey(String value) {
		conditionTool.addCondition(APP_CONFIG.f_app_key,value,ConditionOperator.EQ);
	}
	/**
	 * appKey
	 */
	public void ctnAppKey(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_app_key,value,operator);
	}
	/**
	 * appSecret
	 */
	public void ctnAppSecret(String value) {
		conditionTool.addCondition(APP_CONFIG.f_app_secret,value,ConditionOperator.EQ);
	}
	/**
	 * appSecret
	 */
	public void ctnAppSecret(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_app_secret,value,operator);
	}
	/**
	 * 绑定IP
	 */
	public void ctnBindIp(String value) {
		conditionTool.addCondition(APP_CONFIG.f_bind_ip,value,ConditionOperator.EQ);
	}
	/**
	 * 绑定IP
	 */
	public void ctnBindIp(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_bind_ip,value,operator);
	}
	/**
	 * 应用限制方式（0-不受限制，1-对所有的接口，2-针对单个接口）
	 */
	public void ctnAppLimitType(String value) {
		conditionTool.addCondition(APP_CONFIG.f_app_limit_type,value,ConditionOperator.EQ);
	}
	/**
	 * 应用限制方式（0-不受限制，1-对所有的接口，2-针对单个接口）
	 */
	public void ctnAppLimitType(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_app_limit_type,value,operator);
	}
	/**
	 * 有效期（格式：2015-10-30）
	 */
	public void ctnValidDate(String value) {
		conditionTool.addCondition(APP_CONFIG.f_valid_date,value,ConditionOperator.EQ);
	}
	/**
	 * 有效期（格式：2015-10-30）
	 */
	public void ctnValidDate(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_valid_date,value,operator);
	}
	/**
	 * 是否被锁定
	 */
	public void ctnIsLock(String value) {
		conditionTool.addCondition(APP_CONFIG.f_is_lock,value,ConditionOperator.EQ);
	}
	/**
	 * 是否被锁定
	 */
	public void ctnIsLock(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_is_lock,value,operator);
	}
	/**
	 * 总共访问次数
	 */
	public void ctnTotalVisitsTimes(String value) {
		conditionTool.addCondition(APP_CONFIG.f_total_visits_times,value,ConditionOperator.EQ);
	}
	/**
	 * 总共访问次数
	 */
	public void ctnTotalVisitsTimes(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_total_visits_times,value,operator);
	}
	/**
	 * 总共限制次数
	 */
	public void ctnTotalLimitTimes(String value) {
		conditionTool.addCondition(APP_CONFIG.f_total_limit_times,value,ConditionOperator.EQ);
	}
	/**
	 * 总共限制次数
	 */
	public void ctnTotalLimitTimes(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_total_limit_times,value,operator);
	}
	/**
	 * 日访问总次数
	 */
	public void ctnDateVisitsTimes(String value) {
		conditionTool.addCondition(APP_CONFIG.f_date_visits_times,value,ConditionOperator.EQ);
	}
	/**
	 * 日访问总次数
	 */
	public void ctnDateVisitsTimes(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_date_visits_times,value,operator);
	}
	/**
	 * 日限制总次数
	 */
	public void ctnDateLimitTimes(String value) {
		conditionTool.addCondition(APP_CONFIG.f_date_limit_times,value,ConditionOperator.EQ);
	}
	/**
	 * 日限制总次数
	 */
	public void ctnDateLimitTimes(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_date_limit_times,value,operator);
	}
	/**
	 * 限制访问频率（单位/秒）
	 */
	public void ctnLimitRate(String value) {
		conditionTool.addCondition(APP_CONFIG.f_limit_rate,value,ConditionOperator.EQ);
	}
	/**
	 * 限制访问频率（单位/秒）
	 */
	public void ctnLimitRate(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_limit_rate,value,operator);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value) {
		conditionTool.addCondition(APP_CONFIG.f_update_time,value,ConditionOperator.EQ);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_update_time,value,operator);
	}
	/**
	 * 插入时间
	 */
	public void ctnInsertTime(String value) {
		conditionTool.addCondition(APP_CONFIG.f_insert_time,value,ConditionOperator.EQ);
	}
	/**
	 * 插入时间
	 */
	public void ctnInsertTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(APP_CONFIG.f_insert_time,value,operator);
	}
	 
	public List<AppConfigRowData> queryRows() throws Exception{
		return this.queryRows(AppConfigRowData.class);
	} 
	
	@Override
	protected MapTable getMapTable() {
 		return APP_CONFIG.instance();
	}
	 
}
