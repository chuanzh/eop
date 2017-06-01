package com.github.chuanzh.eop.permission.db.maptable;

import com.github.chuanzh.orm.MapTable;
import com.github.chuanzh.orm.fieldannotation.FieldAuto;
import com.github.chuanzh.orm.fieldannotation.FieldDate;
import com.github.chuanzh.orm.fieldannotation.FieldKey;
import com.github.chuanzh.orm.fieldannotation.FieldNumber;
 
public class APP_LIMIT_FOR_INTERFACE extends MapTable{
	public static final String TABLE_NAME = "app_limit_for_interface"; 
	
	/**
	 * 主键ID
	 */
	@FieldKey
	@FieldAuto
	@FieldNumber
	public static final String f_id = "id";
	
	/**
	 * 应用ID
	 */
	@FieldNumber
	public static final String f_app_id = "app_id";
	
	/**
	 * 接口方法ID
	 */
	@FieldNumber
	public static final String f_method_id = "method_id";
	
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
	 * 频率限制（单位/秒）
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
	
	 
	private APP_LIMIT_FOR_INTERFACE(){}
	private static APP_LIMIT_FOR_INTERFACE instanceObj = null;
	public static APP_LIMIT_FOR_INTERFACE instance(){
		if(instanceObj == null){
			instanceObj = new APP_LIMIT_FOR_INTERFACE();
		}
		return instanceObj;
	}
}
 