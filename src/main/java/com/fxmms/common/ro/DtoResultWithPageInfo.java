package com.fxmms.common.ro;

import java.util.Collection;



public class DtoResultWithPageInfo<T extends Dto> implements ResultObject<T> {
	private Collection<T> results;
	private PageInfo pageInfo;
	public DtoResultWithPageInfo() {
		super();
	}
	public DtoResultWithPageInfo(Collection<T> results, PageInfo pageInfo) {
		super();
		this.results = results;
		this.pageInfo = pageInfo;
	}
	/**
	 * @return pageInfo
	 */
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	/**
	 * @param pageInfo
	 *            the pageInfo to set
	 */
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	
	@Override
	public Collection<T> getResults() {
		return results;
	}

	
	@Override
	public void setResults(Collection<T> results) {
		this.results = results;
	}

	
	@Override
	public boolean isEmptyResult() {
		return results.isEmpty();
	}
}
