package com.kdf.net.thread

import java.util.concurrent.ExecutorService

/**
 * 线程池工厂 接口
 */
interface IThreadPoolFactory {

    /**
     * 获取 CachedThreadPool，该线程池的特点是：没有队列，有可用线程就用可用线程，没有就启动新线程
     */
    fun getCachedThreadPool(): ExecutorService
}