package com.mohammed.shameem.addressbook.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FlashService extends Service {
    public FlashService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
