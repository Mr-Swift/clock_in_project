package com.njusc.npm.utils.util;

import java.util.Collections;
import java.util.List;

import com.njusc.npm.utils.annotation.Page;


/**
 * @author jinzf
 * @date 2013年9月23日
 * @description 分页对象
 * @version 1.0
 */
@Page(page = "pageIndex", rows = "pageSize", total = "total")
public class Pagination {
	// 返回的结果.
	private List rows = Collections.emptyList();

	// 返回的记录总条数.
	private long total = 0;

	// 返回的总页数.
	private int pageCount = 0;

	// 页面索引.
	private int pageIndex = 1;

	// 页面记录条数.
	private int pageSize = 10;

	/**
	 * @description 详细说明
	 */
	public Pagination() {
	}

	/**
	 * @description 详细说明
	 * @param pageIndex
	 * @param pageSize
	 * @param totel
	 */
	public Pagination(int pageIndex, int pageSize) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	/**
	 * @description 详细说明
	 * @param pageIndex
	 * @param pageSize
	 * @param totel
	 */
	public Pagination(int pageIndex, int pageSize, int totel) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		setTotal(totel);
	}

	/**
	 * @return the total
	 */
	public final long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;

		pageCount = (int) ((total % pageSize == 0) ? total / pageSize : total
				/ pageSize + 1);

		if (pageIndex < 1) {
            pageIndex = 1;
        }
		if (pageIndex > pageCount) {
            pageIndex = pageCount;
        }

	}

	public int getPageCount() {
		return pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the rows
	 */
	public final List getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public final void setRows(List rows) {
		this.rows = rows;
	}

	public static final Pagination factory(int limit, int offse) {
		final int idx = Math.max(0, limit / offse) + 1;
		return new Pagination(idx, offse);
	}
}