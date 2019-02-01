package com.bbs.domain;

public class Criteria {
	private int page;
	static final private int perPageNum =10;
	
	public Criteria() {
		this.page = 1;
		
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		
	}
	
	public int getPage() {
		
		return page;
	}
	
	public int getPageStart() {
		return ((this.page - 1) * perPageNum)+1;
	}
	
	public int getPerPageNum() {
		return Criteria.perPageNum*page;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
	
}
