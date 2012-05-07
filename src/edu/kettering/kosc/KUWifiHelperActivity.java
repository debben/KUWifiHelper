package edu.kettering.kosc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class KUWifiHelperActivity extends Activity {
    /** Called when the activity is first created. */
	public final static String FILENAME = "hello_file";
	WifiStateDetect detect = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //First things first. Let's verify that our file exists
        
        FileInputStream fis = null;
        try 
        {
			fis = openFileInput(FILENAME);
		} 
        catch (FileNotFoundException e) 
        {
        	e.printStackTrace();
		}
        
        //ok, now if fis has data, we need to get it and fill in our two text boxes.
        final EditText username =  (EditText)findViewById(R.id.username_input);
        final EditText password =  (EditText)findViewById(R.id.password_input);        
        if(fis != null){      
        	String[] data = getCredentials(fis);
        	username.setText(data[0]);
        	password.setText(data[1]);

        }
        
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//here, we must save the data
				FileOutputStream fos = null;
				try
				{
					fos = openFileOutput(FILENAME, MODE_PRIVATE);
					String writeString = username.getText() + ";" + password.getText();
					byte[] bytes = new byte[writeString.length()];
					for(int i = 0; i < bytes.length; i++){
						bytes[i] = (byte) writeString.charAt(i);
					}
					fos.write(bytes);
					fos.close();
				}
				catch(FileNotFoundException ex)
				{
					ex.printStackTrace();
					//oh shit. what do we do now?
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
        try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	static String[] getCredentials(FileInputStream fis) {
    	byte[] inputData = new byte[50];
    	String[] data = null;
    	int length = 0;
		try 
		{
			length = fis.read(inputData, 0, 50);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
    	if(length > 0){
    		String inputString = new String(inputData);
    		data = inputString.split(";");
    	}
    	return data;
	}
}