package com.fxmms.common.ro;

import org.hibernate.Criteria;

public abstract class PageQo {
	
	private int page;
	private int rows;
	
	public int getPage() {
		if (page==0) {
			return 1;
		}
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		if (rows==0) {
			return 10;
		}
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public abstract void add(Criteria criteria);

}
