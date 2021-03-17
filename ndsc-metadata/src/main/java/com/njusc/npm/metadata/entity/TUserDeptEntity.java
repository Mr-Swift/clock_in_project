package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

public class TUserDeptEntity extends BaseEntity<String> {
    /**
     * 部门名称
     */
    private String postId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 岗位名称
     */
    private String postName;
    /**
     * 工号
     */
    private String personNo;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 性别 1男 2女
     */
    private Integer sex;
    /**
     * 联系方式
     */
    private String telephone;
    /**
     * 基本工资（元）
     */
    private Double salary;
    /**
     * 在职状态 1在职 2离职
     */
    private Integer userStatus;
    /**
     * 身份证号码
     */
    private String cardNo;
    /**
     * 生日
     */
    private Date birthDate;
    /**
     * 名族
     */
    private String ethnic;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 户籍地址
     */
    private String censusRegisterAddress;
    /**
     * 家庭地址
     */
    private String familyAddress;
    /**
     * 婚姻状态,对应字典表
     */
    private String marriageStatus;
    /**
     * 紧急联系人
     */
    private String hurryContractPerson;
    /**
     * 紧急联系电话
     */
    private String hurryContractTelephone;
    /**
     * 毕业院校
     */
    private String school;
    /**
     * 专业名称
     */
    private String nameOfMajor;
    /**
     * 毕业时间
     */
    private Date graduateDate;
    /**
     * 最高学历,对应字典表
     */
    private String highLevel;
    /**
     * 入职时间
     */
    private Date entryCompanyDate;
    /**
     * 转正日期
     */
    private Date regularWorkDate;
    /**
     * 第一期合同起时间
     */
    private Date firstContractStartDate;
    /**
     * 第一期合同止时间
     */
    private Date firstContractEndDate;
    /**
     * 第二期合同起时间
     */
    private Date secondContractStartDate;
    /**
     * 第二期合同止时间
     */
    private Date secondContractEndDate;
    /**
     * 第三期合同起时间
     */
    private Date thirdContractStartDate;
    /**
     * 第三期合同止时间
     */
    private Date thirdContractEndDate;
    /**
     * 新增人员
     */
    private String insertUser;
    /**
     * 新增时间
     */
    private Date insertDate;
    /**
     * 部门id
     */
    private String deptId;
    /**
     * 是否部门经理
     */
    private int isDeptManager;

    public int getIsDeptManager() {
        return isDeptManager;
    }

    public void setIsDeptManager(int isDeptManager) {
        this.isDeptManager = isDeptManager;
    }

    public String getDeptName() {
        return deptName;
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

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getCensusRegisterAddress() {
        return censusRegisterAddress;
    }

    public void setCensusRegisterAddress(String censusRegisterAddress) {
        this.censusRegisterAddress = censusRegisterAddress;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getHurryContractPerson() {
        return hurryContractPerson;
    }

    public void setHurryContractPerson(String hurryContractPerson) {
        this.hurryContractPerson = hurryContractPerson;
    }

    public String getHurryContractTelephone() {
        return hurryContractTelephone;
    }

    public void setHurryContractTelephone(String hurryContractTelephone) {
        this.hurryContractTelephone = hurryContractTelephone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getNameOfMajor() {
        return nameOfMajor;
    }

    public void setNameOfMajor(String nameOfMajor) {
        this.nameOfMajor = nameOfMajor;
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }

    public String getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(String highLevel) {
        this.highLevel = highLevel;
    }

    public Date getEntryCompanyDate() {
        return entryCompanyDate;
    }

    public void setEntryCompanyDate(Date entryCompanyDate) {
        this.entryCompanyDate = entryCompanyDate;
    }

    public Date getRegularWorkDate() {
        return regularWorkDate;
    }

    public void setRegularWorkDate(Date regularWorkDate) {
        this.regularWorkDate = regularWorkDate;
    }

    public Date getFirstContractStartDate() {
        return firstContractStartDate;
    }

    public void setFirstContractStartDate(Date firstContractStartDate) {
        this.firstContractStartDate = firstContractStartDate;
    }

    public Date getFirstContractEndDate() {
        return firstContractEndDate;
    }

    public void setFirstContractEndDate(Date firstContractEndDate) {
        this.firstContractEndDate = firstContractEndDate;
    }

    public Date getSecondContractStartDate() {
        return secondContractStartDate;
    }

    public void setSecondContractStartDate(Date secondContractStartDate) {
        this.secondContractStartDate = secondContractStartDate;
    }

    public Date getSecondContractEndDate() {
        return secondContractEndDate;
    }

    public void setSecondContractEndDate(Date secondContractEndDate) {
        this.secondContractEndDate = secondContractEndDate;
    }

    public Date getThirdContractStartDate() {
        return thirdContractStartDate;
    }

    public void setThirdContractStartDate(Date thirdContractStartDate) {
        this.thirdContractStartDate = thirdContractStartDate;
    }

    public Date getThirdContractEndDate() {
        return thirdContractEndDate;
    }

    public void setThirdContractEndDate(Date thirdContractEndDate) {
        this.thirdContractEndDate = thirdContractEndDate;
    }

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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
