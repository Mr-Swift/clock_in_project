package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

public class TAttendanceEntity extends BaseEntity<String> {

    /**
     * 打卡人ID
     */
    private String userId;

    /**
     * 打卡人姓名
     */
    private String userName;

    /**
     * 考勤ID
     */
    private String clockInId;

    /**
     * 考勤时间
     */
    private String clockInTime;

    /**
     * 考勤类型
     */
    private String clockInType;

    /**
     * 考勤名称
     */
    private String clockInName;

    /**
     * 考勤描述
     */
    private String clockInRemark;

    /**
     * 打卡时间
     */
    private Date actionTime;

    /**
     * 插入、修改人
     */
    private String insertUser;

    /**
     * 插入、修改时间
     */
    private Date insertDate;


    /**
     * 考勤备注
      */
    private String remark;


    public String getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getClockInId() {
        return clockInId;
    }

    public void setClockInId(String clockInId) {
        this.clockInId = clockInId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(String clockInTime) {
        this.clockInTime = clockInTime;
    }

    public String getClockInName() {
        return clockInName;
    }

    public void setClockInName(String clockInName) {
        this.clockInName = clockInName;
    }

    public String getClockInRemark() {
        return clockInRemark;
    }

    public void setClockInRemark(String clockInRemark) {
        this.clockInRemark = clockInRemark;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getClockInType() {
        return clockInType;
    }

    public void setClockInType(String clockInType) {
        this.clockInType = clockInType;
    }
}
