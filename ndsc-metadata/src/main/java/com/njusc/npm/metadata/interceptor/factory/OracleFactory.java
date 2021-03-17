package com.njusc.npm.metadata.interceptor.factory;

public class OracleFactory implements PagerInterceptorFactoryService {

    @Override
    public String getListSql(String sql,
                             int page,
                             int size) {
        int start = page * size;
        int end = (page - 1) * size;

        StringBuilder sb = new StringBuilder();
        sb.append("select * from (select rownum as rn, x.* from (");
        sb.append(sql);
        sb.append(") x where rownum <= ");
        sb.append(start);
        sb.append(") where rn > ");
        sb.append(end);

        return sb.toString();
    }

}
