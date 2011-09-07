package com.liuapps.logclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import com.liuapps.logserver.ILogService;
import com.liuapps.logserver.Message;


public class LogClientActivity extends Activity implements OnClickListener{
	private final String TAG = LogClientActivity.class.getSimpleName();
	ILogService logService;
	LogConnection logConnection;
	Button button;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		logConnection = new LogConnection();
		Intent intent = new Intent("com.liuapps.logserver.ILogService");
		intent.putExtra("version", "1.0");
		bindService(intent, logConnection, Context.BIND_AUTO_CREATE);
		Log.d(TAG, "bound Service");
		
		button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(logConnection); 
	}


	private class LogConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder iBinder) {
			logService = ILogService.Stub.asInterface(iBinder);
			Log.d(TAG, "onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			logService = null;
			Log.d(TAG, "onServiceDisconnected");

		}

	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if ( logService != null ){
			Message msg = new Message(Parcel.obtain()); // 
			msg.setTag("LogClient");
			msg.setText("Hello from inClick() version 1.1");
			try {
				logService.log(msg);
				logService.you_log("this is from logserver");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, "Failed to send through logService", e);
			} // 
		}
	}


}