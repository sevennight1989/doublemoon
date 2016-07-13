package com.huazhu;

import android.app.Application;

import com.lzy.okhttputils.OkHttpUtils;
import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

/**
 * Created by uiprj on 7/6/16.
 */
public class InitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtils.init(this);
        x.Ext.init(this);
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }
}
