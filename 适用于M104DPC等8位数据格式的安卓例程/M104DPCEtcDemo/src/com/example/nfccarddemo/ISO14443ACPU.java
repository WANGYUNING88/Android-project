package com.example.nfccarddemo;

import com.serialport.yzrfidAPI;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ISO14443ACPU extends Activity {
	final int TRUE = 1;
	final int FALSE = 0;
			
	public yzrfidAPI myzrfidAPI=new yzrfidAPI() ; 
	Button btnInitType;
	Button btnReset;
	Button btnCosSend;
	Button btnReturn;
	
	EditText etCosInput1;
	EditText etResult1;
	
	TextView tvInfo;
	TextView tvResult;
	static int nfd=0;
	static int nIsOpenCom=0;
	int nStatus=0;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iso14443_acpu);
		clsGlobalVar globalVar1=((clsGlobalVar)getApplication());
		nfd=globalVar1.getFd();
		nIsOpenCom=globalVar1.getIsComOpen();
		initDlg();   //后面补充内容，先空着
	}
	public void rfInitTypeFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		nStatus = myzrfidAPI.rfAntennaSta(nfd,0, (byte)(0x00));
		if(nStatus==FALSE)
		{
			tvInfo.setText(R.string.antenna_Err);
			return;
		}
		nStatus = myzrfidAPI.rfInitType(nfd, 0, (byte)('A'));
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.initType_Err);
		}
		nStatus = myzrfidAPI.rfAntennaSta(nfd,0, (byte)(0x01));
		if(nStatus==FALSE)
		{
			tvInfo.setText(R.string.antenna_Err);
			return;
		}
		tvInfo.setText(R.string.initType_OK);
	}
	public void rfTypeARstFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		byte[] pData=new byte[100];
		byte[] pMsgLg=new byte[1];
		tvInfo.setText("");
		etResult1.setText("");
		nStatus = myzrfidAPI.rfTypeARst(nfd, 0, (byte)(0x52), pData, pMsgLg);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfReset_OK);
			etResult1.setText(ByteArrayToString(pData,pMsgLg[0]));
		}
		else
		{
			tvInfo.setText(R.string.RfReset_Err);
		}
	}
	public void rfCosCommandFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		byte[] pData=new byte[260];
		byte[] pMsgLg=new byte[1];
		tvInfo.setText("");
		etResult1.setText("");
		byte[] command = StringToByteArray(etCosInput1.getText().toString());
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.rfCosCommand(nfd, 0, command, (byte)(command.length), pData, pMsgLg);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfCosSend_OK);
			etResult1.setText(ByteArrayToString(pData,pMsgLg[0]));
		}
		else
		{
			tvInfo.setText(R.string.RfCosSend_Err);
		}
	}
	public void MainShowDlg() 
	{
		Intent intent = new Intent(ISO14443ACPU.this,MainActivity.class);
		startActivity(intent);
	}
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
		etCosInput1=(EditText)findViewById(R.id.etCosInput);
		etResult1=(EditText)findViewById(R.id.etResult);
	}	
	public void initButton(){
		btnInitType = (Button)findViewById(R.id.btnInitType);
		btnInitType.setOnClickListener(onClick); 
		
		btnReset = (Button)findViewById(R.id.btnReset);
		btnReset.setOnClickListener(onClick); 
        
		btnCosSend = (Button)findViewById(R.id.btnCosSend);
		btnCosSend.setOnClickListener(onClick);   
       
    	btnReturn = (Button)findViewById(R.id.btnReturn);
    	btnReturn.setOnClickListener(onClick); 
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
	OnClickListener onClick=new OnClickListener()
	{
		public void onClick(View v) {
			switch (v.getId()){
			case R.id.btnInitType:
				rfInitTypeFun();
				break;
			case R.id.btnReset:
				rfTypeARstFun();
				break;
			case R.id.btnCosSend:
				rfCosCommandFun();
				break;
			case R.id.btnReturn:
				MainShowDlg();
				break;
			}
		}
		
	};

}
