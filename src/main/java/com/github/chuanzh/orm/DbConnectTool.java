package com.github.chuanzh.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface DbConnectTool {
	
	public Connection getConnection(boolean writeFlag) throws SQLException;
	
	/**
	 * 是否将执行的SQL打印到日志
	 * @return 是否打印
	 */
	public boolean printSql();
	
	public String formatPagerSql(String sql, int startIndex, int length);
	 
	public List<String> allTablesName()  ;
	
	/**
	 * 返回某个表的所有字段及字段类型，字段与字段类型之间用逗号分隔
	 * @param table 字段类型
	 * @return Map集合
	 */
	public List<HashMap<String, String>> allFields(String table);
	
}
