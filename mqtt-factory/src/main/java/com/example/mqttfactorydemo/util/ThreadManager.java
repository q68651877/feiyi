package com.example.mqttfactorydemo.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理工具类
 *
 * @author peiyuxiang
 * @date 2021/1/21
 */
public class ThreadManager {
    /**
     * 通过ThreadPoolExecutor的代理类来对线程池的管理
     */
    public static class ThreadPoolProxy {
        /**
         * 线程池执行者 ，java内部通过该api实现对线程池管理
         */
        private ThreadPoolExecutor poolExecutor;

        private Integer corePoolSize;
        private Integer maximumPoolSize;
        private Long keepAliveTime;
        private Integer boundedQueueSize;

        public ThreadPoolProxy(Integer corePoolSize,
                               Integer maximumPoolSize,
                               Long keepAliveTime,
                               Integer boundedQueueSize) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
            this.boundedQueueSize = boundedQueueSize;
        }

        /**
         * 异步执行方法，
         * 队列排满且超过maximumPoolSize时候抛出RejectedExecutionException异常
         *
         * @param r 待执行方法
         */
        public void execute(Runnable r) {
            if (null == this.poolExecutor || this.poolExecutor.isShutdown()) {
                this.poolExecutor = new ThreadPoolExecutor(
                        this.corePoolSize,
                        this.maximumPoolSize,
                        this.keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<>(this.boundedQueueSize),
                        Executors.defaultThreadFactory()
                );
            }

            // 执行线程
            this.poolExecutor.execute(r);
        }

    }

    /**
     * 主线程池，核心业务使用
     */
    private volatile static ThreadPoolProxy primaryThreadPoolProxy;

    /**
     * 次要线程池，辅助业务使用
     */
    private volatile static ThreadPoolProxy secondaryThreadPoolProxy;

    /**
     * 主线程业务
     *
     * @return 核心线程池对象
     */
    public static ThreadPoolProxy primaryThreadPoolProxy() {
        if (null != primaryThreadPoolProxy) {
            return primaryThreadPoolProxy;
        }

        synchronized (ThreadPoolProxy.class) {
            if (null == primaryThreadPoolProxy) {
                Integer corePoolSize = 10;
                Integer maximumPoolSize = 80;
                Long keepAliveTime = 30000L;
                Integer boundedQueueSize = 3;
                primaryThreadPoolProxy = new ThreadPoolProxy(corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        boundedQueueSize);
            }
        }

        return primaryThreadPoolProxy;
    }

    /**
     * 次要线程池对象
     *
     * @return 次要线程池对象
     */
    public static ThreadPoolProxy secondaryThreadPoolProxy() {
        if (null != secondaryThreadPoolProxy) {
            return secondaryThreadPoolProxy;
        }

        synchronized (ThreadPoolProxy.class) {
            if (null == secondaryThreadPoolProxy) {
                Integer corePoolSize = 10;
                Integer maximumPoolSize = 50;
                Long keepAliveTime = 30000L;
                Integer boundedQueueSize = 8;
                secondaryThreadPoolProxy = new ThreadPoolProxy(corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        boundedQueueSize);
            }
        }

        return secondaryThreadPoolProxy;
    }

}
