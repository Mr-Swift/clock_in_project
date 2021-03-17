package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 请假信息
 */
public class TLeaveEntity extends BaseEntity {
    /**
     * 请假人
     */
    private String leaveUser;

    /**
     * 请假时间
     */
    private Date leaveStartDate;

    /**
     * 销假时间
     */
    private Date leaveEndDate;

    /**
     * 请假类型
     */
    private String leaveType;

    /**
     * 请假理由
     */
    private String leaveReason;

    /**
     * 审批人
     */
    private String checkUser;

    /**
     * 审批时间
     */
    private Date checkDate;

    /**
     * 审批状态
     */
    private Integer checkStatus;

    private String checkStatusStr;

    /**
     * 新增时间
     */
    private Date insertDate;
    /**
     * 新增人
     */
    private String insertUser;

    public String getLeaveUser() {
        return leaveUser;
    }

    public void setLeaveUser(String leaveUser) {
        this.leaveUser = leaveUser;
    }

    public Date getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(Date leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(Date leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
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

    public String getCheckStatusStr() {
        return checkStatusStr;
    }

    public void setCheckStatusStr(String checkStatusStr) {
        this.checkStatusStr = checkStatusStr;
    }

    @Override
    public String toString() {
        return "TLeaveEntity{" +
                "leaveUser='" + leaveUser + '\'' +
                ", leaveStartDate=" + leaveStartDate +
                ", leaveEndDate=" + leaveEndDate +
                ", leaveType='" + leaveType + '\'' +
                ", leaveReason='" + leaveReason + '\'' +
                ", checkUser='" + checkUser + '\'' +
                ", checkDate=" + checkDate +
                ", checkStatus=" + checkStatus +
                ", insertDate=" + insertDate +
                ", insertUser='" + insertUser + '\'' +
                ", id=" + id +
                '}';
    }
}
