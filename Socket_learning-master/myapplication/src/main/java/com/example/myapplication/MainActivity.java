package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    EditText et1;
    EditText et2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            String s1;
                            String s2;

                            s1 = et1.getText().toString();
                            s2 = et2.getText().toString();

                            Log.e("ip", s1);
                            Log.e("port", s2);

                            Socket client = new Socket(s1, Integer.parseInt(s2));
                            Writer writer = new OutputStreamWriter(client.getOutputStream(), "GBK");
                            writer.write("你好，服务端。");
                            Log.e("writer", "你好，服务端。");
                            writer.flush();
                            //写完以后进行读操作
                            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
                            //设置超时间为10秒
                            client.setSoTimeout(10 * 1000);
                            StringBuffer sb = new StringBuffer();
                            String temp;

                            try {
                                while ((temp = br.readLine()) != null) {

                                    sb.append(temp);
                                }
                            } catch (SocketTimeoutException e) {
                                Log.e("error", "数据读取超时。");
                            }

                            br.close();
                            client.close();
                            tv3.setText(sb);
                            br.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
