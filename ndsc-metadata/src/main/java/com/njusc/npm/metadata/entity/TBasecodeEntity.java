package com.njusc.npm.metadata.entity;

import com.njusc.base.bean.BaseEntity;

import java.util.Date;

/**
 * 字典表
 *
 * @author Michael
 * @since 2021-01-18 10:52:41
 */
public class TBasecodeEntity extends BaseEntity<String> {

    /**
     * 字典类型
     */
    private String baseType;
    /**
     * 数据名称
     */
    private String baseTypeName;
    /**
     * key值
     */
    private Integer dataKey;
    /**
     * value1值
     */
    private String dataValue1;
    /**
     * value2值
     */
    private String dataValue2;
    /**
     * value3值
     */
    private String dataValue3;
    /**
     * 新增时间
     */
    private Date insertDate;
    /**
     * 新增人
     */
    private String insertUser;
    /**
     * 父级id
     */
    private String parentId;


    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseTypeName(String baseTypeName) {
        this.baseTypeName = baseTypeName;
    }

    public String getBaseTypeName() {
        return baseTypeName;
    }

    public void setDataKey(Integer dataKey) {
        this.dataKey = dataKey;
    }

    public Integer getDataKey() {
        return dataKey;
    }

    public void setDataValue1(String dataValue1) {
        this.dataValue1 = dataValue1;
    }

    public String getDataValue1() {
        return dataValue1;
    }

    public void setDataValue2(String dataValue2) {
        this.dataValue2 = dataValue2;
    }

    public String getDataValue2() {
        return dataValue2;
    }

    public void setDataValue3(String dataValue3) {
        this.dataValue3 = dataValue3;
    }

    public String getDataValue3() {
        return dataValue3;
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

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }


    @Override
    public String toString() {
        return "TBasecodeEntity{" +
                "baseType='" + baseType + '\'' +
                ", baseTypeName='" + baseTypeName + '\'' +
                ", dataKey=" + dataKey +
                ", dataValue1='" + dataValue1 + '\'' +
                ", dataValue2='" + dataValue2 + '\'' +
                ", dataValue3='" + dataValue3 + '\'' +
                ", insertDate=" + insertDate +
                ", insertUser='" + insertUser + '\'' +
                ", parentId='" + parentId + '\'' +
                ", id=" + id +
                '}';
    }
}
