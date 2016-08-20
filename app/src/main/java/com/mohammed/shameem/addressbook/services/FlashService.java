package com.mohammed.shameem.addressbook.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FlashService extends Service {
    public FlashService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
