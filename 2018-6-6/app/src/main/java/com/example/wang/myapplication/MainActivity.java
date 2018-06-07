package com.example.wang.myapplication;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private Socket socket = null;
    private EditText edit;
    private Button send,disconnect;
//    private RecyclerView msg_recyclerView;
//    private MsgAdapter adapter;
//    private List<Msg> msgs = new ArrayList<Msg>();//存储消息容器
    private String name = "";
    private static final int UPDATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = getIntent();
//        name = intent.getStringExtra("name");
//        Log.d("孔昊",name);

        //从服务器读数进程
        new Thread(new Runnable() {
            private String msg_get="";
            @Override
            public void run() {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("111.11.85.3", 30001), 5000);

                    //input
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    while((msg_get=br.readLine())!=null){
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        Log.d("孔昊",msg_get);
                        bundle.putString("msg",msg_get);
                        message.setData(bundle);
                        message.what = UPDATE;
//                        handler.sendMessage(message);
                    }

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();

//        edit = (EditText) findViewById(R.id.edit);
//        send = (Button) findViewById(R.id.send);
//        send.setOnClickListener(this);
//        disconnect = (Button) findViewById(R.id.disconnect);
//        disconnect.setOnClickListener(this);
//
//        msg_recyclerView = (RecyclerView) findViewById(R.id.msg_view);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        msg_recyclerView.setLayoutManager(linearLayoutManager);
//        adapter = new MsgAdapter(msgs);
//        msg_recyclerView.setAdapter(adapter);
    }
//
//    Handler handler = new Handler(){
//
//        @Override
//        public void handleMessage(Message msg){
//            super.handleMessage(msg);
//            switch (msg.what){
//                //更新消息列表
//                case UPDATE:{
//                    Bundle bundle = msg.getData();
//                    String msg_get = bundle.getString("msg");
//                    Msg msg1 = new Msg(msg_get);
//                    msgs.add(msg1);
//                    adapter.notifyDataSetChanged();
//                }
//                break;
//            }
//        }
//    };
//
//    //向服务器发数据进程
//    class SocketThread extends Thread{
//        private String msg;
//        //Socket socket;
//
//        public SocketThread(String m){
//            msg = m;
//        }
//
//        @Override
//        public void run(){
//            try {
//
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                bw.write(msg);
//                bw.newLine();
//                bw.flush();
//
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.send:{
//                String s = name +":"+ edit.getText().toString();
//                edit.setText("");
//                new Thread(new SocketThread(s)).start();
//            }
//            break;
//            case R.id.disconnect:{
//                try {
//                    //socket.shutdownOutput();
//                    //socket.shutdownInput();
//                    //socket.close();
//                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                    bw.write("-用户退出-");
//                    bw.newLine();
//                    bw.flush();
//                    //发送message,更新UI
//                    Message message = new Message();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("msg","你已经退出聊天！");
//                    message.setData(bundle);
//                    message.what = UPDATE;
//                    handler.sendMessage(message);
//
//                }catch (IOException e){
//                    //Log.d("孔昊","断开连接");
//                    e.printStackTrace();
//                }
//            }
//            break;
//        }
//    }
//
//    //back事件
//
//    @Override
//    public void onBackPressed(){
//        try {
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            bw.write("-用户退出-");
//            bw.newLine();
//            bw.flush();
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        super.onBackPressed();
//    }
}
