package com.example.newchat;

/**
 * Created by wang on 2018/6/12.
 */
import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import cn.jpush.im.android.*;

public class NewActivity extends Activity {

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private EditText friendText;

    private List<Msg> msgList = new ArrayList<Msg>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        JMessageClient.registerEventReceiver(this);

        android.app.ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.layout_chat);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("msg");
//        String msg = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//        TextView textView = findViewById(R.id.text_callback);
//        textView.setText(msg);

        initMsgs();

        adapter = new MsgAdapter(NewActivity.this, R.layout.msg_item, msgList);
        inputText = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        msgListView = (ListView)findViewById(R.id.msg_list_view);
        friendText = (EditText)findViewById(R.id.friend);
        msgListView.setAdapter(adapter);send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String content = inputText.getText().toString();
                String friend_username = friendText.getText().toString();
                Conversation.createSingleConversation(friend_username);
                JMessageClient.getSingleConversation(friend_username);


                if(!"".equals(content)) {
                    Message message = JMessageClient.createSingleTextMessage(friend_username, content);
                    message.setOnSendCompleteCallback(new BasicCallback(){

                        @Override
                        public void gotResult(int i, String s) {
                            if (i==0){
                                Msg msg = new Msg(content, Msg.TYPE_SEND);
                                msgList.add(msg);
                                adapter.notifyDataSetChanged();
                                msgListView.setSelection(msgList.size());
                                inputText.setText("");
                            }else {
                                Toast.makeText(NewActivity.this,s,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }

    public void onEventMainThread(MessageEvent event) {

        Message msg = event.getMessage();
        switch (msg.getContentType()) {
            case text:
                // 处理文字消息
                TextContent textContent = (TextContent) msg.getContent();
                String m = textContent.getText().toString();
                Msg msg1 = new Msg(m, Msg.TYPE_RECEIVED);
                msgList.add(msg1);
                adapter.notifyDataSetChanged();
                msgListView.setSelection(msgList.size());
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }
    private void initMsgs() {
        Msg msg1 = new Msg("Hello, how are you?", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Fine, thank you, and you?", Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("I am fine, too!", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}

