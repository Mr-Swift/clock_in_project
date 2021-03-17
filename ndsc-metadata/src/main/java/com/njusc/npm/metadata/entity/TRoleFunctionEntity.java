package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 角色和菜单关系信息
 *
 * @author Michael
 * @since 2021-01-18 10:52:40
 */
public class TRoleFunctionEntity extends BaseEntity<String> {

    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名
     */
    private String roleName;

    /**
     * 菜单id
     */
    private String functionId;

    /**
     * 模块名
     */
    private String functionName;

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

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionId() {
        return functionId;
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

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public String toString() {
        return "TRoleFunctionEntity{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", functionId='" + functionId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", insertDate=" + insertDate +
                ", insertUser='" + insertUser + '\'' +
                ", id=" + id +
                '}';
    }
}
