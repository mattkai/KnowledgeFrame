package com.kdf.net.thread

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 线程池工厂
 */
object ThreadPoolFactory : IThreadPoolFactory {

    private val mCachedThreadPool: ExecutorService by lazy {
        Executors.newCachedThreadPool()
    }

    override fun getCachedThreadPool(): ExecutorService {
        return mCachedThreadPool
    }

}
