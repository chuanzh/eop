package com.github.chuanzh.eop.permission.db.rowdata;

import java.util.Date;

import org.apache.log4j.Logger;

import com.github.chuanzh.eop.permission.db.maptable.APP_LIMIT_FOR_INTERFACE;
import com.github.chuanzh.orm.BaseOneRow;
import com.github.chuanzh.orm.MapTable;
import com.github.chuanzh.util.FuncStatic;

public class AppLimitForInterfaceRowData extends BaseOneRow{
	private static Logger logger = Logger.getLogger ( AppLimitForInterfaceRowData.class ) ;
	
	/**
	 * 主键ID
	 */
	public Integer getId() {
		try {
			return rowData.getValueInt(APP_LIMIT_FOR_INTERFACE.f_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 主键ID
	 */
	public void setId(Integer value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_id, value );
	}
	
	/**
	 * 应用ID
	 */
	public Integer getAppId() {
		try {
			return rowData.getValueInt(APP_LIMIT_FOR_INTERFACE.f_app_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 应用ID
	 */
	public void setAppId(Integer value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_app_id, value );
	}
	
	/**
	 * 接口方法ID
	 */
	public Integer getMethodId() {
		try {
			return rowData.getValueInt(APP_LIMIT_FOR_INTERFACE.f_method_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 接口方法ID
	 */
	public void setMethodId(Integer value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_method_id, value );
	}
	
	/**
	 * 总共访问次数
	 */
	public Integer getTotalVisitsTimes() {
		try {
			return rowData.getValueInt(APP_LIMIT_FOR_INTERFACE.f_total_visits_times);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 总共访问次数
	 */
	public void setTotalVisitsTimes(Integer value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_total_visits_times, value );
	}
	
	/**
	 * 总共限制次数
	 */
	public Integer getTotalLimitTimes() {
		try {
			return rowData.getValueInt(APP_LIMIT_FOR_INTERFACE.f_total_limit_times);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 总共限制次数
	 */
	public void setTotalLimitTimes(Integer value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_total_limit_times, value );
	}
	
	/**
	 * 日访问总次数
	 */
	public Integer getDateVisitsTimes() {
		try {
			return rowData.getValueInt(APP_LIMIT_FOR_INTERFACE.f_date_visits_times);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 日访问总次数
	 */
	public void setDateVisitsTimes(Integer value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_date_visits_times, value );
	}
	
	/**
	 * 日限制总次数
	 */
	public Integer getDateLimitTimes() {
		try {
			return rowData.getValueInt(APP_LIMIT_FOR_INTERFACE.f_date_limit_times);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 日限制总次数
	 */
	public void setDateLimitTimes(Integer value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_date_limit_times, value );
	}
	
	/**
	 * 频率限制（单位/秒）
	 */
	public Integer getLimitRate() {
		try {
			return rowData.getValueInt(APP_LIMIT_FOR_INTERFACE.f_limit_rate);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 频率限制（单位/秒）
	 */
	public void setLimitRate(Integer value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_limit_rate, value );
	}
	
	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return rowData.getValueDate(APP_LIMIT_FOR_INTERFACE.f_update_time);
	}
	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_update_time, value );
	}
	
	/**
	 * 插入时间
	 */
	public Date getInsertTime() {
		return rowData.getValueDate(APP_LIMIT_FOR_INTERFACE.f_insert_time);
	}
	/**
	 * 插入时间
	 */
	public void setInsertTime(Date value) {
		rowData.setValue(APP_LIMIT_FOR_INTERFACE.f_insert_time, value );
	}
	
  
	@Override
	protected MapTable getMapTable() {
 		return APP_LIMIT_FOR_INTERFACE.instance();
	}
 }
