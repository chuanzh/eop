package com.github.chuanzh.eop.permission.db.rowdata;

import java.util.Date;

import org.apache.log4j.Logger;

import com.github.chuanzh.eop.permission.db.maptable.APP_CONFIG;
import com.github.chuanzh.orm.BaseOneRow;
import com.github.chuanzh.orm.MapTable;
import com.github.chuanzh.util.FuncStatic;

public class AppConfigRowData extends BaseOneRow{
	private static Logger logger = Logger.getLogger ( AppConfigRowData.class ) ;
	
	/**
	 * 
	 */
	public Integer getId() {
		try {
			return rowData.getValueInt(APP_CONFIG.f_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 
	 */
	public void setId(Integer value) {
		rowData.setValue(APP_CONFIG.f_id, value );
	}
	
	/**
	 * 应用名称
	 */
	public String getName() {
		return rowData.getValueString(APP_CONFIG.f_name);
	}
	/**
	 * 应用名称
	 */
	public void setName(String value) {
		rowData.setValue(APP_CONFIG.f_name, value );
	}
	
	/**
	 * appKey
	 */
	public String getAppKey() {
		return rowData.getValueString(APP_CONFIG.f_app_key);
	}
	/**
	 * appKey
	 */
	public void setAppKey(String value) {
		rowData.setValue(APP_CONFIG.f_app_key, value );
	}
	
	/**
	 * appSecret
	 */
	public String getAppSecret() {
		return rowData.getValueString(APP_CONFIG.f_app_secret);
	}
	/**
	 * appSecret
	 */
	public void setAppSecret(String value) {
		rowData.setValue(APP_CONFIG.f_app_secret, value );
	}
	
	/**
	 * 绑定IP
	 */
	public String getBindIp() {
		return rowData.getValueString(APP_CONFIG.f_bind_ip);
	}
	/**
	 * 绑定IP
	 */
	public void setBindIp(String value) {
		rowData.setValue(APP_CONFIG.f_bind_ip, value );
	}
	
	/**
	 * 应用限制方式（0-不受限制，1-对所有的接口，2-针对单个接口）
	 */
	public Integer getAppLimitType() {
		try {
			return rowData.getValueInt(APP_CONFIG.f_app_limit_type);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 应用限制方式（0-不受限制，1-对所有的接口，2-针对单个接口）
	 */
	public void setAppLimitType(Integer value) {
		rowData.setValue(APP_CONFIG.f_app_limit_type, value );
	}
	
	/**
	 * 有效期（格式：2015-10-30）
	 */
	public String getValidDate() {
		return rowData.getValueString(APP_CONFIG.f_valid_date);
	}
	/**
	 * 有效期（格式：2015-10-30）
	 */
	public void setValidDate(String value) {
		rowData.setValue(APP_CONFIG.f_valid_date, value );
	}
	
	/**
	 * 是否被锁定
	 */
	public Integer getIsLock() {
		try {
			return rowData.getValueInt(APP_CONFIG.f_is_lock);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 是否被锁定
	 */
	public void setIsLock(Integer value) {
		rowData.setValue(APP_CONFIG.f_is_lock, value );
	}
	
	/**
	 * 总共访问次数
	 */
	public Integer getTotalVisitsTimes() {
		try {
			return rowData.getValueInt(APP_CONFIG.f_total_visits_times);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 总共访问次数
	 */
	public void setTotalVisitsTimes(Integer value) {
		rowData.setValue(APP_CONFIG.f_total_visits_times, value );
	}
	
	/**
	 * 总共限制次数
	 */
	public Integer getTotalLimitTimes() {
		try {
			return rowData.getValueInt(APP_CONFIG.f_total_limit_times);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 总共限制次数
	 */
	public void setTotalLimitTimes(Integer value) {
		rowData.setValue(APP_CONFIG.f_total_limit_times, value );
	}
	
	/**
	 * 日访问总次数
	 */
	public Integer getDateVisitsTimes() {
		try {
			return rowData.getValueInt(APP_CONFIG.f_date_visits_times);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 日访问总次数
	 */
	public void setDateVisitsTimes(Integer value) {
		rowData.setValue(APP_CONFIG.f_date_visits_times, value );
	}
	
	/**
	 * 日限制总次数
	 */
	public Integer getDateLimitTimes() {
		try {
			return rowData.getValueInt(APP_CONFIG.f_date_limit_times);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 日限制总次数
	 */
	public void setDateLimitTimes(Integer value) {
		rowData.setValue(APP_CONFIG.f_date_limit_times, value );
	}
	
	/**
	 * 限制访问频率（单位/秒）
	 */
	public Integer getLimitRate() {
		try {
			return rowData.getValueInt(APP_CONFIG.f_limit_rate);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 限制访问频率（单位/秒）
	 */
	public void setLimitRate(Integer value) {
		rowData.setValue(APP_CONFIG.f_limit_rate, value );
	}
	
	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return rowData.getValueDate(APP_CONFIG.f_update_time);
	}
	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date value) {
		rowData.setValue(APP_CONFIG.f_update_time, value );
	}
	
	/**
	 * 插入时间
	 */
	public Date getInsertTime() {
		return rowData.getValueDate(APP_CONFIG.f_insert_time);
	}
	/**
	 * 插入时间
	 */
	public void setInsertTime(Date value) {
		rowData.setValue(APP_CONFIG.f_insert_time, value );
	}
	
  
	@Override
	protected MapTable getMapTable() {
 		return APP_CONFIG.instance();
	}
 }
