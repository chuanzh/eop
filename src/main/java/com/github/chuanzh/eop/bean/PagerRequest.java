package com.github.chuanzh.eop.bean;

import com.github.chuanzh.eop.annotation.DescNote;

public class PagerRequest extends AbstractRequest{
	@DescNote("第几页")
	private Integer page = 1;
	@DescNote("每页大小")
	private Integer pageSize = 100;
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
	
	public Integer getStart() {
		return (page-1) * pageSize;
	}
	
	public Integer getLimit() {
		return pageSize;
	}
	
	public static void main(String[] args) throws Exception {
		PagerRequest pr = new PagerRequest();
		pr.setPageSize(10);
		System.out.println(pr.getPageSize());
		pr.setPageSize(140);
		System.out.println(pr.getPageSize()); 
		pr.setPageSize(null);
		System.out.println(pr.getPageSize()); 
	}
}
