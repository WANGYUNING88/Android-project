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

public class MorePSAM extends Activity {
	final int TRUE = 1;
	final int FALSE = 0;
			
	public yzrfidAPI myzrfidAPI=new yzrfidAPI() ; 
	Button btnReset;
	Button btnCosSend;
	Button btnReturn;
	
	EditText etPsamNo1;
	EditText etPsamBaud1;
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
		setContentView(R.layout.activity_more_psam);
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
		byte[] pData=new byte[256];
		byte[] pMsgLg=new byte[1];
		etResult1.setText("");
		byte[] pMode=new byte[10];
		byte intPsamNo = (byte)Integer.parseInt(etPsamNo1.getText().toString().replaceAll(" ",""));
		int intPsamBaud = Integer.parseInt(etPsamNo1.getText().toString().replaceAll(" ",""));
		switch(intPsamBaud)
		{
			case 9600:
				pMode[0]=0x00;
				break;
			case 38400:
				pMode[0]=0x01;
				break;
			case 115200:
				pMode[0]=0x02;
				break;
			default:
				pMode[0]=0x00;
				break;
		}
		if((intPsamNo>16)||(intPsamNo<=16))
		{
			tvInfo.setText(R.string.RfInputPara_Err);
		}
		pMode[0] += (intPsamNo-1)*16;    //cmode高4位为卡号,从0开始，即1号卡对应0，低2位为波特率
		nStatus = myzrfidAPI.rfSamRst2(nfd, 0, pMode,(byte)0x01,pData,pMsgLg);
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
		byte[] command = StringToByteArray(etCosInput1.getText().toString());
		//卡号,从0开始，即1号卡对应0，
		byte intPsamNo = (byte)Integer.parseInt(etPsamNo1.getText().toString().replaceAll(" ",""));
		nStatus = myzrfidAPI.rfSamCos2(nfd, 0, (byte)(intPsamNo-1),command, (byte)(command.length), pData, pMsgLg);
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
		Intent intent = new Intent(MorePSAM.this,MainActivity.class);
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
		etPsamNo1=(EditText)findViewById(R.id.etPsamNo);
		etPsamBaud1=(EditText)findViewById(R.id.etPsamBaud);
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
