package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 用户角色管理
 *
 * @author Michael
 * @since 2021-01-18 10:52:40
 */
public class TUserRoleEntity extends BaseEntity<String> {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 新增或者修改时间
     */
    private Date insertDate;
    /**
     * 新增或者修改人
     */
    private String insertUser;

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TUserRoleEntity{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", insertDate=" + insertDate +
                ", insertUser='" + insertUser + '\'' +
                ", id=" + id +
                '}';
    }
}
