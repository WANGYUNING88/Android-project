package com.example.nfccarddemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.app.Application;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.serialport.yzrfidAPI;
import android.widget.Button;

public class MainActivity extends Activity {
	
	//声明库函数
	final int TRUE = 1;
	final int FALSE = 0;

			
	public yzrfidAPI myzrfidAPI=new yzrfidAPI() ; 
	
	Button btnOpenCom;
	Button btnCloseCom;
	Button btnBeep;
	Button btnLight;
	Button btnMF1Test;
	Button btnACPUTest;
	Button btnBCPUTest;
	Button btnSAMTest;
	Button btnMSAMTest;
	Button btn15693Test;
	Button btnSRI4KTest;
	Button btnFelicaTest;
	Button btnGetNameCardTest;
	Button btnVer;
	EditText etComNo;
	EditText etBaud;
	
	TextView tvInfo;
	TextView tvResult;
	static int nfd=0;
	static int nIsOpenCom=0;
	int nStatus=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_main);
		 
		initDlg();   //后面补充内容，先空着
		//globalVar.setFd(1);
		//globalVar.setIsComOpen(1);
	}

	public void rfInitComFun()
	{
		clsGlobalVar globalVar1=((clsGlobalVar)getApplication());
		//nfd=myzrfidAPI.rfInitCom(0, "/dev/ttyS1",19200);
		nfd=myzrfidAPI.rfInitCom(0, "/dev/"+etComNo.getText().toString().replaceAll(" ",""), Integer.parseInt(etBaud.getText().toString().replaceAll(" ","")));
		if(nfd>0)
		{	
			globalVar1.setFd(nfd);
			globalVar1.setIsComOpen(1);
			nIsOpenCom=1;
			tvInfo.setText(R.string.open_com_OK);
		}
		else
		{
			globalVar1.setFd(0);
			globalVar1.setIsComOpen(0);
			nIsOpenCom=0;
			tvInfo.setText(R.string.open_com_Err);
		}
	}
	public void rfClosePortFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		nStatus = myzrfidAPI.rfClosePort(nfd,0);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.close_com_OK);
		}
		else
		{
			tvInfo.setText(R.string.close_com_Err);
		}
	}	
	public void rfBeepFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		nStatus = myzrfidAPI.rfBeep(nfd, 0, (byte)(0x44));
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfBeep_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfBeep_Err);
		}
	};
	public void rfLightFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		nStatus = myzrfidAPI.rfLight(nfd, 0,(byte)(0x03));
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfLight_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfLight_Err);
		}
	};
	public void rfLibVerFun() {
		byte[] Ver=new byte[4];
		nStatus = myzrfidAPI.rfLibVer(nfd, 0, Ver);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfGetVer_OK);
			tvResult.setText(ByteArrayToString(Ver,4));
		}
		else
		{
			tvInfo.setText(R.string.RfGetVer_Err);
		}
	};
	public void MF1ShowDlg() {
		Intent intent = new Intent(MainActivity.this,MifareOneActivity.class);
		startActivity(intent);
	};
	public void ACPUShowDlg() {
		Intent intent = new Intent(MainActivity.this,ISO14443ACPU.class);
		startActivity(intent);
	};
	public void SAMShowDlg() {
		Intent intent = new Intent(MainActivity.this,PSAM.class);
		startActivity(intent);
	};
	public void MoreSAMShowDlg() {
		Intent intent = new Intent(MainActivity.this,MorePSAM.class);
		startActivity(intent);
	};
	public void ISO15693ShowDlg() {
		Intent intent = new Intent(MainActivity.this,ISO15693.class);
		startActivity(intent);
	};
	public void FelicaShowDlg() {
		Intent intent = new Intent(MainActivity.this,Felica.class);
		startActivity(intent);
	};
	public void NameCardShowDlg() {
		Intent intent = new Intent(MainActivity.this,NameCard.class);
		startActivity(intent);
	};
	OnClickListener onClick=new OnClickListener()
	{
		public void onClick(View v) {
			switch (v.getId()){
			case R.id.btnOpenCom:
				rfInitComFun();
				break;
			case R.id.btnCloseCom:
				rfClosePortFun();
				break;
			case R.id.btnBeep:
				rfBeepFun();
				break;
			case R.id.btnLEDTest:
				rfLightFun();
				break;
			case R.id.btnMifareOneTest:
				MF1ShowDlg();
				break;
			case R.id.btnTYPEACPUTest:
				ACPUShowDlg();
				break;
			case R.id.btnTYPEBCPUTest:
				
				break;
			case R.id.btnPSAMTest:
				SAMShowDlg();
				break;
			case R.id.btnMorePSAMTest:
				MoreSAMShowDlg();
				break;
			case R.id.btnGetVer:
				rfLibVerFun();
				break;
			case R.id.btn15693Test:
				ISO15693ShowDlg();
				break;
			case R.id.btnFelicaTest:
				FelicaShowDlg();
				break;
			case R.id.btnNameCardTest:
				NameCardShowDlg();
				break;
				/*case R.id.btnOpenCom:
				
				break;
			case R.id.btnOpenCom:
				
				break;
			case R.id.btnOpenCom:
				
				break;*/
			}
		}
		
	};
	public void initDlg(){
		initButton();
		initTextView();
		initEditText();
	}
	public void initTextView(){
		tvInfo = (TextView)findViewById(R.id.TextViewInfo);
		tvResult = (TextView)findViewById(R.id.tvResult);
	}	
	public void initEditText(){
		etComNo=(EditText)findViewById(R.id.etComNo);
		etBaud=(EditText)findViewById(R.id.etBaud);
	}
	public void initButton(){
        btnOpenCom = (Button) findViewById(R.id.btnOpenCom);
        btnOpenCom.setOnClickListener(onClick);
        
        btnCloseCom = (Button) findViewById(R.id.btnCloseCom);
        btnCloseCom.setOnClickListener(onClick);    
        
    	btnBeep = (Button)findViewById(R.id.btnBeep);
    	btnBeep.setOnClickListener(onClick); 
    	
    	btnLight = (Button)findViewById(R.id.btnLEDTest);
    	btnLight.setOnClickListener(onClick);   
    	
    	btnMF1Test = (Button)findViewById(R.id.btnMifareOneTest);
    	btnMF1Test.setOnClickListener(onClick);  
    	
    	btnACPUTest = (Button)findViewById(R.id.btnTYPEACPUTest);
    	btnACPUTest.setOnClickListener(onClick);  
    	
    	btnBCPUTest = (Button)findViewById(R.id.btnTYPEBCPUTest);
    	btnBCPUTest.setOnClickListener(onClick);  
    	
    	btnSAMTest = (Button)findViewById(R.id.btnPSAMTest);
    	btnSAMTest.setOnClickListener(onClick);  
    	
    	btnMSAMTest = (Button)findViewById(R.id.btnMorePSAMTest);
    	btnMSAMTest.setOnClickListener(onClick);
    	
    	btn15693Test = (Button)findViewById(R.id.btn15693Test);
    	btn15693Test.setOnClickListener(onClick);  
    	
    	btnSRI4KTest = (Button)findViewById(R.id.btnSRIX4KTest);
    	btnSRI4KTest.setOnClickListener(onClick);  
    	
    	btnFelicaTest = (Button)findViewById(R.id.btnFelicaTest);
    	btnFelicaTest.setOnClickListener(onClick);  
    	
    	btnGetNameCardTest = (Button)findViewById(R.id.btnNameCardTest);
    	btnGetNameCardTest.setOnClickListener(onClick);  
    	
    	btnVer = (Button)findViewById(R.id.btnGetVer);
    	btnVer.setOnClickListener(onClick);    
	}
/*	OnClickListener onClick=new OnClickListener(){
		public void onClick(View V){
			switch(v.)
			
		}
		
	};*/
	public byte[] StringToByteArray(String str) {
		str=str.replaceAll(" ","");
		int n=str.length()/2;
        String[] str_ary = new String[n];
        for(int i=0;i<n;i++)
        {
        	str_ary[i]=str.substring(i * 2, i * 2 + 2);
        }
        byte[] bt_ary = new byte[n];
        for (int i = 0; i < n; i++)
            bt_ary[i] = (byte)Integer.parseInt(str_ary[i], 16) ;
        return bt_ary;
    }
 
    public String ByteArrayToString(byte[] bt_ary,int len) {
        StringBuilder sb = new StringBuilder();
        int i;
        if (bt_ary != null)
        	if(len<bt_ary.length)
        	{
	        	for(i=0;i<len;i++)
	        	{
	        		sb.append(String.format("%02X ",bt_ary[i]));
	        	}
        	}
        	else
        	{
	            for (byte b : bt_ary) {
	                sb.append(String.format("%02X ", b));
	            }
        	}
        return sb.toString();
    }
 
    public String ByteToString(byte bt) {
        return String.format("%08d",Integer.parseInt(Integer.toBinaryString(bt)));
    }

/*	public byte[] apdustring(String apdu) {
		String[] acs = new String[apdu.length() / 2];
		for (int i = 0; i < apdu.length() / 2; i++) {
			acs[i] = apdu.substring(i * 2, i * 2 + 2);
		}
		byte[] command = new byte[acs.length];
		for (int i = 0; i < acs.length; i++) {
			command[i] = hexStrToByte(acs[i]);
		}
		return command;
	}*/
	
	
	public String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		char[] buffer = new char[2];
		for (int i = 0; i < src.length; i++) {
			buffer[0] = Character.forDigit((src[i] >> 4) & 0x0F, 16);
			buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
			stringBuilder.append(buffer);
		}
		return stringBuilder.toString();
	}	
}
