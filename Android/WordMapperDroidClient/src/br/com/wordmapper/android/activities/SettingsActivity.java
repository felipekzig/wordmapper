package br.com.wordmapper.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SettingsActivity extends Activity {
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);        
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
    			this.showDefineActivity();
    		break;

    		case R.id.Settings:
    			// Does Nothing
    		break;
    	}
    	
    	return true;
    }    
    
    private void showDefineActivity(){
    	Intent intent = new Intent(this, DefineActivity.class);
    	startActivity(intent);
    }
    
}
