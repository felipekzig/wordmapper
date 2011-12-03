package br.com.wordmapper.android.actions;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import br.com.wordmapper.android.activities.DefineActivity;
import br.com.wordmapper.android.activities.DefinitionsActivity;
import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.utils.AppSettings;

public class DefinitionsActions implements OnClickListener {
	
	private DefinitionsActivity definitionsActivity;
	
	private View callerView;

	public DefinitionsActions(DefinitionsActivity activity){
		definitionsActivity = activity;
	}

	public void onClick(View v) {
		
		this.callerView = v;
		
		switch(v.getId()){
			case R.id.btnPlaySound:
				this.playSound();
			break;
			
			case R.id.btnDefineOtherWord:
				this.defineAnotherWord();
			break;

		}

	}
	
	private void playSound(){
		final TextView lblWordDefined = (TextView) definitionsActivity.findViewById(R.id.lblWordDefined);
		try {
			definitionsActivity.speech.speak(lblWordDefined.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
		} catch (Exception e){
			Log.e(AppSettings.TAG, "ERRO", e);
		}
	}
	
	private void defineAnotherWord(){
		Intent i = new Intent(this.callerView.getRootView().getContext(), DefineActivity.class);
		definitionsActivity.startActivity(i);
		definitionsActivity.finish();		
	}

}
