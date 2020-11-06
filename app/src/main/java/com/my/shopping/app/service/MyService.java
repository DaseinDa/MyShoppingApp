package com.my.shopping.app.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import com.my.shopping.app.beans.OrderInfo;

public class MyService extends Service {

    private BroadcastReceiver mInfoReceiver;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initBroadcastReceiver();

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    /**
     * 初始化广播
     */
    private void initBroadcastReceiver() {
        final IntentFilter filter = new IntentFilter();
        filter.addAction("pay");

        mInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                OrderInfo  mOrderInfo = (OrderInfo) intent.getSerializableExtra("info");
                mOrderInfo.save();

            }
        };
        //注册广播
        registerReceiver(mInfoReceiver, filter);
    }





}
