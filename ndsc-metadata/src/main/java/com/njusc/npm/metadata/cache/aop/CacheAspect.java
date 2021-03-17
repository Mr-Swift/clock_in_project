///**
// * ResultCacheAj.java
// */
//package com.njusc.wqlwc.metadata.cache.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.njusc.wqlwc.metadata.cache.annotation.CacheOperate;
//import com.njusc.wqlwc.metadata.cache.annotation.CacheStoreName;
//import com.njusc.wqlwc.metadata.cache.strategy.CacheStrategy;
//import com.njusc.wqlwc.metadata.entity.BaseEntity;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author jinzf
// * @date May 13, 2015
// * @description TODO1
// * @version 1.0
// */
//public class CacheAspect {
//
//    protected Logger log = LoggerFactory.getLogger(CacheAspect.class);
//
//    protected CacheStrategy strategy;
//
//    public Object execute(ProceedingJoinPoint point) throws Throwable {
//        Object ro;
//        Object target = point.getTarget();
//        // String methodName = point.getSignature().getName();
//        Object[] args = point.getArgs();
//        // Class[] parameterTypes = ((MethodSignature) point
//        // .getSignature()).getMethod().getParameterTypes();
//
//        try {
//            // 通过反射获得拦截的method
//            MethodSignature methodSignature = ((MethodSignature) point
//                    .getSignature());
//            Method m = methodSignature.getMethod();
//            CacheOperate co = m.getAnnotation(CacheOperate.class);
//            if (co != null) {
//                Type[] ts = target.getClass().getGenericInterfaces();
//                CacheStoreName csn = ((Class<?>) ts[0])
//                        .getAnnotation(CacheStoreName.class);
//                if (csn == null) {
//                    return point.proceed();
//                }
//                String ck;
//                Object id = args[co.cacheId()];
//                if (id instanceof Number) {
//                    ck = csn.cacheNames()[0] + id;
//                } else if (id instanceof BaseEntity) {
//                    ck = csn.cacheNames()[0] + ((BaseEntity) id).getId();
//                } else {
//                    return point.proceed();
//                }
//
//                switch (co.Operate()) {
//                    case 添加至缓存:
//                        ro = this.strategy.get(ck, csn.targetClazz());
//                        if (ro != null) {
//                            return ro;
//                        }
//
//                        ro = point.proceed();
//                        if (ro instanceof Number) {
//                            ck = csn.cacheNames()[0] + ro;
//                        } else if (ro instanceof BaseEntity) {
//                            ck = csn.cacheNames()[0]
//                                    + ((BaseEntity) ro).getId();
//                        }
//
//                        log.debug("添加到redis key={}\tvalue={}", ck,
//                                JSON.toJSONString(ro));
//                        this.strategy.add(ck, ro, 1L, TimeUnit.DAYS);
//                        return ro;
//                    case 清除缓存:
//                        ro = point.proceed();
//                        if(this.strategy.remove(ck)) {
//                            log.debug("清理到redis key=" + ck);
//                        }
//                        return ro;
//                    case 添加至缓存列表:
//                        ro = point.proceed();
//                        @SuppressWarnings("unchecked")
//                        List<Object> list = (List<Object>) ro;
//                        for (Object o : list) {
//                            if (o instanceof Number) {
//                                log.debug("添加到redis key=" + ck + "value="
//                                        + JSON.toJSONString(id));
//                                ck = csn.cacheNames()[0] + o;
//                                this.strategy.add(ck, id, 1L, TimeUnit.DAYS);
//                            } else if (id instanceof BaseEntity) {
//                                log.debug("添加到redis key=" + ck + "value="
//                                        + JSON.toJSONString(id));
//                                ck = csn.cacheNames()[0]
//                                        + ((BaseEntity) id).getId();
//                                this.strategy.add(ck, id, 1L, TimeUnit.DAYS);
//                            }
//                        }
//                        return list;
//                    case 清除缓存列表:
//                        ro = point.proceed();
//                        @SuppressWarnings("unchecked")
//                        List<Object> rlist = (List<Object>) id;
//                        for (Object o : rlist) {
//                            if (o instanceof Number) {
//                                log.debug("添加到redis key=" + ck + "value="
//                                        + JSON.toJSONString(id));
//                                ck = csn.cacheNames()[0] + o;
//                                log.debug("清理到redis key=" + ck);
//                                this.strategy.remove(ck);
//                            } else if (id instanceof BaseEntity) {
//                                log.debug("添加到redis key=" + ck + "value="
//                                        + JSON.toJSONString(id));
//                                ck = csn.cacheNames()[0]
//                                        + ((BaseEntity) id).getId();
//                                log.debug("清理到redis key=" + ck);
//                                this.strategy.remove(ck);
//                            }
//                        }
//                        return ro;
//                    default:
//                        break;
//                }
//            }
//        } catch (SecurityException e) {
//            log.error(e.getMessage(), e);
//        } catch (Throwable e) {
//            log.error(e.getMessage(), e);
//        }
//        return point.proceed();
//    }
//
//    public final CacheStrategy getStrategy() {
//        return strategy;
//    }
//
//    public final void setStrategy(CacheStrategy strategy) {
//        this.strategy = strategy;
//    }
//
//}
