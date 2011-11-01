package com.wordmapper.android;

import android.app.Activity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.os.Bundle;

public class WordMapperActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Spinner cmbDictionary = (Spinner) findViewById(R.id.cmbDictionary);
        
        cmbDictionary.addView(new View(getBaseContext()).)
    }
}