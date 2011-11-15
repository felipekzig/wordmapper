package br.com.wordmapper.android.activities;

import br.com.wordmapper.android.actions.DefineActions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class DefineActivity extends Activity {
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.define);
	        
	        final Button btnDefine = (Button) findViewById(R.id.btnDefine);
	        btnDefine.setOnClickListener(new DefineActions());
	        
	        final Button btnReset = (Button) findViewById(R.id.btnReset);
	        btnReset.setOnClickListener(new DefineActions());	        
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
