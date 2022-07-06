package com.kdf.more;

import android.app.Application;
import android.util.Log;

import com.kdf.more.manager.DataMKUtils;
import com.kdf.more.manager.UmengSetting;
import com.tencent.mmkv.MMKV;
import com.umeng.commonsdk.UMConfigure;

public class KnowLedgeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initMMKV();
        initUmeng();
    }

    private void initMMKV() {
        String filePath = getFilesDir().getAbsolutePath() + "/mmkv_1";
        String mmkvPath = MMKV.initialize(this,filePath);
        Log.d("mmkvPath",mmkvPath);
    }

    private void initUmeng() {
        UMConfigure.preInit(getApplicationContext(),"62c5864205844627b5dba91a","Umeng");
        boolean isProvicy = DataMKUtils.Companion.getInstance().getBoolean("isProvicy",false);
        if (isProvicy) {
            UmengSetting.INSTANCE.umengInit(getApplicationContext());
        }
    }

}
