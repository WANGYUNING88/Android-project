package com.example.newchat;

/**
 * Created by wang on 2018/6/12.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;

public class NewActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("msg");
        String msg = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.text_callback);
        textView.setText(msg);


    }
}