package com.psd.mmmp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.ljtq.res.Res;

public class P extends Service {
    public P() {
        super();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("res", "onStartCommand");
        Res.get(this.getApplicationContext()).init("maopao03", "maopao03");
        return super.onStartCommand(intent, flags, startId);
    }
}

