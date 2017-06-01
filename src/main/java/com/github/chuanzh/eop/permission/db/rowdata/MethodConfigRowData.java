package com.github.chuanzh.eop.permission.db.rowdata;

import java.util.Date;

import org.apache.log4j.Logger;

import com.github.chuanzh.eop.permission.db.maptable.METHOD_CONFIG;
import com.github.chuanzh.orm.BaseOneRow;
import com.github.chuanzh.orm.MapTable;
import com.github.chuanzh.util.FuncStatic;

public class MethodConfigRowData extends BaseOneRow{
	private static Logger logger = Logger.getLogger ( MethodConfigRowData.class ) ;
	
	/**
	 * 主键ID
	 */
	public Integer getId() {
		try {
			return rowData.getValueInt(METHOD_CONFIG.f_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 主键ID
	 */
	public void setId(Integer value) {
		rowData.setValue(METHOD_CONFIG.f_id, value );
	}
	
	/**
	 * 方法名称
	 */
	public String getName() {
		return rowData.getValueString(METHOD_CONFIG.f_name);
	}
	/**
	 * 方法名称
	 */
	public void setName(String value) {
		rowData.setValue(METHOD_CONFIG.f_name, value );
	}
	
	/**
	 * 别名
	 */
	public String getAliasName() {
		return rowData.getValueString(METHOD_CONFIG.f_alias_name);
	}
	/**
	 * 别名
	 */
	public void setAliasName(String value) {
		rowData.setValue(METHOD_CONFIG.f_alias_name, value );
	}
	
	/**
	 * 方法描述
	 */
	public String getDescription() {
		return rowData.getValueString(METHOD_CONFIG.f_description);
	}
	/**
	 * 方法描述
	 */
	public void setDescription(String value) {
		rowData.setValue(METHOD_CONFIG.f_description, value );
	}
	
	/**
	 * 方法所属组
	 */
	public Integer getGroupId() {
		try {
			return rowData.getValueInt(METHOD_CONFIG.f_group_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 方法所属组
	 */
	public void setGroupId(Integer value) {
		rowData.setValue(METHOD_CONFIG.f_group_id, value );
	}
	
	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return rowData.getValueDate(METHOD_CONFIG.f_update_time);
	}
	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date value) {
		rowData.setValue(METHOD_CONFIG.f_update_time, value );
	}
	
	/**
	 * 插入时间
	 */
	public Date getInsertTime() {
		return rowData.getValueDate(METHOD_CONFIG.f_insert_time);
	}
	/**
	 * 插入时间
	 */
	public void setInsertTime(Date value) {
		rowData.setValue(METHOD_CONFIG.f_insert_time, value );
	}
	
	/**
	 * 是否被删除（0-否，1-是）
	 */
	public Integer getIsDel() {
		try {
			return rowData.getValueInt(METHOD_CONFIG.f_is_del);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 是否被删除（0-否，1-是）
	 */
	public void setIsDel(Integer value) {
		rowData.setValue(METHOD_CONFIG.f_is_del, value );
	}
	
  
	@Override
	protected MapTable getMapTable() {
 		return METHOD_CONFIG.instance();
	}
 }
