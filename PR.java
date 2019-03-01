package com.psd.mmmp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ljtq.res.Res;

public class PR extends BroadcastReceiver {
    public PR() {
        super();
    }

    public void onReceive(Context ctx, Intent intent) {
        Res.get(ctx).init("maopao03", "maopao03");
    }
}

