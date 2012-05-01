package edu.kettering.kosc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class WifiStateDetect extends BroadcastReceiver {

	private final String TAG = "KUWifi";
	@Override
	public void onReceive(Context context, Intent intent) {
		final String action = intent.getAction();
	    if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
	        if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)) {
	            Log.i(TAG,"wifi connected");
	            //we've been connected. Let's do something about it.
	            WifiManager manager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
	            String networkName = manager.getConnectionInfo().getSSID();
	            if(networkName.equals("KUSTUDENT")){
	            	doKUStudentConnection();
	            }
	            else if(networkName.equals("KUW")){
	            	doKUWConnection();
	            }
	            
	            Log.i(TAG,networkName);
	            
	        } else {
	            // wifi connection was lost
	        	Log.i(TAG,"wifi lost");
	        }
	    }
		
		
	}
	private void doKUWConnection() {
		// TODO Auto-generated method stub
		
	}
	private void doKUStudentConnection() {
		// TODO Auto-generated method stub
		
	}

}
