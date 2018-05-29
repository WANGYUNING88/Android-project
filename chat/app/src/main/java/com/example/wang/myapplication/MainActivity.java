package com.example.wang.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.messagec.observer.IObservable;
import com.example.messagec.observer.IObserver;
import com.example.messagec.observer.ObserverHolder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IObserver {

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @BindView(R.id.til_message)
    TextInputLayout tilMessage;
    @BindView(R.id.btn_send_msg)
    Button btnSendMsg;
    @BindView(R.id.txt_message)
    TextView txtMessage;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startService(new Intent(this, ReceiverMessageService.class));
        new Thread(new Runnable() {
            @Override
            public void run() {
                tcpSend = new TCPSend("127.0.0.1",10012);
            }
        }).start();
        ObserverHolder.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ObserverHolder.getInstance().unregister(this);
    }

    @Override
    public void onMessageReceived(IObservable observable, final Object msg, int flag) {
        switch (flag) {
            case ObserverHolder.RECEIVER_MESSAGE:
                Log.i("=+++++=",msg+"");
                runOnUiThread(new Runnable() {  //切换到主线程更新ui
                    @Override
                    public void run() {
                        txtMessage.setText(msg+"");
                    }
                });

                break;
        }
    }

    @OnClick({R.id.btn_send_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_msg:
                sendMsg();
                break;
        }
    }

    /**
     * 消息发送的类
     */
    private TCPSend tcpSend;

    /**
     * 发送信息
     */
    private void sendMsg() {
        executorService.execute(runnable);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tcpSend.sendMessage(tilMessage.getEditText().getText().toString());
        }
    };

}