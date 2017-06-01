package com.github.chuanzh.eop.permission.db.maptable;

import com.github.chuanzh.orm.MapTable;
import com.github.chuanzh.orm.fieldannotation.FieldAuto;
import com.github.chuanzh.orm.fieldannotation.FieldDate;
import com.github.chuanzh.orm.fieldannotation.FieldKey;
import com.github.chuanzh.orm.fieldannotation.FieldNumber;
import com.github.chuanzh.orm.fieldannotation.FieldString;
 
public class METHOD_CONFIG extends MapTable{
	public static final String TABLE_NAME = "method_config"; 
	
	/**
	 * 主键ID
	 */
	@FieldKey
	@FieldAuto
	@FieldNumber
	public static final String f_id = "id";
	
	/**
	 * 方法名称
	 */
	@FieldString
	public static final String f_name = "name";
	
	/**
	 * 别名
	 */
	@FieldString
	public static final String f_alias_name = "alias_name";
	
	/**
	 * 方法描述
	 */
	@FieldString
	public static final String f_description = "description";
	
	/**
	 * 方法所属组
	 */
	@FieldNumber
	public static final String f_group_id = "group_id";
	
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
	
	 
	private METHOD_CONFIG(){}
	private static METHOD_CONFIG instanceObj = null;
	public static METHOD_CONFIG instance(){
		if(instanceObj == null){
			instanceObj = new METHOD_CONFIG();
		}
		return instanceObj;
	}
}
 