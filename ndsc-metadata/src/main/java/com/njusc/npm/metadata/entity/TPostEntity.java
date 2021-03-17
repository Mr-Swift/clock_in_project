package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 岗位信息
 *
 * @author Michael
 * @since 2021-01-18 10:52:41
 */
public class TPostEntity extends BaseEntity<String> {

    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 岗位名称
     */
    private String postName;
    /**
     * 新增或者修改时间
     */
    private Date insertDate;
    /**
     * 新增或者修改人
     */
    private String insertUser;
    /**
     * 岗位类型 对应字典表
     */
    private String postTypeId;

    public String getDeptName() {
        return deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }

    public String getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(String postTypeId) {
        this.postTypeId = postTypeId;
    }

    @Override
    public String toString() {
        return "TPostEntity{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", postName='" + postName + '\'' +
                ", insertDate=" + insertDate +
                ", insertUser='" + insertUser + '\'' +
                ", postTypeId='" + postTypeId + '\'' +
                ", id=" + id +
                '}';
    }
}
