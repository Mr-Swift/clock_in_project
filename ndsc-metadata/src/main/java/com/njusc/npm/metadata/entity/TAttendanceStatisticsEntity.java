package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;


/**
 *考勤统计
 */
public class TAttendanceStatisticsEntity extends BaseEntity<String> {

    /**
     * 职工ID
     */
    private String userId;

    /**
     * 职工姓名
     */
    private String userName;

    /**
     * 年份
     */
    private Integer dataYear;

    /**
     * 月份
     */
    private Integer dataMonth;

    /**
     * 迟到次数
     */
    private int late;


    /**
     * 早退次数
     */
    private int leaveEarly;

    /**
     * 平均工作时长
     */
    private double averageWorkingHours;

    /**
     * 出勤天数
     */
    private int attendanceDays;

    /**
     * 出勤班次
     */
    private int attendanceTimes;

    /**
     * 缺卡次数
     */
    private int missAttendance;

    /**
     * 旷工次数
     */
    private int absenteeism;

    /**
     * 外勤次数
     */
    private int workOutSide;

    /**
     * 加班时长
     */
    private double overTime;

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



    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getLeaveEarly() {
        return leaveEarly;
    }

    public void setLeaveEarly(int leaveEarly) {
        this.leaveEarly = leaveEarly;
    }

    public double getAverageWorkingHours() {
        return averageWorkingHours;
    }

    public void setAverageWorkingHours(double averageWorkingHours) {
        this.averageWorkingHours = averageWorkingHours;
    }

    public int getAttendanceDays() {
        return attendanceDays;
    }

    public void setAttendanceDays(int attendanceDays) {
        this.attendanceDays = attendanceDays;
    }

    public int getAttendanceTimes() {
        return attendanceTimes;
    }

    public void setAttendanceTimes(int attendanceTimes) {
        this.attendanceTimes = attendanceTimes;
    }

    public int getMissAttendance() {
        return missAttendance;
    }

    public void setMissAttendance(int missAttendance) {
        this.missAttendance = missAttendance;
    }

    public int getAbsenteeism() {
        return absenteeism;
    }

    public void setAbsenteeism(int absenteeism) {
        this.absenteeism = absenteeism;
    }

    public int getWorkOutSide() {
        return workOutSide;
    }

    public void setWorkOutSide(int workOutSide) {
        this.workOutSide = workOutSide;
    }

    public double getOverTime() {
        return overTime;
    }

    public void setOverTime(double overTime) {
        this.overTime = overTime;
    }


    public Integer getDataYear() {
        return dataYear;
    }

    public void setDataYear(Integer dataYear) {
        this.dataYear = dataYear;
    }

    public Integer getDataMonth() {
        return dataMonth;
    }

    public void setDataMonth(Integer dataMonth) {
        this.dataMonth = dataMonth;
    }
}
