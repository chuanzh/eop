package com.github.chuanzh.orm;

/**
 * 实现NeedConnect的对象全部通过DbFactory获取实例
 *
 */
public interface NeedConnect {
	void initConnect(DbConnectTool connect);
}
