package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 人员岗位信息
 *
 * @author Michael
 * @since 2021-01-18 10:52:41
 */
public class TUserPostEntity extends BaseEntity<String> {

    /**
     * 人员id
     */
    private String userId;

    /**
     * 人员姓名
     */
    private String userName;

    /**
     * 岗位id,对应字典表id
     */
    private String postManageId;

    /**
     * 岗位名称
     */
    private String postName;


    /**
     * 新增人员
     */
    private String insertUser;
    /**
     * 新增日期
     */
    private Date insertDate;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPostManageId(String postManageId) {
        this.postManageId = postManageId;
    }

    public String getPostManageId() {
        return postManageId;
    }

    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }

    public String getInsertUser() {
        return insertUser;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return "TUserPostEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", postManageId='" + postManageId + '\'' +
                ", postName='" + postName + '\'' +
                ", insertUser='" + insertUser + '\'' +
                ", insertDate=" + insertDate +
                ", id=" + id +
                '}';
    }
}
