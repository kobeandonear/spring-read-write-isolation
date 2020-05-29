package com.gdw.database.isolation;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2020/5/28 - 15:30 by guowenhao6
 * email：guowenhao6@jd.com
 * 不生产代码 做bug的搬运工
 *
 * @author guowenhao6
 */
class DataSourceContextThreadHolder {
    private static ThreadLocal<String> dataSoureLocal = new InheritableThreadLocal<>();
    private static ThreadLocal<AtomicInteger> threadCount = new InheritableThreadLocal<>();

    static String getDataSoureLocal() {
        return dataSoureLocal.get();
    }

    static void setDataSoureLocal(String dataSourceName) {
        AtomicInteger atomicInteger = threadCount.get();
        if (atomicInteger == null) {
            dataSoureLocal.set(dataSourceName);
            threadCount.set(new AtomicInteger(0));
        } else {
            atomicInteger.incrementAndGet();
        }
    }

    static void countDecrementOrClean() {
        AtomicInteger atomicInteger = threadCount.get();
        if (atomicInteger != null) {
            if (atomicInteger.get() == 0) {
                dataSoureLocal.remove();
                threadCount.remove();
            } else {
                atomicInteger.decrementAndGet();
            }
        }
    }

    static void countIncrement() {
        threadCount.get().incrementAndGet();
    }
}
