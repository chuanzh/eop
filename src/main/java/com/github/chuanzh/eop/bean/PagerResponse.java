package com.github.chuanzh.eop.bean;

import com.github.chuanzh.eop.annotation.DescNote;

public class PagerResponse extends AbstractResponse{
	@DescNote("第几页")
	private Integer page = null;
	@DescNote("每页大小")
	private Integer pageSize = 100;
	@DescNote("总记录数")
	private Long totalRecord = new Long(0);
	@DescNote("总页数")
	private Long totalPage = null;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Long getTotalPage() {
		if(totalPage == null){
			if(totalRecord%this.pageSize == 0){
				totalPage = totalRecord/this.pageSize ;
			}else{
				totalPage = totalRecord/this.pageSize + 1;
			}	
		}
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	
 
	
}
