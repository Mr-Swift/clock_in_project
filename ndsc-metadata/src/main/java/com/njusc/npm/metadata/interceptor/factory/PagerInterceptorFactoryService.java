package com.njusc.npm.metadata.interceptor.factory;

/**
 * 分页拦截工厂接口
 *
 * @author wuxb
 */
public interface PagerInterceptorFactoryService {

    /**
     * 拼接分页sql
     *
     * @param sql  原sql
     * @param page 当前页
     * @param size 当前容量
     * @return
     */
    String getListSql(String sql,
                      int page,
                      int size);

    /**
     * 查询总数
     *
     * @param sql 原sql
     * @return
     */
    default String getCountSql(String sql) {
        return "select count(1) from (" + sql + ") a";
    }
//
//
//        final String lowerSql = sql.toLowerCase();
//        int lastWhere = lowerSql.lastIndexOf("where");
//        int lastGroup = lowerSql.lastIndexOf("group");
//        int lastOrder = lowerSql.lastIndexOf("order");
//        int lastKuoHao = lowerSql.lastIndexOf(")");
//
//        StringBuilder str = new StringBuilder(sql);
//        StringBuilder findSql = new StringBuilder();
//        boolean flag = true;
//        //去除sql返回的多数据列
//        if (lastWhere > lastGroup || lastKuoHao > lastGroup) {
//            int fromIndex = lowerSql.indexOf("from");
//            str = new StringBuilder("select count(1) ");
//            str.append(sql.substring(fromIndex));
//            flag = false;
//        }
//
//        //去除多余的order by
//        if (lastOrder > lastWhere && lastOrder > lastGroup && lastOrder > lastKuoHao) {
//            str = new StringBuilder(str.substring(0, str.toString().toLowerCase().lastIndexOf("order")));
//        }
//
//        if (flag) {
//            findSql.append("select count(1) from ( ");
//            findSql.append(str);
//            findSql.append(" ) countSql");
//        } else {
//            findSql.append(str);
//        }
//        return findSql.toString();
//    }
}
