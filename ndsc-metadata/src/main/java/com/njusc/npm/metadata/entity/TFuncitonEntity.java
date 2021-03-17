package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 菜单信息
 *
 * @author Michael
 * @since 2021-01-18 10:52:41
 */
public class TFuncitonEntity extends BaseEntity<String> {

    /**
     * 模块地址
     */
    private String funUrl;
    /**
     * 模块名称
     */
    private String funName;
    /**
     * 描述信息
     */
    private String remark;
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 排序字段
     */
    private Integer orderNumber;
    /**
     * 新增或者修改时间
     */
    private Date insertDate;
    /**
     * 新增或者修改人
     */
    private String insertUser;
    /**
     * 非数据库字段，存放功能模块子级
     */
    private List<TFuncitonEntity> childs;

    public void setFunUrl(String funUrl) {
        this.funUrl = funUrl;
    }

    public String getFunUrl() {
        return funUrl;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getFunName() {
        return funName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }


    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }

    public String getInsertUser() {
        return insertUser;
    }

    public List<TFuncitonEntity> getChilds() {
        return childs;
    }

    public void setChilds(List<TFuncitonEntity> childs) {
        this.childs = childs;
    }

    public TFuncitonEntity() {
    }

    public TFuncitonEntity(String funUrl, String funName, Integer orderNumber) {
        this.funUrl = funUrl;
        this.funName = funName;
        this.orderNumber = orderNumber;
    }

    public TFuncitonEntity(String funUrl, String funName, String parentId, Integer orderNumber) {
        this.funUrl = funUrl;
        this.funName = funName;
        this.parentId = parentId;
        this.orderNumber = orderNumber;
    }

    public TFuncitonEntity(String funUrl, String funName, String remark, String parentId, Integer orderNumber, Date insertDate, String insertUser) {
        this.funUrl = funUrl;
        this.funName = funName;
        this.remark = remark;
        this.parentId = parentId;
        this.orderNumber = orderNumber;
        this.insertDate = insertDate;
        this.insertUser = insertUser;
    }
}
