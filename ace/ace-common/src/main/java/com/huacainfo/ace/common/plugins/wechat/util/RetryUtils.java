package com.huacainfo.ace.common.plugins.wechat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常重试工具类
 *
 * @author L.cm
 */
public class RetryUtils {

    protected static Logger log = LoggerFactory.getLogger(RetryUtils.class);

    /**
     * 在遇到异常时尝试重试
     *
     * @param retryLimit    重试次数
     * @param retryCallable 重试回调
     * @return V
     */
    public static <V extends ResultCheck> V retryOnException(int retryLimit,
                                                             java.util.concurrent.Callable<V> retryCallable) {

        V v = null;
        for (int i = 0; i < retryLimit; i++) {
            try {
                v = retryCallable.call();
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("retry on " + (i + 1) + " times v = " + (v == null ? null : v.getJson()), e);
                }
            }
            if (v.matching()) break;
            log.error("retry on " + (i + 1) + " times but not matching v = " + (v == null ? null : v.getJson()));
        }
        return v;
    }

    /**
     * 在遇到异常时尝试重试
     *
     * @param retryLimit    重试次数
     * @param sleepMillis   每次重试之后休眠的时间
     * @param retryCallable 重试回调
     * @return V
     * @throws InterruptedException
     */
    public static <V extends ResultCheck> V retryOnException(int retryLimit, long sleepMillis,
                                                             java.util.concurrent.Callable<V> retryCallable) throws InterruptedException {

        V v = null;
        for (int i = 0; i < retryLimit; i++) {
            try {
                v = retryCallable.call();
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("retry on " + (i + 1) + " times v = " + (v == null ? null : v.getJson()), e);
                }
            }
            if (v.matching()) break;
            log.error("retry on " + (i + 1) + " times but not matching v = " + (v == null ? null : v.getJson()));
        }
        return v;
    }

    /**
     * 回调结果检查
     */
    public interface ResultCheck {
        boolean matching();

        String getJson();
    }

}
