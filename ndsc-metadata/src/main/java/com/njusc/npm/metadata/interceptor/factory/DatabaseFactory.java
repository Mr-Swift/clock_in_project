package com.njusc.npm.metadata.interceptor.factory;

import com.njusc.npm.metadata.interceptor.enumerate.DatabaseTypeEnum;
import com.njusc.npm.utils.util.Util;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库工厂
 * 
 * @author wuxb
 *
 */
public class DatabaseFactory {

    private static Logger log = Logger.getLogger(DatabaseFactory.class);

    private static final Map<DatabaseTypeEnum, PagerInterceptorFactoryService> factoryService =
                    new ConcurrentHashMap<>(DatabaseTypeEnum.values().length);

    public static PagerInterceptorFactoryService createFactory(final String jdbcUrl) {
        switch (dbtype(jdbcUrl)) {
            case MYSQL:
                if (Util.n(factoryService.get(DatabaseTypeEnum.MYSQL))) {
                    factoryService.put(DatabaseTypeEnum.MYSQL, new MySqlFactory());
                }

                return factoryService.get(DatabaseTypeEnum.MYSQL);
            case ORACLE:
                if (Util.n(factoryService.get(DatabaseTypeEnum.ORACLE))) {
                    factoryService.put(DatabaseTypeEnum.ORACLE, new OracleFactory());
                }

                return factoryService.get(DatabaseTypeEnum.ORACLE);
            default:
            	log.info("invalid jdbc url, url = " + jdbcUrl);
                break;
        }
        return null;
    }

    private static DatabaseTypeEnum dbtype(final String jdbcUrl) {
        if (jdbcUrl.startsWith("jdbc:mysql")) {
            return DatabaseTypeEnum.MYSQL;
        } else if (jdbcUrl.startsWith("jdbc:oracle")) {
            return DatabaseTypeEnum.ORACLE;
        } else {
            log.info("invalid jdbc url, url = " + jdbcUrl);
        }
        return null;
    }
}
