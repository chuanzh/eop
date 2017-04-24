package com.github.chuanzh.util;

public class PagerTool {
	private int nowPage = 1;
	private int pageSize = 15;
	private int totalCount = 0;
	 
	
	/**
	 * 
	 * @param nowPage 从1开始 
	 * @param pageSize 页数
	 * @return pagerTool
	 */
	public static PagerTool init(int nowPage,int pageSize){
		return new PagerTool(nowPage,pageSize);
	}
	public static PagerTool init(int nowPage,int pageSize,int totalCount){
		return new PagerTool(nowPage,pageSize,totalCount);
	}
	/**
	 * 
	 * @param nowPage 从1开始 
	 * @return pagerTool
	 */
	public static PagerTool init(int nowPage){
		return new PagerTool(nowPage);
	}
	private PagerTool(){}
	private PagerTool (int nowPage,int pageSize){
		this.setNowPage(nowPage);
		this.pageSize = pageSize; 
	}
	private PagerTool (int nowPage,int pageSize,int totalCount){
		this.setNowPage(nowPage);
		this.pageSize = pageSize; 
		this.totalCount = totalCount;
	}
	private PagerTool (int nowPage){
		this.setNowPage(nowPage); 
	}
	public int getNowPage() {
		if(nowPage > getPagerCount() )
			return getPagerCount();
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		if(nowPage == 0)
			this.nowPage = 1;
		else
			this.nowPage = nowPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getStartIndex(){
		return (this.getNowPage() - 1) * this.getPageSize() ;
	}
	public int getPagerCount(){
		int i = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount
				/ pageSize + 1;
		return i == 0 ? 1 : i;
	}
 
}
