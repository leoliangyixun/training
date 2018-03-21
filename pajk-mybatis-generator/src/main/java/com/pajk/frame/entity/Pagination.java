package com.pajk.frame.entity;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private int page;
	private int pageSize = 10;
	private int total;
	private List<T> result;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}
	
	public List<T> getRows() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
	public int getPosition(){
		int position = this.page * this.pageSize;
		return position >= 0 ? position : 0;
	}

}
