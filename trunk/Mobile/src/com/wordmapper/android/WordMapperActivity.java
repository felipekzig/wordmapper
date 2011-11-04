package com.wordmapper.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;

public class WordMapperActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	protected static final String TAG = "DroidWordMapper";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Spinner cmbDictionary = (Spinner) findViewById(R.id.cmbDictionaries);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dictItens, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        cmbDictionary.setAdapter(adapter);
        
        Button btnDefine = (Button) findViewById(R.id.btnDefine);
        btnDefine.setOnClickListener(this);
    }
   
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String json = "";
		
		EditText txtWord = (EditText) findViewById(R.id.txtWord2Define);
		TextView lblWordDefinition = (TextView) findViewById(R.id.lblWordDefinition);
		
		lblWordDefinition.setText(txtWord.getText());
		
		/*
		
		
		//json = "{\"Word\":\"" + txtWord.getText().toString() + "\"," + "\"IdTpOperation\":1}";
		json = "%7B\"Word\":\"need\",\"IdTpOperation\":1%7D"; 
		
		String urlWS = "http://localhost:8080/WordMapperService/resources/WordMapper/";
		urlWS = urlWS + json;
		
		Log.d(TAG, json);
		Log.d(TAG, urlWS);
				
		
		try {
			lblWordDefinition.setText(this.getRESTFileContent(urlWS).read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("DroidWordMapper", "Falha ao acessar Web service", e);
		}
		*/
			
	}
	
	public InputStream getRESTFileContent(String url) {  
		HttpClient httpclient = new DefaultHttpClient();  
		HttpGet httpget = new HttpGet(url);  
		  
		try {  
			
			HttpResponse response =   
		    httpclient.execute(httpget);  
		  
		    HttpEntity entity = response.getEntity();  
		  
		    if (entity != null) {  
		    	InputStream instream = entity.getContent();
		      
		    	return instream;
		    }  
		} catch (Exception e) {  
			Log.e("DroidWordMapper", "Falha ao acessar Web service", e);  
		}  
		
		return null;
	}
}