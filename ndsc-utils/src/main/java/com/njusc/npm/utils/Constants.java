package com.njusc.npm.utils;

/**
 * 全局常量
 * @author Michael
 */
public final class Constants {

    public static final String PROVINCE_UNIT_ALL_PATH="1|";//省的allpath
    public static final String PROVINCE_UNIT_ID="1";//省的地区id
    public static final String ADMIN_ID="1";//管理员id
    public static final Integer PROVINCE_UNIT_LEVEL=1;//管理员级别
    /**
     * 字典表中类型
     */
    public enum  BaseCodeEnum{

        DXDM("地形地貌","DXDM"),
        CJLX("村居类型","CJLX"),
        LJLX("联建类型","LJLX"),
        LJNR("联建内容","LJNR"),
        QYLX("企业类型","QYLX"),
        QYLX2("企业类型2","QYLX2"),
        ZFFS("支付方式","ZFFS"),
        YJDJ("预警等级","CJLX"),
        LJXQ("联建需求","LJXQ"),
        XMJD("项目阶段","XMJD"),
        JHTZ("计划投资","JHTZ"),
        JGLX("机构类型","JGLX"),
        FJLC("附件流程","FJLC"),
        ZJLX("证件类型","ZJLX"),
        XMTP("项目图片","XMTP"),
        TYSHXYDM("统一社会信用代码","TYSHXYDM"),
        WTLB("问题类别","WTLB"),
        ;
        private String key;//建
        private String value;//值

        BaseCodeEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 复杂审核状态（1未提交 2已提交 4初审通过 7初审不通过 8复审不通过 9复审通过）
     */
    public enum  CheckStatusComplexEnum{

        UN_SUBMIT("未提交","1"),
        SUBMIT("已提交","2"),
        FIRST_CHECK_PASS("初审通过","4"),
        SECOND_CHECK_PASS("复审通过","9"),
        FIRST_CHECK_REFUSE("初审不通过","7"),
        SECOND_CHECK_REFUSE("复审不通过","8"),
        ;
        private String key;//建
        private String value;//值

        CheckStatusComplexEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 操作日志模块类型 1、涉农村居  2、合作需求 3、合作意向确认 4、合作项目 collaborate
     */
    public enum OperateLogModelEnum{

        VILL_MODEL("涉农村居","1"),
        DEMAND_MODEL("合作需求","2"),
        COLL_SURE_MODEL("合作意向确认","3"),
        COLL_PRO_MODEL("合作项目","4"),
        ;
        private String key;//建
        private String value;//值

        OperateLogModelEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     *1、已提交 2、初审通过 3、初审不通过 6、复审通过 7、复审不通过 8、审定不通过、9、审定通过  10勘核通过 11勘核不通过
     */
    public enum OperateLogTypeEnum{

        LOG_SUBMIT("已提交","1"),
        LOG_FIRST_CHECK_PASS("初审通过","2"),
        LOG_FIRST_CHECK_NO_PASS("初审不通过","3"),
        LOG_SECOND_CHECK_PASS("复审通过","6"),
        LOG_SECOND_CHECK_NO_PASS("复审不通过","7"),
        LOG_THIRD_CHECK_PASS("审定通过","9"),
        LOG_THIRD_CHECK_NO_PASS("审定不通过","8"),
        LOG_KH_CHECK_PASS("勘核通过","10"),
        LOG_KH_CHECK_NO_PASS("勘核不通过","11"),
        ;
        private String key;//建
        private String value;//值

        OperateLogTypeEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 简单审核状态（1已提交 8审核不通过 9审核通过）
     */
    public enum  CheckStatusEasyEnum{

        SUBMIT("已提交","2"),
        CHECK_PASS("复审通过","9"),
        CHECK_REFUSE("复审不通过","8"),
        ;
        private String key;//建
        private String value;//值

        CheckStatusEasyEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 附件流程
     */
    public enum  FileFlowEnum{

        VILLAGE_INFO("村居信息","1"),
        PRO_INFO("项目信息信息","2"),
        PRO_INFO_START("项目启动证明材料","22"),
        PRO_INFO_IMG("项目现场照片","26"),
        PRO_INFO_IMG_KH("项目现场堪核照片","33"),
        DEMAND_INFO("项目图片","3"),
        COMPANY_INFO("企业信息","4"),
        //PROCESS_LJXY("项目过程跟踪-联建协议","6"),
        PROCESS_PROPLAN("项目过程跟踪-项目计划","7"),
        PROCESS_EXPERIENCE("项目过程跟踪-经验总结","8"),
        PAIR_PRO_INFO_FILE("结对项目信息-联建协议","13"),
//        PAIR_PRO_INFO("结对项目信息","21"),
        USER_PICTURE("用户头像","11"),
        LOAN_REGISTER("贷款备案登记","15"),
        TYSHXYDM_INFO("统一社会信用代码","20"),

        ;
        private String key;//建
        private String value;//值

        FileFlowEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     *  行政级别
     */
    public enum  AreaLevel{

        PROVINCE("省",1),
        CITY("市",2),
        AREA("区县",3),
        TOWN("镇",4),
        VILLAGE("村",5),
        ;

        private String name;//名称
        private Integer level;//级别
        AreaLevel(String name,Integer level)
        {
            this.name=name;
            this.level=level;
        }

        public String getName() {
            return name;
        }

        public Integer getLevel() {
            return level;
        }

    }
    /**
     * 后台用户状态
     */
    public enum  BackStageUserStatusEnum{

        ENABLE("在职",1),
        DISABLED("离职",2),
        ;
        private String key;//建
        private Integer value;//值

        BackStageUserStatusEnum(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }

}
