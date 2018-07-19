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

public class MifareOneActivity extends Activity {
	//声明库函数
		final int TRUE = 1;
		final int FALSE = 0;
				
		public yzrfidAPI myzrfidAPI=new yzrfidAPI() ; 

		Button btnInitType;
		Button btnReQuest;
		Button btnAnticoll;
		Button btnHalt;	
		Button btnAuth;
		Button btnReadBlock;
		Button btnWriteBlock;
		Button btnInitValue;
		Button btnIncValue;
		Button btnDecValue;
		Button btnReadValue;
		Button btnReturn;
		
		EditText etKeyInput;
		EditText etDataInput;
		EditText etValueInput;
		
		TextView tvSnr;
		TextView tvInfo;
		TextView tvResult;
		static int nfd=0;
		static int nIsOpenCom=0;
		int nStatus=0;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mifare_one);
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
		tvSnr.setText("");
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
	public void rfRequestFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvSnr.setText("");
		tvInfo.setText("");
		tvResult.setText("");
		byte[] TagType=new byte[2];
		nStatus = myzrfidAPI.rfRequest(nfd,0, (byte)(0x26), TagType);
		if(nStatus==FALSE)
		{
			tvInfo.setText(R.string.RfQuest_Err);
			return;
		}
		byte[] pSnr=new byte[10];
		byte[] pRLength=new byte[1];
		byte[] Size=new byte[1];
		nStatus = myzrfidAPI.rfAnticoll(nfd, 0, (byte)(4), pSnr, pRLength);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfAnticoll_OK);
			//tvResult.setText(ByteArrayToString(pSnr,(int)(pRLength[0])));
			
			nStatus=myzrfidAPI.rfSelect(nfd, 0, pSnr, (byte)(4), Size);
			if(nStatus==TRUE)
			{
				tvInfo.setText(R.string.RfSelect_OK);
				tvSnr.setText(ByteArrayToString(pSnr,(int)(pRLength[0])));
			}
			else
			{
				tvInfo.setText(R.string.RfSelect_Err);
			}
		}
		else
		{
			tvInfo.setText(R.string.RfAnticoll_Err);
		}	
	}
	public void rfHaltFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		nStatus = myzrfidAPI.rfHalt(nfd, 0);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfHalt_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfHalt_Err);
		}
	}
	public void rfM1Authentication2Fun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		byte[] KeyInput = StringToByteArray(etKeyInput.getText().toString());
		if(KeyInput.length !=6)
		{
			tvInfo.setText(R.string.RfKeyLen_Err);
			return;
		}
			
		//此处demo用的A密钥，块4
		nStatus = myzrfidAPI.rfM1Authentication2(nfd, 0, (byte)(0x60), (byte)(0x04), KeyInput);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfAuth_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfAuth_Err);
		}
	}
	public void rfM1readFun() 
	{
		byte[] pData=new byte[16];
		byte[] pLen=new byte[1];
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		
		//此处demo用的A密钥，块4
		nStatus = myzrfidAPI.rfM1read(nfd, 0, (byte)(0x04), pData, pLen);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfReadBlock_OK);
			tvResult.setText(ByteArrayToString(pData,(int)(pLen[0])));
		}
		else
		{
			tvInfo.setText(R.string.RfReadBlock_Err);
		}
	}
	public void rfM1WriteFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte[] DataInput = StringToByteArray(etDataInput.getText().toString());
		if(DataInput.length !=16)
		{
			tvInfo.setText(R.string.RfDataLen_Err);
			return;
		}
		//此处demo用的A密钥，块4
		nStatus = myzrfidAPI.rfM1Write(nfd, 0, (byte)(0x04), DataInput);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfWriteBlock_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfWriteBlock_Err);
		}
	}
	public void rfM1InitValFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte[] DataValue = StringToByteArray(etValueInput.getText().toString());
		if(DataValue.length !=4)
		{
			tvInfo.setText(R.string.RfDataLen_Err);
			return;
		}
		//此处demo用的A密钥，块5
		nStatus = myzrfidAPI.rfM1InitVal(nfd, 0, (byte)(0x05), DataValue);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfInitValue_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfInitValue_Err);
		}
	}
	public void rfM1IncrementFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte[] DataValue = StringToByteArray(etValueInput.getText().toString());
		if(DataValue.length !=4)
		{
			tvInfo.setText(R.string.RfDataLen_Err);
			return;
		}
		//此处demo用的A密钥，块5
		nStatus = myzrfidAPI.rfM1Increment(nfd, 0, (byte)(0x05), DataValue);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfIncValue_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfIncValue_Err);
		}
	}
	public void rfM1DecrementFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte[] DataValue = StringToByteArray(etValueInput.getText().toString());
		if(DataValue.length !=4)
		{
			tvInfo.setText(R.string.RfDataLen_Err);
			return;
		}
		//此处demo用的A密钥，块5
		nStatus = myzrfidAPI.rfM1Decrement(nfd, 0, (byte)(0x05), DataValue);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfDecValue_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfDecValue_Err);
		}
	}
	public void rfM1ReadValFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte[] pValue=new byte[4];
		//此处demo用的A密钥，块5
		nStatus = myzrfidAPI.rfM1ReadVal(nfd, 0, (byte)(0x05), pValue);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfReadValue_OK);
			tvResult.setText(ByteArrayToString(pValue,4));
		}
		else
		{
			tvInfo.setText(R.string.RfReadValue_Err);
		}
	}
	public void rfM1RestoreFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte[] pValue=new byte[4];
		//此处demo用的A密钥，块5
		nStatus = myzrfidAPI.rfM1Restore(nfd, 0, (byte)(0x05));
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfRestore_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfRestore_Err);
		}
	}
	public void rfM1TransferFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte[] pValue=new byte[4];
		//此处demo用的A密钥，块5
		nStatus = myzrfidAPI.rfM1Transfer(nfd, 0, (byte)(0x06));
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfTransfer_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfTransfer_Err);
		}
	}
	public void MainShowDlg() 
	{
		Intent intent = new Intent(MifareOneActivity.this,MainActivity.class);
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
		tvSnr = (TextView)findViewById(R.id.tvSNR);	
	}	
	public void initEditText(){
		etKeyInput=(EditText)findViewById(R.id.etKeyInput);
		etDataInput=(EditText)findViewById(R.id.etBlockData);
		etValueInput=(EditText)findViewById(R.id.etValueData);
	}	
	public void initButton(){

		btnInitType = (Button)findViewById(R.id.btnInitType);
		btnInitType.setOnClickListener(onClick); 
		
        btnReQuest = (Button)findViewById(R.id.btnRequest);
        btnReQuest.setOnClickListener(onClick); 
        
        btnHalt = (Button)findViewById(R.id.btnHalt);
        btnHalt.setOnClickListener(onClick);   
        
    	btnAuth = (Button)findViewById(R.id.btnAuth);
    	btnAuth.setOnClickListener(onClick);   
        
    	btnReadBlock = (Button)findViewById(R.id.btnReadBlock);
    	btnReadBlock.setOnClickListener(onClick);   
        
    	btnWriteBlock = (Button)findViewById(R.id.btnWriteBlock);
    	btnWriteBlock.setOnClickListener(onClick);   
        
    	btnInitValue = (Button)findViewById(R.id.btnInitValue);
    	btnInitValue.setOnClickListener(onClick);   
        
    	btnIncValue = (Button)findViewById(R.id.btnIncValue);
    	btnIncValue.setOnClickListener(onClick);   
        
    	btnDecValue = (Button)findViewById(R.id.btnDecValue);
    	btnDecValue.setOnClickListener(onClick);   
    	
    	btnReadValue = (Button)findViewById(R.id.btnReadValue);
    	btnReadValue.setOnClickListener(onClick);   
  
    	btnReadValue = (Button)findViewById(R.id.btnRestore);
    	btnReadValue.setOnClickListener(onClick);   
    	
    	btnReadValue = (Button)findViewById(R.id.btnTransfer);
    	btnReadValue.setOnClickListener(onClick);   
    	
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
			case R.id.btnRequest:
				rfRequestFun();
				break;
			case R.id.btnHalt:
				rfHaltFun();
				break;
			case R.id.btnAuth:
				rfM1Authentication2Fun();
				break;
			case R.id.btnReadBlock:
				rfM1readFun();
				break;
			case R.id.btnWriteBlock:
				rfM1WriteFun();
				break;
			case R.id.btnInitValue:
				rfM1InitValFun();
				break;
			case R.id.btnIncValue:
				rfM1IncrementFun();
				break;
			case R.id.btnDecValue:
				rfM1DecrementFun();
				break;
			case R.id.btnReadValue:
				rfM1ReadValFun();
				break;
			case R.id.btnRestore:
				rfM1RestoreFun();
				break;
			case R.id.btnTransfer:
				rfM1TransferFun();
				break;
			case R.id.btnReturn:
				MainShowDlg();
				break;
			}
		}
		
	};


}
