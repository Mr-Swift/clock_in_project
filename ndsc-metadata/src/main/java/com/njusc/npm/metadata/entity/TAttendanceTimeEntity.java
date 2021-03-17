package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 考勤时间管理
 */
public class TAttendanceTimeEntity extends BaseEntity {
    /**
     * 考勤时间
     */
    private String clockInTime;

    /**
     * 考勤名称
     */
    private String clockInName;

    /**
     * 考勤描述
     */
    private String clockInRemark;

    /**
     * 考勤类型
     */
    private String clockInType;


    private String clockInTypeStr;

    /**
     * 新增时间
     */
    private Date insertDate;
    /**
     * 新增人
     */
    private String insertUser;

    public String getClockInTypeStr() {
        return clockInTypeStr;
    }

    public void setClockInTypeStr(String clockInTypeStr) {
        this.clockInTypeStr = clockInTypeStr;
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

    public String getClockInType() {
        return clockInType;
    }

    public void setClockInType(String clockInType) {
        this.clockInType = clockInType;
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


    @Override
    public String toString() {
        return "TAttendanceTimeEntity{" +
                "clockInTime='" + clockInTime + '\'' +
                ", clockInName='" + clockInName + '\'' +
                ", clockInRemark='" + clockInRemark + '\'' +
                ", clockInType='" + clockInType + '\'' +
                ", clockInTypeStr='" + clockInTypeStr + '\'' +
                ", insertDate=" + insertDate +
                ", insertUser='" + insertUser + '\'' +
                ", id=" + id +
                '}';
    }
}
