package com.liuapps.logserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class LogService extends Service {
	private final String TAG = LogService.class.getSimpleName();
	
	@Override
	public IBinder onBind(Intent intent) {
		final String version = intent.getExtras().getString("version");

		return new ILogService.Stub() {
			
			public void log_d(String tag, String message) throws RemoteException { // 
		        Log.d(tag, message + " version: " + version);
		      }

			@Override
			public void log(Message msg) throws RemoteException {
				// TODO Auto-generated method stub
				Log.d(msg.getTag(), msg.getText());
				
			}
			
			public void you_log(String log) throws RemoteException {
				Log.d(TAG, log);
			}
		};
	}

}
