package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 部门信息
 *
 * @author Michael
 * @since 2021-01-18 10:52:41
 */
public class TDeptEntity extends BaseEntity<String> {

    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门描述
     */
    private String deptRemark;

    /**
     * 新增人或修改人
     */
    private String insertUser;

    /**
     * 新增或修改时间
     */
    private Date insertDate;

    /**
     * 部门经理
     */
    private String deptManager;

    public String getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptRemark() {
        return deptRemark;
    }

    public void setDeptRemark(String deptRemark) {
        this.deptRemark = deptRemark;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }


    public String getDeptManager() {
        return deptManager;
    }

    public void setDeptManager(String deptManager) {
        this.deptManager = deptManager;
    }

    @Override
    public String toString() {
        return "TDeptEntity{" +
                "deptName='" + deptName + '\'' +
                ", deptRemark='" + deptRemark + '\'' +
                ", insertUser='" + insertUser + '\'' +
                ", insertDate=" + insertDate +
                ", deptManager='" + deptManager + '\'' +
                ", id=" + id +
                '}';
    }
}
