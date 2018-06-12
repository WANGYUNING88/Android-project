package com.example.newchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class MainActivity extends AppCompatActivity {
    private static String SUCCESSED_1 = "注册成功!!!";
    private static String FAILED_1 = "注册失败!!!";

    private static String SUCCESSED_2 = "登录成功!!!";
    private static String FAILED_2 = "登录失败!!!";

    private TextView textView;
    private String username;
    private String password;
    private EditText editText1;
    private EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        editText1 = findViewById(R.id.username);
        editText2 = findViewById(R.id.password);
        textView = findViewById(R.id.text_callback);


        Button button = findViewById(R.id.btn_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editText1.getText().toString();
                password = editText2.getText().toString();
                Log.e("username",username);
                Log.e("password",password);
                JMessageClient.register(username, password, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            // 注册成功
                            textView.setText(SUCCESSED_1);
                        } else {
                            // 注册失败。status：错误码；desc：错误描述
                            textView.setText(FAILED_1+"  "+s);
                        }
                    }
                });
            }
        });
        Button button1 = findViewById(R.id.btn_login);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editText1.getText().toString();
                password = editText2.getText().toString();
                Log.e("username",username);
                Log.e("password",password);
                JMessageClient.login(username, password, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            // 注册成功
                            textView.setText(SUCCESSED_2);
                        } else {
                            // 注册失败。status：错误码；desc：错误描述
                            textView.setText(FAILED_2+"  "+s);
                        }
                    }
                });
            }
        });
    }
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
