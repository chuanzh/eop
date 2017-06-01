package com.github.chuanzh.eop.permission.db.maptable;

import com.github.chuanzh.orm.MapTable;
import com.github.chuanzh.orm.fieldannotation.FieldAuto;
import com.github.chuanzh.orm.fieldannotation.FieldDate;
import com.github.chuanzh.orm.fieldannotation.FieldKey;
import com.github.chuanzh.orm.fieldannotation.FieldNumber;
import com.github.chuanzh.orm.fieldannotation.FieldString;
 
public class INTERFACE_GROUP extends MapTable{
	public static final String TABLE_NAME = "interface_group"; 
	
	/**
	 * 主键ID
	 */
	@FieldKey
	@FieldAuto
	@FieldNumber
	public static final String f_id = "id";
	
	/**
	 * 组名
	 */
	@FieldString
	public static final String f_name = "name";
	
	/**
	 * 更新时间
	 */
	@FieldDate
	public static final String f_update_time = "update_time";
	
	/**
	 * 插入时间
	 */
	@FieldDate
	public static final String f_insert_time = "insert_time";
	
	/**
	 * 是否被删除（0-否，1-是）
	 */
	@FieldNumber
	public static final String f_is_del = "is_del";
	
	 
	private INTERFACE_GROUP(){}
	private static INTERFACE_GROUP instanceObj = null;
	public static INTERFACE_GROUP instance(){
		if(instanceObj == null){
			instanceObj = new INTERFACE_GROUP();
		}
		return instanceObj;
	}
}
 