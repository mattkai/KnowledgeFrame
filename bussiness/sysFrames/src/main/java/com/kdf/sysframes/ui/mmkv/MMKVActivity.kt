package com.kdf.sysframes.ui.mmkv

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.kdf.sysframes.R
import com.kdf.sysframes.manager.DataStoreManager

class MMKVActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mmkv)

        var btnSave = findViewById<Button>(R.id.btn_save_data)
        var tvMMKV = findViewById<TextView>(R.id.tv_mmkv)
        btnSave.setOnClickListener {
            DataStoreManager.instance.putString("MT01","good luck")
            tvMMKV.text = DataStoreManager.instance.getString("MT01","NUL")
        }

    }

}