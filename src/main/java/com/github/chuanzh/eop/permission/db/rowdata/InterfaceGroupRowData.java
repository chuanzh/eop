package com.github.chuanzh.eop.permission.db.rowdata;

import java.util.Date;

import org.apache.log4j.Logger;

import com.github.chuanzh.eop.permission.db.maptable.INTERFACE_GROUP;
import com.github.chuanzh.orm.BaseOneRow;
import com.github.chuanzh.orm.MapTable;
import com.github.chuanzh.util.FuncStatic;

public class InterfaceGroupRowData extends BaseOneRow{
	private static Logger logger = Logger.getLogger ( InterfaceGroupRowData.class ) ;
	
	/**
	 * 主键ID
	 */
	public Integer getId() {
		try {
			return rowData.getValueInt(INTERFACE_GROUP.f_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 主键ID
	 */
	public void setId(Integer value) {
		rowData.setValue(INTERFACE_GROUP.f_id, value );
	}
	
	/**
	 * 组名
	 */
	public String getName() {
		return rowData.getValueString(INTERFACE_GROUP.f_name);
	}
	/**
	 * 组名
	 */
	public void setName(String value) {
		rowData.setValue(INTERFACE_GROUP.f_name, value );
	}
	
	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return rowData.getValueDate(INTERFACE_GROUP.f_update_time);
	}
	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date value) {
		rowData.setValue(INTERFACE_GROUP.f_update_time, value );
	}
	
	/**
	 * 插入时间
	 */
	public Date getInsertTime() {
		return rowData.getValueDate(INTERFACE_GROUP.f_insert_time);
	}
	/**
	 * 插入时间
	 */
	public void setInsertTime(Date value) {
		rowData.setValue(INTERFACE_GROUP.f_insert_time, value );
	}
	
	/**
	 * 是否被删除（0-否，1-是）
	 */
	public Integer getIsDel() {
		try {
			return rowData.getValueInt(INTERFACE_GROUP.f_is_del);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 是否被删除（0-否，1-是）
	 */
	public void setIsDel(Integer value) {
		rowData.setValue(INTERFACE_GROUP.f_is_del, value );
	}
	
  
	@Override
	protected MapTable getMapTable() {
 		return INTERFACE_GROUP.instance();
	}
 }
