package com.grupocisc.healthmonitor.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SendDataMyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context ctx, Intent intent) {
        ctx.startService(new Intent(ctx, SendDataMyService.class));

    }
}
