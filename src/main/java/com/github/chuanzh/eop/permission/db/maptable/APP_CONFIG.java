package com.github.chuanzh.eop.permission.db.maptable;

import com.github.chuanzh.orm.MapTable;
import com.github.chuanzh.orm.fieldannotation.FieldAuto;
import com.github.chuanzh.orm.fieldannotation.FieldDate;
import com.github.chuanzh.orm.fieldannotation.FieldKey;
import com.github.chuanzh.orm.fieldannotation.FieldNumber;
import com.github.chuanzh.orm.fieldannotation.FieldString;
 
public class APP_CONFIG extends MapTable{
	public static final String TABLE_NAME = "app_config"; 
	
	/**
	 * 
	 */
	@FieldKey
	@FieldAuto
	@FieldNumber
	public static final String f_id = "id";
	
	/**
	 * 应用名称
	 */
	@FieldString
	public static final String f_name = "name";
	
	/**
	 * appKey
	 */
	@FieldString
	public static final String f_app_key = "app_key";
	
	/**
	 * appSecret
	 */
	@FieldString
	public static final String f_app_secret = "app_secret";
	
	/**
	 * 绑定IP
	 */
	@FieldString
	public static final String f_bind_ip = "bind_ip";
	
	/**
	 * 应用限制方式（0-不受限制，1-对所有的接口，2-针对单个接口）
	 */
	@FieldNumber
	public static final String f_app_limit_type = "app_limit_type";
	
	/**
	 * 有效期（格式：2015-10-30）
	 */
	@FieldString
	public static final String f_valid_date = "valid_date";
	
	/**
	 * 是否被锁定
	 */
	@FieldNumber
	public static final String f_is_lock = "is_lock";
	
	/**
	 * 总共访问次数
	 */
	@FieldNumber
	public static final String f_total_visits_times = "total_visits_times";
	
	/**
	 * 总共限制次数
	 */
	@FieldNumber
	public static final String f_total_limit_times = "total_limit_times";
	
	/**
	 * 日访问总次数
	 */
	@FieldNumber
	public static final String f_date_visits_times = "date_visits_times";
	
	/**
	 * 日限制总次数
	 */
	@FieldNumber
	public static final String f_date_limit_times = "date_limit_times";
	
	/**
	 * 限制访问频率（单位/秒）
	 */
	@FieldNumber
	public static final String f_limit_rate = "limit_rate";
	
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
	
	 
	private APP_CONFIG(){}
	private static APP_CONFIG instanceObj = null;
	public static APP_CONFIG instance(){
		if(instanceObj == null){
			instanceObj = new APP_CONFIG();
		}
		return instanceObj;
	}
}
 