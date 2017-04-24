package com.github.chuanzh.orm.dbadapter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.github.chuanzh.orm.DbConfBean;
import com.github.chuanzh.orm.DbConnectTool;

public abstract class SqliteDb implements DbConnectTool {
	
	private static Logger logger = Logger.getLogger(MysqlDb.class);
	protected BasicDataSource masterDataSource = null;
	private String getConnectUrl(DbConfBean confBean) {
		String cons = "jdbc:sqlite:"+confBean.getDbName();
		logger.info("db connect:" + cons);
		return cons;
	}
	protected abstract DbConfBean getMasterDb();
	protected abstract String getEncode();
	protected SqliteDb(){
		initDataSource();
	}
	protected void initDataSource() {
		try {
			masterDataSource = new BasicDataSource(); 
			masterDataSource.setDriverClassName("org.sqlite.JDBC");
			masterDataSource.setUrl(getConnectUrl(getMasterDb()));
			 
			HashMap<String, String> masterPoolConf = getMasterDb().getPoolConf();
			for(String key : masterPoolConf.keySet()){
				BeanUtils.setProperty(masterDataSource, key, masterPoolConf.get(key));
			}
			
		} catch (Exception e) {
			logger.error("数据库初始化错误",e);
		}
 	}
	@Override
	public Connection getConnection(boolean writeFlag) throws SQLException{
		return this.masterDataSource.getConnection();
 	}
	
	@Override
	public String formatPagerSql(String sql, int startIndex, int length) {
		return sql;
	}

	public List<String> allTablesName()  {
		List<String> list = new ArrayList<String>();
		return list;
	}
	
	/**
	 * 返回某个表的所有字段,字段类型，字段注释， 是否自增长,
	 * @param table 表名
	 * @return 表的所有字段
	 */
	public List<HashMap<String, String>> allFields(String table){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		return list;
	}
	 
	/**
	 * 多个主键用逗号隔开
	 * @param table 表名
	 * @return 表的所有字段
	 */
	public String getKeyFieldName(String table)
	{ 
		String key = new String();
		
		return key;
	}
  
	
	public BasicDataSource getMasterBasicDataSource(){
		return this.masterDataSource;
	}
	
	@Override
	public boolean printSql() {
		// TODO Auto-generated method stub
		return false;
	}

}
