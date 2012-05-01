package edu.kettering.kosc;

import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

public class KUWifiHelperActivity extends Activity {
    /** Called when the activity is first created. */
	private final String FILENAME = "hello_file";
	WifiStateDetect detect = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //FileInputStream fis = openFileinput(FILENAME, Context.MODE_PRIVATE);
        
    }
}