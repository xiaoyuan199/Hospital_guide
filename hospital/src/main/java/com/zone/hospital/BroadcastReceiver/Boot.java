package com.zone.hospital.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zone.hospital.MainActivity;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class Boot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context.getApplicationContext(), MainActivity.class);
        intent1.setAction("android.intent.action.MAIN");
        intent1.addCategory("android.intent.category.LAUNCHER");
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
        Log.e("++++++++aa", "接受到开机广播");
    }
}

