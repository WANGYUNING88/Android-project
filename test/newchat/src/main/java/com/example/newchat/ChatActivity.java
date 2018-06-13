package com.example.newchat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by wang on 2018/6/12.
 */

public class ChatActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_111);


    }
    //    public void onEventMainThread(MessageEvent event) {
//        Message msg = event.getMessage();
//        switch (msg.getContentType()) {
//            case text:
//                // 处理文字消息
//                TextContent textContent = (TextContent) msg.getContent();
//                textContent.getText();
//                textView.setText(textContent.getText());
//                break;
//        }
//    }
    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
