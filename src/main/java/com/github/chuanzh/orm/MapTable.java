package com.github.chuanzh.orm;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.github.chuanzh.orm.fieldannotation.FieldAuto;
import com.github.chuanzh.orm.fieldannotation.FieldClob;
import com.github.chuanzh.orm.fieldannotation.FieldDate;
import com.github.chuanzh.orm.fieldannotation.FieldKey;

public abstract class MapTable {
	  
	private String selectColumns = null;
	private String tableName = null;
	private String keyColumns = null;
	private HashMap<String,Boolean> dataColumns = new HashMap<String,Boolean>(); 
	private String autoColumn = null;
	
	public MapTable(){
		Field[] fs = this.getClass().getFields();
		for(Field f : fs){ 
			if(f.isAnnotationPresent(FieldDate.class)){
				dataColumns.put(columnName(f), true);
			}else{
				dataColumns.put(columnName(f), false);
			}
			if(f.isAnnotationPresent(FieldAuto.class)){
				this.autoColumn = columnName(f);
			}
			
		}
	}
	private String columnName(Field f){
		try {
			String  columnName = f.get(this).toString();
			return columnName;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "";
	}
	public String getSelectColumns() {
		if(selectColumns == null){
			selectColumns = "";
			Field[] fs = this.getClass().getFields();
			for(Field f : fs){
				if(f.getName().equals("TABLE_NAME")){
					continue;
				}
				if(f.isAnnotationPresent(FieldClob.class)){
					continue;
				}
				selectColumns += columnName(f)+",";
				
			}
			if(selectColumns.length() > 0){
				selectColumns = selectColumns.substring(0,selectColumns.length()-1);
			}
		}
		return selectColumns;
	}

	/**
	 * 返回该表表名
	 * @return tableName
	 */
	public String getTableName() {
		if(tableName == null){
			Field[] fs = this.getClass().getFields();
			for(Field f : fs){
				if(f.getName().equals("TABLE_NAME")){
					try {
						tableName = f.get(this).toString();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		return tableName;
	}

	/**
	 * 返回该表主键列，多个主键用,号隔开
	 * @return keyColumns
	 */
	public String getKeyColumns() {
		if(keyColumns == null){
			keyColumns = "";
			Field[] fs = this.getClass().getFields();
			for(Field f : fs){ 
				if(f.isAnnotationPresent(FieldKey.class)){
					keyColumns += columnName(f)+",";
				}
			}
			if(keyColumns.length() > 0){
				keyColumns = keyColumns.substring(0,keyColumns.length()-1);
			}
		}
		return keyColumns;
	}

	public boolean isDateColumn(String columnName){
		if(dataColumns.containsKey(columnName))
			return dataColumns.get(columnName);
		
		return false;
	}
	
	public String getAutoColumn(){
		return this.autoColumn;
	}
}