package br.com.wordmapper.android.activities;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import br.com.wordmapper.android.actions.DefineActions;

public class DefineActivity extends Activity implements OnInitListener {
	
	public static final Integer RequestCode = 1;
	
	public static final String defineWordKey = "DEFINE_WORD";
	
	public TextToSpeech speech;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.define);

	    DefineActions actions = new DefineActions(this);
	        
	    final Button btnDefine = (Button) findViewById(R.id.btnDefine);
        btnDefine.setOnClickListener(actions);
        
        final Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(actions);
        
        final EditText txtWord2Define = (EditText) findViewById(R.id.txtWord2Define);
        txtWord2Define.setOnFocusChangeListener(actions);
        
        final ImageButton btnPlaySound = (ImageButton) findViewById(R.id.btnPlaySound);
        btnPlaySound.setOnClickListener(actions);
        btnPlaySound.setEnabled(false);
        
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.lstDefinitions);
        listView.setOnChildClickListener(actions);     
      
        this.checkTTSAvailable();
        
        Bundle b = getIntent().getExtras();
        if (b != null){
	        final String wordToDefine = b.getString(defineWordKey);
	        if (wordToDefine != null){
	        	txtWord2Define.setText(wordToDefine);
	        	actions.onClick(btnDefine);
	        }
        }
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
    
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent i){
		if (requestCode == RequestCode){
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
				speech = new TextToSpeech(this, this);
			} else {
				Intent installLanguage = new Intent();
				installLanguage.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installLanguage);
			}
		}
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		speech.shutdown();
	}
		
	public void onInit(int status) {
		speech.setLanguage(Locale.US);
	}
	
	private void checkTTSAvailable(){
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, RequestCode);		
	}	    
	      
}
