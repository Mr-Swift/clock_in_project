package com.njusc.npm.metadata.interceptor;

import com.njusc.npm.utils.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页对象
 *
 * @param <T> 泛型对象
 *
 * @author michael
 */
public class PageObject<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 默认每页容量
     */
    public static Integer DEFAULT_SIZE = 15;
    /**
     * 默认页码
     */
    public static Integer DEFAULT_PAGE = 1;
    /**
     * 默认总页数
     */
    public static Integer DEFAULT_TOTALPAGE = 1;
    /**
     * 默认总记录数
     */
    public static Integer DEFAULT_TOTALSIZE = 0;

    /**
     * 每页的记录数，默认10
     */
    private Integer limit = DEFAULT_SIZE;
    /**
     * 当前页，默认第一页
     */
    private Integer page = DEFAULT_PAGE;
    /**
     * 总记录数
     */
    private Integer totalSize = DEFAULT_TOTALSIZE;
    /**
     * 总页数
     */
    private Integer totalPage = DEFAULT_TOTALPAGE;
    /**
     * 下一页
     */
    private Boolean next = false;
    /**
     * 上一页
     */
    private Boolean previous = false;
    /**
     * 内容
     */
    private List<T> rows = new ArrayList<>();
    /**
     * 请求参数
     */
    private Map<String, Object> params = new HashMap<>();

    public PageObject() {
    }

    public PageObject(List<T> rows, Map<String, Object> params) {
        this.rows = rows;
        this.params = params;

        setLimit(Util.n(params.get("limit")) ? DEFAULT_SIZE : Integer.parseInt(params.get("limit").toString()));
        setPage(Util.n(params.get("page")) ? DEFAULT_PAGE : Integer.parseInt(params.get("page").toString()));

        setTotalSize(Util.n(params.get("totalSize")) ? DEFAULT_TOTALSIZE : Integer.parseInt(params.get("totalSize").toString()));
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;

        this.totalPage = 0 == totalSize ? DEFAULT_TOTALPAGE : (totalSize % limit == 0 ? totalSize / limit : totalSize / limit + 1);
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Boolean getNext() {
        next = page >= totalPage ? false : true;
        return next;
    }

    public void setNext(Boolean next) {
        this.next = next;
    }

    public Boolean getPrevious() {
        previous = page <= DEFAULT_PAGE ? false : true;
        return previous;
    }

    public void setPrevious(Boolean previous) {
        this.previous = previous;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
