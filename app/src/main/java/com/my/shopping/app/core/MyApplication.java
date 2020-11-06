package com.my.shopping.app.core;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import org.litepal.LitePal;


public class MyApplication extends Application {

    

    public Vibrator mVibrator;


    //整个app的上下文
    public static Context sContext;

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        sContext = getApplicationContext();

        LitePal.initialize(this);
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);



    }


       public static MyApplication getApplication() {
        return mInstance;
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getContext() {
        return sContext;
    }

}
