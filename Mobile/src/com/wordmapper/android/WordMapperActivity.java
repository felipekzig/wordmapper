package com.wordmapper.android;

import com.wordmapper.android.actions.DefineAction;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.os.Bundle;

public class WordMapperActivity extends Activity {
    /** Called when the activity is first created. */
	
	public static final String TAG = "DroidWordMapper";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Spinner cmbDictionary = (Spinner) findViewById(R.id.cmbDictionaries);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dictItens, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        cmbDictionary.setAdapter(adapter);
        
        Button btnDefine = (Button) findViewById(R.id.btnDefine);
        DefineAction btnDefineAction = new DefineAction();
        
        btnDefine.setOnClickListener(btnDefineAction);
    }
 
}