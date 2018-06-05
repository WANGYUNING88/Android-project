package com.example.wang.myapplication;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * Created by wang on 2018/6/5.
 */

public class GetIp extends Thread {
    private ServerSocket serverSocket;
    @Override
    public void run() {
        super.run();
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            serverSocket = new ServerSocket(0);

            Log.e("ip",ip);
            Log.e("port", String.valueOf(serverSocket.getLocalPort()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
