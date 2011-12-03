package br.com.wordmapper.android.activities;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import br.com.wordmapper.android.actions.DefinitionsActions;

public class DefinitionsActivity extends Activity implements OnInitListener {
	
	public static final Integer RequestCode = 1;
	public static final String WordKey = "WORD";
	public static final String DictionariesKey = "DICTIONARIES";
	public static final String DefinitionsKey = "DEFINITIONS";
	
	public TextToSpeech speech;
   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.definitions);
		Bundle b = getIntent().getExtras();
		
		DefinitionsActions actions = new DefinitionsActions(this);
		
		final ArrayList<CharSequence> lstDictionaries = b.getCharSequenceArrayList(DictionariesKey);
		final ArrayList<CharSequence> lstDefinitionsString = b.getCharSequenceArrayList(DefinitionsKey);
		final String word = b.getString(WordKey);
		
		@SuppressWarnings("unchecked")
		final ArrayList<CharSequence> lstCompressDef = (ArrayList<CharSequence>) lstDefinitionsString.clone();
		for (int i = 0; i < lstCompressDef.size(); i++) {
			lstCompressDef.set(i, lstCompressDef.get(i).subSequence(0, 20));
		}
		
		final TextView wordLabel = (TextView) findViewById(R.id.lblWordDefined);
		wordLabel.setText(word.replace(word.charAt(0), word.toUpperCase().charAt(0))); 
		
		final ListView lstDefinitions = (ListView) findViewById(R.id.lstDefinitions);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, lstCompressDef);
		lstDefinitions.setAdapter(adapter);
		
		lstDefinitions.setOnItemClickListener( new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View v, int item, long arg3) {
		    	final Dialog definitionDialog = new Dialog(v.getContext());

		    	definitionDialog.setContentView(R.layout.definition_dialog);
		    	definitionDialog.setTitle(lstDictionaries.get(item).toString());
		    
		    	final Button btnClose = (Button) definitionDialog.findViewById(R.id.btnCloseDefinition);
		    	final TextView lblDefinition = (TextView) definitionDialog.findViewById(R.id.lblDefinition);
		    	
		    	lblDefinition.setText(lstDefinitionsString.get(item).toString());
		    	
		    	btnClose.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						definitionDialog.dismiss();
						
					}
		    	});
		    
		    	definitionDialog.show(); 	
			}
		});
		
		final ImageButton btnPlaySound = (ImageButton) findViewById(R.id.btnPlaySound);
		btnPlaySound.setOnClickListener(actions);
		
		final Button btnDefineAnotherWord = (Button) findViewById(R.id.btnDefineOtherWord);
		btnDefineAnotherWord.setOnClickListener(actions);
		
		checkTTSAvailable();
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













