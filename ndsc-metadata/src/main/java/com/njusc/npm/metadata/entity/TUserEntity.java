package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 人员表
 *
 * @author Michael
 * @since 2021-01-18 10:52:41
 */
public class TUserEntity extends BaseEntity<String> {
    /**
     * 工号（登录账号）
     */
    private String personNo;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别
     */
    private Integer sex;

    private String sexStr;

    /**
     * 联系方式（电话号码）
     */
    private String telephone;

    /**
     * 工资
     */
    private double salary;

    /**
     * 在职状态
     */
    private Integer userStatus;

    private String userStatusStr;

    /**
     * 身份证号
     */
    private String cardNo;

    /**
     * 生日
     */
    private Date birthDate;

    /**
     * 民族
     */
    private String ethnic;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     *户籍地址
     */
    private String censusRegisterAddress;

    /**
     *家庭住址
     */
    private String familyAddress;

    /**
     *婚姻状态
     */
    private String marriageStatus;

    /**
     *紧急联系人
     */
    private String hurryContractPerson;

    /**
     *紧急联系电话
     */
    private String hurryContractTelephone;

    /**
     *毕业院校
     */
    private String school;

    /**
     *专业名称
     */
    private String nameOfMajor;

    /**
     *毕业时间
     */
    private Date graduateDate;

    /**
     *最高学历
     */
    private String highLevel;

    /**
     *入职时间
     */
    private Date entryDate;

    /**
     *转正时间
     */
    private Date regularWorkDate;

    /**
     *第一合同起始时间
     */
    private Date firstContractStartDate;

    /**
     *第一合同结束时间
     */
    private Date firstContractEndDate;

    /**
     *第二合同起始时间
     */
    private Date secondContractStartDate;

    /**
     *第二合同结束时间
     */
    private Date secondContractEndDate;

    /**
     *第三合同起始时间
     */
    private Date thirdContractStartDate;

    /**
     *第三合同结束时间
     */
    private Date thirdContractEndDate;

    /**
     *新增、修改人
     */
    private String insertUser;

    /**
     *新增、修改时间
     */
    private Date insertDate;

    /**
     *部门ID
     */
    private String deptId;


    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 岗位ID
     */
    private String postId;


    /**
     * 岗位名称
     */
    private String postName;


    /**
     *用户密码
     */
    private String userPassword;

    /**
     *是否部门经理
     */
    private Integer isDeptManager;


    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
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

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatusStr() {
        return userStatusStr;
    }

    public void setUserStatusStr(String userStatusStr) {
        this.userStatusStr = userStatusStr;
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

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getIsDeptManager() {
        return isDeptManager;
    }

    public void setIsDeptManager(Integer isDeptManager) {
        this.isDeptManager = isDeptManager;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return "TUserEntity{" +
                "personNo='" + personNo + '\'' +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", sexStr='" + sexStr + '\'' +
                ", telephone='" + telephone + '\'' +
                ", salary=" + salary +
                ", userStatus=" + userStatus +
                ", userStatusStr='" + userStatusStr + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", birthDate=" + birthDate +
                ", ethnic='" + ethnic + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", censusRegisterAddress='" + censusRegisterAddress + '\'' +
                ", familyAddress='" + familyAddress + '\'' +
                ", marriageStatus='" + marriageStatus + '\'' +
                ", hurryContractPerson='" + hurryContractPerson + '\'' +
                ", hurryContractTelephone='" + hurryContractTelephone + '\'' +
                ", school='" + school + '\'' +
                ", nameOfMajor='" + nameOfMajor + '\'' +
                ", graduateDate=" + graduateDate +
                ", highLevel='" + highLevel + '\'' +
                ", entryDate=" + entryDate +
                ", regularWorkDate=" + regularWorkDate +
                ", firstContractStartDate=" + firstContractStartDate +
                ", firstContractEndDate=" + firstContractEndDate +
                ", secondContractStartDate=" + secondContractStartDate +
                ", secondContractEndDate=" + secondContractEndDate +
                ", thirdContractStartDate=" + thirdContractStartDate +
                ", thirdContractEndDate=" + thirdContractEndDate +
                ", insertUser='" + insertUser + '\'' +
                ", insertDate=" + insertDate +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", isDeptManager=" + isDeptManager +
                '}';
    }
}
