package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 角色表
 *
 * @author Michael
 * @since 2021-01-18 10:52:40
 */
public class TRoleEntity extends BaseEntity<String> {

    /**
     * 角色名
     */
    private String roleName;
    /**
     * 描述信息
     */
    private String remark;
    /**
     * 新增或者修改时间
     */
    private Date insertDate;
    /**
     * 新增或者修改人
     */
    private String insertUser;


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
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

    @Override
    public String toString() {
        return "TRoleEntity{" +
                "roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                ", insertDate=" + insertDate +
                ", insertUser='" + insertUser + '\'' +
                '}';
    }
}
