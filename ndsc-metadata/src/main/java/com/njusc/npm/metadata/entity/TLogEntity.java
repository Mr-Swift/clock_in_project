package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 日志表
 *
 * @author Michael
 * @since 2021-01-18 10:52:40
 */
public class TLogEntity extends BaseEntity<String> {

    /**
     * 操作描述
     */
    private String operationRemark;
    /**
     * 新增或者修改时间
     */
    private Date insertDate;
    /**
     * 新增或者修改人
     */
    private String insertUser;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;


    public void setOperationRemark(String operationRemark) {
        this.operationRemark = operationRemark;
    }

    public String getOperationRemark() {
        return operationRemark;
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


    @Override
    public String toString() {
        return "TLogEntity{" +
                "operationRemark='" + operationRemark + '\'' +
                ", insertDate=" + insertDate +
                ", insertUser='" + insertUser + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
