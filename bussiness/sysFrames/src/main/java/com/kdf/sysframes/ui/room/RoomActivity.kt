package com.kdf.sysframes.ui.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kdf.hilog.HiLog
import com.kdf.sysframes.R
import com.kdf.sysframes.data.CnStudent
import com.kdf.sysframes.databinding.ActivityRoomBinding
import com.kdf.sysframes.db.AppDataBase

class RoomActivity: AppCompatActivity() {

    private lateinit var mDataBinding: ActivityRoomBinding

    private val dataBase: AppDataBase by lazy {
        val dataBase = AppDataBase.getInstance(this)
        dataBase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_room)
        mDataBinding.apply {

            btnDbAdd.setOnClickListener {
                dataBase.getCnStuDao().addStu(CnStudent(123,"wukai",23,1))
                dataBase.getCnStuDao().addStu(CnStudent(125,"gaoli",26,1))
            }

            btnDbDel.setOnClickListener {
                dataBase.getCnStuDao().deleteStuById(125)
            }

            btnDbQuery.setOnClickListener {
                val list = dataBase.getCnStuDao().selectAll()
                list.forEach {
                    HiLog.d(it.toString())
                }
            }

            btnDbUpd.setOnClickListener {
                dataBase.getCnStuDao().updStu(CnStudent(123,"wukai",45,1))
            }

        }
    }

}