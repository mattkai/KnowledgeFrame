package com.kdf.sysframes.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HiltxDoin @Inject constructor() {

    var adders: String? = null

    fun display() {
        println("this is hilt fucntion")
    }

}