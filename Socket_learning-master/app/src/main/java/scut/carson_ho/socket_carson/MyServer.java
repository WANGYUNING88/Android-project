package scut.carson_ho.socket_carson;

import java.io.*;  
import java.net.*;  
  
public class MyServer {  
    public static void main(String[] args) throws IOException {  
        ServerSocket ss = new ServerSocket(8989);  
        while(true) {  
            Socket s = ss.accept();  
            PrintStream ps = new PrintStream(s.getOutputStream());  
            ps.println("连接服务器成功！");
            ps.close();  
            s.close();  
        }  
    }  
}  