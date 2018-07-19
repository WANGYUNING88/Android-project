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

public class ISO15693 extends Activity {
	final int TRUE = 1;
	final int FALSE = 0;
			
	public yzrfidAPI myzrfidAPI=new yzrfidAPI() ; 
	Button btnInitType;
	Button btnInventory;
	Button btGetInformation;
	Button bt15693ReadBlock;
	Button bt15693WriteBlock;
	Button bt15693LockBlock;
	Button bt15693GetBlockSec;
	Button bt15693WriteDSFID;
	Button bt15693LockDSFID;
	Button bt15693WriteAFI;
	Button bt15693LcokAFI;
	Button bt15693StayQuiet;
	Button bt15693ResetToReady;
	Button bt15693SetEAS;
	Button bt15693LockEAS;
	Button bt15693ResetEAS;
	Button bt15693EASAlarm;
	Button btnReturn;
	
	EditText et15693DataValue1;

	TextView tvInfo;
	TextView tvResult;
	static int nfd=0;
	static int nIsOpenCom=0;
	int nStatus=0;	
	byte[] UID=new byte[8];
	byte CardType=0x02;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iso15693);
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
		nStatus = myzrfidAPI.rfInitType(nfd, 0, (byte)('1'));
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
	public void ISO15693InventoryFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		int i;
		byte[] pData=new byte[100];
		byte[] pLen=new byte[1];
		tvInfo.setText("");
		tvResult.setText("");
		nStatus = myzrfidAPI.ISO15693Inventory(nfd, 0, pData, pLen);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfInventory_OK);
			for(i=0;i<8;i++)
			{
				UID[i]=pData[i+1];
			}
			tvResult.setText(ByteArrayToString(pData,pLen[0]));
		}
		else
		{
			tvInfo.setText(R.string.RfInventory_Err);
		}
	}
	public void ISO15693GetSystemInformationFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		byte[] pDataReturn=new byte[100];
		byte[] pUID=new byte[8];
		byte[] pLen=new byte[1];
		tvInfo.setText("");
		tvResult.setText("");
		nStatus = myzrfidAPI.ISO15693GetSystemInformation(nfd, 0,(byte)(0), pUID, pDataReturn, pLen);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfGetInformation_OK);
			tvResult.setText(ByteArrayToString(pDataReturn,pLen[0]));
		}
		else
		{
			tvInfo.setText(R.string.RfGetInformation_Err);
		}
	}
	public void ISO15693StayQuietFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		nStatus = myzrfidAPI.ISO15693StayQuiet(nfd, 0, UID);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfStayQuiet_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfStayQuiet_Err);
		}
	}
	public void ISO15693ResetToReadyFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		nStatus = myzrfidAPI.ISO15693ResetToReady(nfd, 0, CardType, UID);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfResetToReady_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfResetToReady_Err);
		}
	}
	public void ISO15693ReadFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		byte[] pDataReturn=new byte[100];
		byte[] pLen=new byte[1];
		tvInfo.setText("");
		tvResult.setText("");
		nStatus = myzrfidAPI.ISO15693Read(nfd, 0, CardType, UID, (byte)(0x07), (byte)(0x08), pDataReturn, pLen);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.Rf15693ReadBlock_OK);
			tvResult.setText(ByteArrayToString(pDataReturn,pLen[0]));
		}
		else
		{
			tvInfo.setText(R.string.Rf15693ReadBlock_Err);
		}
	}
	public void ISO15693GetBlockSecurityFun() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		byte[] pDataReturn=new byte[100];
		byte[] pLen=new byte[1];
		tvInfo.setText("");
		tvResult.setText("");
		nStatus = myzrfidAPI.ISO15693GetBlockSecurity(nfd, 0, CardType, UID, (byte)(0x00), (byte)(0x40), pDataReturn, pLen);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.Rf15693ReadBlock_OK);
			tvResult.setText(ByteArrayToString(pDataReturn,pLen[0]));
		}
		else
		{
			tvInfo.setText(R.string.Rf15693ReadBlock_Err);
		}
	}
	public void ISO15693WriteFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte[] data = StringToByteArray(et15693DataValue1.getText().toString());
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.ISO15693Write(nfd, 0, CardType, UID, (byte)(0x07), data);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.Rf15693WriteBlock_OK);
		}
		else
		{
			tvInfo.setText(R.string.Rf15693WriteBlock_Err);
		}
	}
	public void ISO15693LockBlockFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.ISO15693LockBlock(nfd, 0, CardType, UID,(byte)(0x07));
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.Rf15693LockBlock_OK);
		}
		else
		{
			tvInfo.setText(R.string.Rf15693LockBlock_Err);
		}
	}
	public void ISO15693WriteDSFIDFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte DSFID=0x00;
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.ISO15693WriteDSFID(nfd, 0, CardType, UID, DSFID);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfWriteDSFID_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfWriteDSFID_Err);
		}
	}
	public void ISO15693LockDSFIDFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte DSFID=0x00;
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.ISO15693LockDSFID(nfd, 0, CardType, UID);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfLockDSFID_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfLockDSFID_Err);
		}
	}
	public void ISO15693WriteAFIFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte AFI=0x00;
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.ISO15693WriteAFI(nfd, 0, CardType, UID, AFI);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfWriteAFI_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfWriteAFI_Err);
		}
	}
	public void ISO15693LockAFIFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.ISO15693LockAFI(nfd, 0, CardType, UID);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfLcokAFI_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfLcokAFI_Err);
		}
	}
	public void ISO15693SetEasIcodeFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		byte DSFID=0x00;
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.ISO15693SetEasIcode(nfd, 0, CardType, UID);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfSetEAS_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfSetEAS_Err);
		}
	}
	public void ISO15693ResetEasIcodeFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.ISO15693ResetEasIcode(nfd, 0, CardType, UID);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfResetEAS_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfResetEAS_Err);
		}
	}
	public void ISO15693LockEasIcodeFun() {
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		tvInfo.setText("");
		tvResult.setText("");
		//此处demo用的A密钥，块4	
		nStatus = myzrfidAPI.ISO15693LockEasIcode(nfd, 0, CardType, UID);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfLockEAS_OK);
		}
		else
		{
			tvInfo.setText(R.string.RfLockEAS_Err);
		}
	}
	public void ISO15693EasAlarmIcode() 
	{
		if(nIsOpenCom==0)
		{
			tvInfo.setText(R.string.btIsComOpen);
			return;
		}
		byte[] pDataReturn=new byte[100];
		byte[] pLen=new byte[1];
		tvInfo.setText("");
		tvResult.setText("");
		nStatus = myzrfidAPI.ISO15693EasAlarmIcode(nfd, 0, CardType, UID, pDataReturn, pLen);
		if(nStatus==TRUE)
		{
			tvInfo.setText(R.string.RfEASAlarm_OK);
			tvResult.setText(ByteArrayToString(pDataReturn,pLen[0]));
		}
		else
		{
			tvInfo.setText(R.string.RfEASAlarm_Err);
		}
	}
	public void MainShowDlg() 
	{
		Intent intent = new Intent(ISO15693.this,MainActivity.class);
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
		et15693DataValue1=(EditText)findViewById(R.id.etDataInput);
	}	
	public void initButton(){
		btnInitType = (Button)findViewById(R.id.btnInitType);
		btnInitType.setOnClickListener(onClick); 

		btnInventory = (Button)findViewById(R.id.btnInventory);
		btnInventory.setOnClickListener(onClick); 
        
		btGetInformation = (Button)findViewById(R.id.btnGetInformation);
		btGetInformation.setOnClickListener(onClick);  
		
		bt15693WriteBlock = (Button)findViewById(R.id.btn15693ReadBlock);
		bt15693WriteBlock.setOnClickListener(onClick); 	
		
		bt15693WriteBlock = (Button)findViewById(R.id.btn15693WriteBlock);
		bt15693WriteBlock.setOnClickListener(onClick); 
        
		bt15693LockBlock = (Button)findViewById(R.id.btn15693LockBlock);
		bt15693LockBlock.setOnClickListener(onClick); 
		
		bt15693GetBlockSec = (Button)findViewById(R.id.btn15693GetBlockSec);
		bt15693GetBlockSec.setOnClickListener(onClick); 		
		
		bt15693WriteDSFID = (Button)findViewById(R.id.btn15693WriteDSFID);
		bt15693WriteDSFID.setOnClickListener(onClick);   
		
		bt15693LockDSFID = (Button)findViewById(R.id.btn15693LockDSFID);
		bt15693LockDSFID.setOnClickListener(onClick); 
        
		bt15693WriteAFI = (Button)findViewById(R.id.btn15693WriteAFI);
		bt15693WriteAFI.setOnClickListener(onClick);   
		
		bt15693LcokAFI = (Button)findViewById(R.id.btn15693LcokAFI);
		bt15693LcokAFI.setOnClickListener(onClick); 
        
		bt15693StayQuiet = (Button)findViewById(R.id.btn15693StayQuiet);
		bt15693StayQuiet.setOnClickListener(onClick);   
		
		bt15693ResetToReady = (Button)findViewById(R.id.btn15693ResetToReady);
		bt15693ResetToReady.setOnClickListener(onClick); 
        
		bt15693SetEAS = (Button)findViewById(R.id.btn15693SetEAS);
		bt15693SetEAS.setOnClickListener(onClick);   
		
		bt15693LockEAS = (Button)findViewById(R.id.btn15693LockEAS);
		bt15693LockEAS.setOnClickListener(onClick); 
        
		bt15693ResetEAS = (Button)findViewById(R.id.btn15693ResetEAS);
		bt15693ResetEAS.setOnClickListener(onClick);   
		
		bt15693EASAlarm = (Button)findViewById(R.id.btn15693EASAlarm);
		bt15693EASAlarm.setOnClickListener(onClick); 
        
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
			case R.id.btnInventory:
				ISO15693InventoryFun();
				break;
			case R.id.btnGetInformation:
				ISO15693GetSystemInformationFun();
				break;
			case R.id.btn15693ReadBlock:
				ISO15693ReadFun();
				break;
			case R.id.btn15693WriteBlock:
				ISO15693WriteFun();
				break;
			case R.id.btn15693LockBlock:
				ISO15693LockBlockFun();
				break;	
			case R.id.btn15693GetBlockSec:
				ISO15693GetBlockSecurityFun();
				break;					
			case R.id.btn15693WriteDSFID:
				ISO15693WriteDSFIDFun();
				break;
			case R.id.btn15693LockDSFID:
				ISO15693LockDSFIDFun();
				break;
			case R.id.btn15693WriteAFI:
				ISO15693WriteAFIFun();
				break;
			case R.id.btn15693LcokAFI:
				ISO15693LockAFIFun();
				break;
			case R.id.btn15693StayQuiet:
				ISO15693StayQuietFun();
				break;
			case R.id.btn15693ResetToReady:
				ISO15693ResetToReadyFun();
				break;
			case R.id.btn15693SetEAS:
				ISO15693SetEasIcodeFun();
				break;
			case R.id.btn15693LockEAS:
				ISO15693LockEasIcodeFun();
				break;
			case R.id.btn15693ResetEAS:
				ISO15693ResetEasIcodeFun();
				break;
			case R.id.btn15693EASAlarm:
				ISO15693EasAlarmIcode();
				break;
			case R.id.btnReturn:
				MainShowDlg();
				break;
			}
		}
		
	};
}
