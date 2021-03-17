package com.njusc.npm.metadata.interceptor;

import com.njusc.npm.metadata.interceptor.factory.DatabaseFactory;
import com.njusc.npm.metadata.interceptor.factory.PagerInterceptorFactoryService;
import com.njusc.npm.utils.util.Pagination;
import com.njusc.npm.utils.util.Util;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * 分页拦截器
 *
 * @author michael
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagerInterceptor implements Interceptor {

    private Logger log = Logger.getLogger(getClass());

    @SuppressWarnings("unchecked")
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());

        BoundSql boundSql = statementHandler.getBoundSql();
        // 原始sql
        final String curSql = boundSql.getSql().trim();
        // 非查询或查询查询不为Map，直接放行
        if (curSql.toLowerCase().indexOf("select") < 0 || !(boundSql.getParameterObject() instanceof Map)) {
            return invocation.proceed();
        }

        Map<String, Object> params = (Map<String, Object>) boundSql.getParameterObject();
        //为了兼容以前的分页
        Pagination pagination=null;
        if(params!=null&&(!params.containsKey("page")&&!params.containsKey("limit")))
        {
            Collection<Object> values = params.values();
            for (Object c : values) {
                if (c instanceof Pagination)
                {
                    pagination=(Pagination)c;
                    params.put("page", pagination.getPageIndex());
                    params.put("limit", pagination.getPageSize());
                    break;
                }
            }
        }

        if (Util.n(params) || !params.containsKey("page")) {
            return invocation.proceed();
        }

        if (Util.n(params.get("page"))) {
            params.put("page", PageObject.DEFAULT_PAGE);
        }

        if (!params.containsKey("limit") || Util.n(params.get("limit"))) {
            params.put("limit", PageObject.DEFAULT_SIZE);
        }

        // 移除参数中的前后空格
        params.forEach((k,
                        v) -> {
            String s = Util.n(v) ? "" : v.toString().trim();
            params.put(k, s);
        });

        Connection connection = (Connection) invocation.getArgs()[0];
        final String jdbcUrl = connection.getMetaData().getURL();

        int page = Integer.parseInt(params.get("page").toString());
        int size = Integer.parseInt(params.get("limit").toString());

        PagerInterceptorFactoryService factoryService = DatabaseFactory.createFactory(jdbcUrl);
        // 组装后的查询语句
        final String listSql = factoryService.getListSql(curSql, page, size);
        // 查询总记录
        final String countSql = factoryService.getCountSql(curSql);

        // 覆盖原来的SQL
        metaObject.setValue("delegate.boundSql.sql", listSql);
        // 查询总记录
        PreparedStatement countStatement = connection.prepareStatement(countSql);
        ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
        parameterHandler.setParameters(countStatement);

        ResultSet rs = null;
        try {
            rs = countStatement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                params.put("totalSize", count);
                if(pagination!=null)
                {
                    pagination.setTotal(count);
                }
            }
           
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        } finally {
            if (!Util.n(rs)) {
                rs.close();
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);

        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
