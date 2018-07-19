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

public class PSAM extends Activity {
	final int TRUE = 1;
	final int FALSE = 0;
			
	public yzrfidAPI myzrfidAPI=new yzrfidAPI() ; 
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
		setContentView(R.layout.activity_psam);
		clsGlobalVar globalVar1=((clsGlobalVar)getApplication());
		nfd=globalVar1.getFd();
		nIsOpenCom=globalVar1.getIsComOpen();
		initDlg();   //后面补充内容，先空着
	}
	public void rfSAMRstFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		byte[] pData=new byte[100];
		byte[] pMsgLg=new byte[1];
		etResult1.setText("");
		nStatus = myzrfidAPI.rfInitSam(nfd, 0, (byte)(0x00));
		if(nStatus==TRUE)
		{
			nStatus = myzrfidAPI.rfSamRst(nfd, 0, pData, pMsgLg);
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
		byte[] command = StringToByteArray(etCosInput1.getText().toString());
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.rfSamCos(nfd, 0, command, (byte)(command.length), pData, pMsgLg);
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
		Intent intent = new Intent(PSAM.this,MainActivity.class);
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
			case R.id.btnReset:
				rfSAMRstFun();
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
