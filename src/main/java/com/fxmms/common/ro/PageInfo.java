package com.fxmms.common.ro;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 分页信息Bean
 * 
 */
public class PageInfo {
	private static Log log = LogFactory.getLog(PageInfo.class);
    
	private int pageNo;
	private int pageSize;
	private long totalQuantity;
	private int firstResultNum;
	private long lastResultNum;
	private int totalPage;
	private boolean firstPage;
	private boolean lastPage;

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param totalQuantity
	 */
	private PageInfo(int pageNo, int pageSize, long totalQuantity) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalQuantity = totalQuantity;
		this.firstPage = false;
		this.lastPage = false;
	}

	public PageInfo() {

	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalQuantity
	 */
	public long getTotalQuantity() {
		return totalQuantity;
	}

	/**
	 * @param totalQuantity
	 *            the totalQuantity to set
	 */
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	/**
	 * @return the firstResultNum
	 */
	public int getFirstResultNum() {
		return firstResultNum;
	}

	/**
	 * @param firstResultNum
	 *            the firstResultNum to set
	 */
	public void setFirstResultNum(int firstResultNum) {
		this.firstResultNum = firstResultNum;
	}

	/**
	 * @return lastResultNum
	 */
	public long getLastResultNum() {
		return lastResultNum;
	}

	/**
	 * @param lastResultNum
	 *            the lastResultNum to set
	 */
	public void setLastResultNum(long lastResultNum) {
		this.lastResultNum = lastResultNum;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage
	 *            the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the isFirstPage
	 */
	public boolean isFirstPage() {
		return firstPage;
	}

	/**
	 * @param isFirstPage
	 *            the isFirstPage to set
	 */
	public void setFirstPage(boolean isFirstPage) {
		this.firstPage = isFirstPage;
	}

	/**
	 * @return the isLastPage
	 */
	public boolean isLastPage() {
		return lastPage;
	}

	/**
	 * @param isLastPage
	 *            the isLastPage to set
	 */
	public void setLastPage(boolean isLastPage) {
		this.lastPage = isLastPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PageInfo [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalQuantity=" + totalQuantity + ", firstResultNum="
				+ firstResultNum + ", lastResultNum=" + lastResultNum
				+ ", totalPage=" + totalPage + ", firstPage=" + firstPage
				+ ", lastPage=" + lastPage + "]";
	}

	public static PageInfo getInstance(int pageNo, int pageSize,
			long totalQuantity) {
		PageInfo page = new PageInfo(pageNo, pageSize, totalQuantity);

		if ((totalQuantity % pageSize) == 0) {
			page.setTotalPage((int) (totalQuantity / pageSize));
		} else {
			page.setTotalPage((int) (totalQuantity / pageSize) + 1);
		}

		if (pageNo > page.getTotalPage()) {
			page.setPageNo(page.getTotalPage());
		}

		if (pageNo <= 1) {
			page.setPageNo(1);
		}

		if (page.getPageNo() == 1) {
			page.setFirstPage(true);
		}
		if (page.getPageNo() == page.getTotalPage()) {
			page.setLastPage(true);
		}

		
		page.setFirstResultNum(pageSize * (page.getPageNo() - 1));

		if (page.isLastPage()) {
			page.setLastResultNum(page.getTotalQuantity());
		} else {
			page
					.setLastResultNum(page.getFirstResultNum()
							+ page.getPageSize());
		}

		if (log.isDebugEnabled())
			log.debug(page);

		return page;
	}
}
