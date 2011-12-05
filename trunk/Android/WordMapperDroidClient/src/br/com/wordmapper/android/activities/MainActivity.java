package br.com.wordmapper.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import br.com.wordmapper.android.utils.AppSettings;

public class MainActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        
        AppSettings.loadSettings(this.getApplicationContext());
 
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
    		case R.id.Define:
    			Log.d(AppSettings.TAG, "Define Activity");
    			this.showDefineActivity();
    		break;
    		
    		case R.id.Mapper:
    			Log.d(AppSettings.TAG, "Mapper Activity");
    			this.showMapperActivity();
    		break;
    		
    		case R.id.Settings:
    			this.showSettingsActivity();
    		break;
    	}
    	
    	return true;
    }    
    
    private void showDefineActivity(){
    	Intent intent = new Intent(this, DefineActivity.class);
    	startActivity(intent);
    }
    
    private void showSettingsActivity(){
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
    }
    
    private void showMapperActivity(){
    	Intent intent = new Intent(this, MapperActivity.class);
    	startActivity(intent);
    }
}