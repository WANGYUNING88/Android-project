package com.example.newchat;

/**
 * Created by wang on 2018/6/12.
 */

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (JPushInterface.ACTION_MESSAGE_RECEIVED
                .equals(intent.getAction())) {
            //接收到推送下来的自定义消息
            Log.e("msg", "接收到推送下来的自定义消息");
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED
                .equals(intent.getAction())) {
            //接收到推送下来的通知
            Log.e("msg", "接收到推送下来的通知");
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED
                .equals(intent.getAction())) {
            //用户点击打开了通知

            intent.setClass(context, NewActivity.class);

            intent.putExtra("msg", intent.getExtras());
            context.startActivity(intent);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK
                .equals(intent.getAction())) {
            //用户收到到RICH PUSH CALLBACK

        }
    }
}
