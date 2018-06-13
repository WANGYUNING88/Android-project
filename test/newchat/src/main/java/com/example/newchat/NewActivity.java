package com.example.newchat;

/**
 * Created by wang on 2018/6/12.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public class NewActivity extends Activity {


    private EditText inputText;
    private Button send;

    private EditText friendText;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.layout_chat);
//        JMessageClient.registerEventReceiver(this);
        Content.adapter = new MsgAdapter(NewActivity.this, R.layout.msg_item, Content.msgList);
        inputText = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        Content.msgListView = (ListView)findViewById(R.id.msg_list_view);
        Content.msgListView.setAdapter(Content.adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if(!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    Content.msgList.add(msg);
                    Content.adapter.notifyDataSetChanged();
                    Content.msgListView.setSelection(Content.msgList.size());
                    inputText.setText("");
                }
            }
        });


    }


//    public void onEventMainThread(MessageEvent event) {
//        Message msg = event.getMessage();
//        switch (msg.getContentType()) {
//            case text:
//                // 处理文字消息
//                TextContent textContent = (TextContent) msg.getContent();
//
//                Toast.makeText(this,textContent.getText(),Toast.LENGTH_SHORT).show();
//                Msg msg1 = new Msg(textContent.getText(), Msg.TYPE_RECEIVED);
//                Content.msgList.add(msg1);
//                Content.adapter.notifyDataSetChanged();
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
//    @Override
//    protected void onDestroy() {
//        JMessageClient.unRegisterEventReceiver(this);
//        super.onDestroy();
//    }
}

