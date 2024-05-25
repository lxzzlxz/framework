package com.liuzemin.server.framework.datasource.page;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 4121168324340189627L;

	private Integer curPage;

	private Integer pageSize;

	private Integer startIndex;

	private Long totalCount = 0L;

	private Integer totalPages;

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartIndex() {
		return (this.curPage - 1) * this.pageSize;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
