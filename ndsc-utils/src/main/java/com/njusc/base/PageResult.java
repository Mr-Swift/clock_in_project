package com.njusc.base;

import com.njusc.npm.utils.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PageResult<T> {
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

//    private
    private Integer totalpage;// 总页数
    private  Integer code= 0;
    private  Object data;
    private  String msg;
    private Integer count=DEFAULT_TOTALSIZE;//总条数
    /**
     * 每页的记录数，默认10
     */
    private Integer limit = DEFAULT_SIZE;
    /**
     * 当前页，默认第一页
     */
    private Integer page = DEFAULT_PAGE;

    /**
     * 请求参数
     */
    private Map<String, Object> params = new HashMap<>();
    public PageResult() {
    }

    public PageResult(List<T> rows, Map<String, Object> params) {
        this.data = rows;
        this.params = params;

        setLimit(Util.n(params.get("limit")) ? DEFAULT_SIZE : Integer.parseInt(params.get("limit").toString()));
        setPage(Util.n(params.get("page")) ? DEFAULT_PAGE : Integer.parseInt(params.get("page").toString()));

        setCount(Util.n(params.get("totalSize")) ? DEFAULT_TOTALSIZE : Integer.parseInt(params.get("totalSize").toString()));
        setTotalpage((getCount()-1)/DEFAULT_SIZE+1);
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
