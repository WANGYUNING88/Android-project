package com.example.newchat;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2018/6/13.
 */

public class Content {
    public static  ListView msgListView;
    public static List<Msg> msgList = new ArrayList<Msg>();
    public static MsgAdapter adapter;
}
