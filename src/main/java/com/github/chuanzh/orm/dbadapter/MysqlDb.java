package com.github.chuanzh.orm.dbadapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.github.chuanzh.orm.DbConfBean;
import com.github.chuanzh.orm.DbConnectTool;
import com.github.chuanzh.util.FuncStatic;
 
public abstract class MysqlDb implements DbConnectTool {
	private static Logger logger = Logger.getLogger(MysqlDb.class);
	protected BasicDataSource masterDataSource = null;
	protected BasicDataSource[] slaveDataSourceArray = null;
	private String getConnectUrl(DbConfBean confBean) {
		String cons = "jdbc:mysql://"
				+ confBean.getIpAndPort()
				+ "/"
				+ confBean.getDbName()
				+ "?useOldAliasMetadataBehavior=true&autoReconnect=true&failOverReadOnly=false&characterEncoding="+getEncode();
		logger.info("db connect:" + cons);
		return cons;
	}
	private int slaveIndex = 0;
	private int slaveDataSourceLength = 0;
	protected abstract DbConfBean getMasterDb();
	protected abstract DbConfBean[] getSlaveDbArray();
	protected abstract String getEncode();
	protected MysqlDb(){
		initDataSource();
	}
	protected void initDataSource() {
		try {
			masterDataSource = new BasicDataSource(); 
			masterDataSource.setValidationQuery("select 1");
			masterDataSource.setDriverClassName("com.mysql.jdbc.Driver");
			masterDataSource.setUrl(getConnectUrl(getMasterDb()));
			masterDataSource.setUsername(getMasterDb().getUserName());
			masterDataSource.setPassword(getMasterDb().getPassword());
			 
			HashMap<String, String> masterPoolConf = getMasterDb().getPoolConf();
			for(String key : masterPoolConf.keySet()){
				BeanUtils.setProperty(masterDataSource, key, masterPoolConf.get(key));
			}
			
			if(getSlaveDbArray() != null){
				DbConfBean[] slaveConf = getSlaveDbArray();
				slaveDataSourceLength = slaveConf.length;
				slaveDataSourceArray = new BasicDataSource[slaveDataSourceLength];
				for(int i=0; i<slaveConf.length ; i++){
					BasicDataSource slaveDataSource = new BasicDataSource();
					slaveDataSource.setValidationQuery("select 1");
					slaveDataSource.setDriverClassName("com.mysql.jdbc.Driver");
					slaveDataSource.setUrl(getConnectUrl(slaveConf[i]));
					slaveDataSource.setUsername(slaveConf[i].getUserName());
					slaveDataSource.setPassword(slaveConf[i].getPassword());
					HashMap<String, String> slavePoolConf = slaveConf[i].getPoolConf();
					for(String key : slavePoolConf.keySet()){
						BeanUtils.setProperty(slaveDataSource, key, slavePoolConf.get(key));
					}
					slaveDataSourceArray[i] = slaveDataSource;
				}
			}
			
		} catch (Exception e) {
			logger.error("数据库初始化错误",e);
		}
 	}
	@Override
	public Connection getConnection(boolean writeFlag) throws SQLException{
		if(this.slaveDataSourceArray == null){
			return this.masterDataSource.getConnection();
		}
		if(writeFlag){
			return this.masterDataSource.getConnection();
		}else{
			this.slaveIndex ++;
			if(this.slaveIndex >= slaveDataSourceLength){
				this.slaveIndex = 0;
			}
			return this.slaveDataSourceArray[this.slaveIndex].getConnection();
		}
 	}
  
	@Override
	public String formatPagerSql(String sql, int startIndex, int length) {
		return sql + " limit " + startIndex + ","+length;
	}

	public List<String> allTablesName()  {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			conn = this.getConnection(true);
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("show tables");
			while (resultSet.next()){
				list.add(resultSet.getString(1));
			}
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}finally{
			try {
				if(resultSet != null)
					resultSet.close();
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
			try {
				if(stmt != null)
					stmt.close();
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
		}
		return list;
	}
	
	/**
	 * 返回某个表的所有字段,字段类型，字段注释， 是否自增长,
	 * @param table 表名
	 * @return 表的所有字段
	 */
	public List<HashMap<String, String>> allFields(String table){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			conn = this.getConnection(true);
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SHOW FULL COLUMNS FROM "+getMasterDb().getDbName()+"."+table);
			while (resultSet.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("fieldName", resultSet.getString(1));
				map.put("fieldType", resultSet.getString(2));
				map.put("fieldComment", resultSet.getString(9));
				if(!FuncStatic.checkIsEmpty(resultSet.getString("Extra"))){
					if(resultSet.getString("Extra").contains("auto_increment")){
						map.put("fieldAutoIncreace", "true");
					}
				}
				list.add(map);
			}
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}finally{
			try {
				if(resultSet != null)
					resultSet.close();
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
			try {
				if(stmt != null)
					stmt.close();
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
		}
		return list;
	}
	 
	/**
	 * 多个主键用逗号隔开
	 * @param table 表名
	 * @return 表的主键
	 */
	public String getKeyFieldName(String table)
	{ 
		String key = new String();
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			conn = this.getConnection(true);
			stmt = conn.createStatement();
			String sql = "SELECT t.TABLE_NAME, c.COLUMN_NAME "+
		"FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS AS t, INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS c "+
		"WHERE t.TABLE_NAME = c.TABLE_NAME "+
		"AND t.TABLE_NAME =  '{0}' "+
		"AND c.table_schema='{1}' "+
		"AND c.CONSTRAINT_NAME = 'PRIMARY' " +
		"AND t.CONSTRAINT_TYPE =  'PRIMARY KEY' group by c.COLUMN_NAME";
			resultSet = stmt.executeQuery(FuncStatic.format(sql, table,getMasterDb().getDbName()));
			while(resultSet.next()){
				key += resultSet.getString(2) + ",";
			}
			if(!FuncStatic.checkIsEmpty(key)){
				key = key.substring(0,key.length()-1);
			}
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}finally{
			try {
				if(resultSet != null)
					resultSet.close();
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
			try {
				if(stmt != null)
					stmt.close();
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
		}
		return key;
	}
	public BasicDataSource getMasterBasicDataSource(){
		return this.masterDataSource;
	}
	
	public BasicDataSource[] getSlaveBasicDataSource(){
		return this.slaveDataSourceArray;
	}
}
