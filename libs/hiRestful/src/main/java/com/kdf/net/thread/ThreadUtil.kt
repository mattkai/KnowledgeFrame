package com.kdf.net.thread

/**
 * 线程工具接口
 */
import android.os.Handler
import android.os.Looper

/**
 * 后台线程的 ExecutorService
 */
private val BG_THREAD_EXECUTOR = ThreadPoolFactory.getCachedThreadPool()

/**
 * 后台线程执行
 */
fun bgThreadRun(f: () -> Unit) {
    BG_THREAD_EXECUTOR.execute(f)
}

/**
 * ui 主线程 执行
 */
fun mainThreadRun(f: () -> Unit) {
    if (isMainThread()) {
        f()
    } else {
        MainExecutor.mainHandler.post(f)
    }
}

/**
 * 是否是Ui 主线程
 */
fun isMainThread(): Boolean = Looper.getMainLooper().thread.id == Thread.currentThread().id

private object MainExecutor {
    val mainHandler by lazy {
        Handler(Looper.getMainLooper())
    }
}