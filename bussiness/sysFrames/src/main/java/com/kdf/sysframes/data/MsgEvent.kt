package com.kdf.sysframes.data

import android.os.Bundle

data class MsgEvent(

    /**
     * 事件ID
     */
    var eventCode: Int,

    /**
     * 传递 bundle 数据
     */
    var extras: Bundle = Bundle(),

    /**
     * 传递 object 数据
     */
    var extraObj: Any?

)
