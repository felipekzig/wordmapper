package br.com.wordmapper.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import br.com.wordmapper.android.actions.SettingsActions;
import br.com.wordmapper.android.utils.AppSettings;

public class SettingsActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);    
        
        SettingsActions actions = new SettingsActions(this);

        final Button btnSingUp = (Button) findViewById(R.id.btnSingUp);
        btnSingUp.setOnClickListener(actions);
        if (!AppSettings.userLicense.equals("")) btnSingUp.setEnabled(false);

        final Button btnApplySettings = (Button) findViewById(R.id.btnApplySettings);
        btnApplySettings.setOnClickListener(actions);        
        
        final Button btnCancel = (Button) findViewById(R.id.btnCancelSettings);
        btnCancel.setOnClickListener(actions);
        
        final Spinner cmbDefaultDict = (Spinner) findViewById(R.id.cmbDefaultDict);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dictItens, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        cmbDefaultDict.setAdapter(adapter);       
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
    	finish();
    }
    
}
