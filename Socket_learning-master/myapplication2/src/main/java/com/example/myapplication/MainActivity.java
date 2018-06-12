package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread()
        {
            public void run()
            {
                try
                {
                    //建立连接到远程服务器的Socket
                    //服务器ip要么是公网ip 要么是和你在一个局域网下的服务器的局域网ip地址
                    Socket socket = new Socket("192.168.23.1",8899);
                            //将Socket对应的输入流包装为BufferedReader
                            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                        String content = br.readLine();
                        //这里注意，如果你想把你收到的数据，显示到界面上，在这里不能直接操作的
                        //因为Android 子线程中是不能更新ui的，需要在主线程中建立Handler，然后向handler发送message才可以
                    Log.e("收到服务器消息",content);
                    br.close();
                    socket.close();
                    } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            }.start();

        }
}
