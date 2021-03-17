package com.njusc.npm.metadata.interceptor.factory;

public class MySqlFactory implements PagerInterceptorFactoryService {

    @Override
    public String getListSql(String sql,
                             int page,
                             int size) {

        int start = (page - 1) * size;
        return sql + " limit " + start + "," + size;
    }

}
