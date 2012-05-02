package edu.kettering.kosc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
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
	            	doKUWConnection(context);
	            }
	            
	            Log.i(TAG,networkName);
	            
	        } else {
	            // wifi connection was lost
	        	Log.i(TAG,"wifi lost");
	        }
	    }
		
		
	}
	private void doKUWConnection(Context c) {
		//first, let's put up a toast message for the user:
		Context context = c;
		CharSequence text = "Attempting to send user credentials on the wifi network";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(c, "attempting login on KUW", Toast.LENGTH_LONG);
		toast.show();				
		
		
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("https://apcontrol.kettering.edu:443/logon");

	    try {
	    	FileInputStream fis = new FileInputStream(KUWifiHelperActivity.FILENAME);
	    	String[] credentials = KUWifiHelperActivity.getCredentials(fis);
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("query_string", "http://www.google.com/"));
	        nameValuePairs.add(new BasicNameValuePair("javaworks", "1"));
	        nameValuePairs.add(new BasicNameValuePair("vernier_id", "VernierNetworks"));
	        nameValuePairs.add(new BasicNameValuePair("product_id", "VNSS"));
	        nameValuePairs.add(new BasicNameValuePair("releast_id", "1.0"));
	        nameValuePairs.add(new BasicNameValuePair("logon_status", "0"));
	        nameValuePairs.add(new BasicNameValuePair("guest_allowed", "0"));
	        nameValuePairs.add(new BasicNameValuePair("realm_required", "0"));
	        nameValuePairs.add(new BasicNameValuePair("secret", "ae9c243d04978e9a773d36eaa03e63c0127b9550f0658fe5b22a84cd8b162ecea51ddf41fc5fdc633309328e0f3e34e8"));
	        nameValuePairs.add(new BasicNameValuePair("verify_vernier", "e6a7ff651ba106cf82e3dab61e77bd06"));
	        nameValuePairs.add(new BasicNameValuePair("username", credentials[0]));
	        nameValuePairs.add(new BasicNameValuePair("password", credentials[1]));
	        
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
		
	}
	private void doKUStudentConnection() {
		// TODO Auto-generated method stub
		
	}

}
