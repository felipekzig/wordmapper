package br.com.wordmapper.android.actions;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.activities.ShowMapActivity;
import br.com.wordmapper.android.service.MapperService;
import br.com.wordmapper.android.utils.AppSettings;

public class MapperActions implements OnClickListener, OnFocusChangeListener {
	
	private Activity mapperActivity;

	public MapperActions(Activity activity){
		this.mapperActivity = activity;
	}
	
	public void onClick(View v) {

		switch(v.getId()){
			case R.id.btnMapWord:
				this.mapWord();
			break;

			case R.id.btnCancelAction:
				this.cancel();
			break;

		}
	}
	
	private void mapWord(){
		
		final EditText txtWord2Map = (EditText) this.mapperActivity.findViewById(R.id.txtWord2Map);
		
		ArrayList<String> syns = null;
		ArrayList<String> ants = null;
		
		try {
			
			final MapperService service = new MapperService(txtWord2Map.getText().toString());
			service.execute();
			
			Log.d(AppSettings.TAG, service.getResponseJson());
				
			syns = service.getResponseObject().getSynonymous();
			ants = service.getResponseObject().getAntonyms();
			
		} catch (Exception e){
			Log.e(AppSettings.TAG, "Mapping Word", e);
		}		
	
		
		Intent i = new Intent(mapperActivity, ShowMapActivity.class);
		
		Bundle b = new Bundle();
		b.putCharSequence(ShowMapActivity.mainKey, txtWord2Map.getText().toString());
		b.putStringArrayList(ShowMapActivity.synKey, syns);
		b.putStringArrayList(ShowMapActivity.antKey, ants);
		
		i.putExtras(b);
		mapperActivity.startActivity(i);
		mapperActivity.finish();
	}
	
	private void cancel(){
		mapperActivity.finish();
	}

	public void onFocusChange(View v, boolean hasFocus) {	
		if (hasFocus){
			final EditText txtWord = (EditText) v.getRootView().findViewById(R.id.txtWord2Map);
			if (txtWord.getText().toString().equalsIgnoreCase(mapperActivity.getResources().getString(R.string.lblDefineWord).toString())) txtWord.setText("");
			txtWord.setTextColor(Color.BLACK);
		} else {
			final EditText txtWord = (EditText) v.getRootView().findViewById(R.id.txtWord2Map);
			if (txtWord.getText().toString().equalsIgnoreCase("")){
				txtWord.setText(R.string.lblDefineWord);
				txtWord.setTextColor(Color.rgb(187, 187, 187));
			}
		}
	}

}
