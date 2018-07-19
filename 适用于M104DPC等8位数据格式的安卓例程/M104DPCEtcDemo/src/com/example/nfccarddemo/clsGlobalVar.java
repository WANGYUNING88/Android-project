package com.example.nfccarddemo;
import android.app.Application;

public class clsGlobalVar extends Application{
	private int nfd;
	private int nIsComOpen;
	
    public void setFd(int fd){  
         this.nfd= fd;  
     }  
    public int getFd(){  
        return this.nfd; 
    }  
    
    public int getIsComOpen(){  
        return this.nIsComOpen; 
    }  
    public void setIsComOpen(int isOpen){  
        this.nIsComOpen= isOpen;  
    }  

    @Override  
    public void onCreate(){  
    	nfd = 0;  
    	nIsComOpen=0;
        super.onCreate();  
     }  

	
}
