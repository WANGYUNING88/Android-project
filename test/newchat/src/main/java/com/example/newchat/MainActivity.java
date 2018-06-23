package com.example.newchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;

public class MainActivity extends AppCompatActivity {
    private static String SUCCESSED_1 = "注册成功!!!";
    private static String FAILED_1 = "注册失败!!!";

    private static String SUCCESSED_2 = "登录成功!!!";
    private static String FAILED_2 = "登录失败!!!";

    private Message message;

    private TextView textView;
    private String username;
    private String password;
    private EditText editText1;
    private EditText editText2;
    private EditText friend_username;
    private EditText sendText;
    private Button button_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JMessageClient.registerEventReceiver(this);

        editText1 = findViewById(R.id.username);
        editText2 = findViewById(R.id.password);
        textView = findViewById(R.id.text_callback);
        friend_username = findViewById(R.id.friend_text);
        sendText = findViewById(R.id.send_text);
        button_send = findViewById(R.id.send);

        String ip = IpUtil.getLocalHostAddress();
        Toast.makeText(this,ip,Toast.LENGTH_LONG).show();

        Button button = findViewById(R.id.btn_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editText1.getText().toString();
                password = editText2.getText().toString();
                Log.e("username", username);
                Log.e("password", password);
                JMessageClient.register(username, password, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            // 注册成功
                            textView.setText(SUCCESSED_1);
                        } else {
                            // 注册失败。status：错误码；desc：错误描述
                            textView.setText(FAILED_1 + "  " + s);
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
                Log.e("username", username);
                Log.e("password", password);
                JMessageClient.login(username, password, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i == 0) {
                            // 登录成功
                            textView.setText(SUCCESSED_2);

                        } else {
                            // 登录失败。status：错误码；desc：错误描述
                            textView.setText(FAILED_2 + "  " + s);
                        }
                    }
                });
            }
        });
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friend_id = friend_username.getText().toString();
                Conversation.createSingleConversation(friend_id);
                Conversation mConversation = JMessageClient.getSingleConversation(friend_id);
                String content = sendText.getText().toString();
                message = mConversation.createSendMessage(new TextContent(content));
                message.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseDesc) {
                        if (responseCode == 0) {
                            //消息发送成功
                            Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                        } else {
                            //消息发送失败
                            Toast.makeText(MainActivity.this, "发送失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                MessageSendingOptions options = new MessageSendingOptions();
                options.setRetainOffline(false);

                JMessageClient.sendMessage(message);//使用默认控制参数发送消息
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

    @Override
    protected void onDestroy() {
        JMessageClient.unRegisterEventReceiver(this);
        super.onDestroy();
    }

    public void onEventMainThread(MessageEvent event) {
        Message msg = event.getMessage();
        switch (msg.getContentType()) {
            case text:
                // 处理文字消息
                TextContent textContent = (TextContent) msg.getContent();
                textContent.getText();
                Msg msg1 = new Msg(textContent.getText(), Msg.TYPE_RECEIVED);
                Content.msgList.add(msg1);
                Content.adapter.notifyDataSetChanged();
                break;
        }
    }
    public void onEvent(NotificationClickEvent event) {
        Intent notificationIntent = new Intent(this, NewActivity.class);
        String name = event.getMessage().getFromID();
        notificationIntent.putExtra("username",name);
        this.startActivity(notificationIntent);// 自定义跳转到指定页面
    }
}
