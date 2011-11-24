package br.com.wordmapper.android.activities;

import br.com.wordmapper.android.actions.DefineActions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class DefineActivity extends Activity {
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.define);
	        
	        DefineActions actions = new DefineActions(this);
	        
	        final Button btnDefine = (Button) findViewById(R.id.btnDefine);
	        btnDefine.setOnClickListener(actions);
	        
	        final Button btnReset = (Button) findViewById(R.id.btnReset);
	        btnReset.setOnClickListener(actions);	   
	        
	        final Spinner cmbDictionaries = (Spinner) findViewById(R.id.cmbDictionaries);
	        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dictItens, android.R.layout.simple_spinner_dropdown_item);
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        
	        cmbDictionaries.setAdapter(adapter);	        
	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.menu, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch(item.getItemId()){
	    		case R.id.Home:
	    			finish();
	    		break;
	    		
	    		case R.id.Define:
	    			// Does Nothing
	    		break;
	    		
	    		case R.id.Settings:
	    			this.showSettingsActivity();
	    		break;
	    	}
	    	
	    	return true;
	    }    
	    

	    
	    private void showSettingsActivity(){
	    	Intent intent = new Intent(this, SettingsActivity.class);
	    	startActivity(intent);
	    }
	      
}
