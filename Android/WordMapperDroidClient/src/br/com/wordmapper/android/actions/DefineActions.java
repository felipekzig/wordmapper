package br.com.wordmapper.android.actions;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import br.com.wordmapper.android.activities.DefineActivity;
import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.adapters.ExpandableListDefinitionsAdapter;
import br.com.wordmapper.android.service.DefineService;
import br.com.wordmapper.android.utils.AppSettings;
import br.com.wordmapper.service.container.DefinitionContainer;

public class DefineActions implements OnClickListener, OnFocusChangeListener, OnChildClickListener {
	
	private DefineActivity defineActivity;

	public ExpandableListDefinitionsAdapter adapter;
	
	public DefineActions(DefineActivity activity){
		this.defineActivity = activity;
		this.adapter = new ExpandableListDefinitionsAdapter(defineActivity, new ArrayList<String>(), new ArrayList<ArrayList<String>>());
	}

	public void onClick(View v) {

		switch(v.getId()){
			case R.id.btnDefine:
				this.defineWord();
			break;

			case R.id.btnReset:
				this.resetFields();
			break;
			
			case R.id.btnPlaySound:
				this.playSound();
			break;
		}
	}
	
	private void defineWord(){
		
		final EditText txtWord2Define = (EditText) this.defineActivity.findViewById(R.id.txtWord2Define);
	
		try {
			
			final DefineService service = new DefineService(txtWord2Define.getText().toString());
			service.execute();
			
			Log.i(AppSettings.TAG, service.getResponseJson());
			
			fillListDefinitions(service.getResponseObject().getDefinitions());	
			
			final ImageButton btnPlaySound = (ImageButton) defineActivity.findViewById(R.id.btnPlaySound);
			btnPlaySound.setEnabled(true);
			
		} catch (Exception e){
			Log.e(AppSettings.TAG, "Defining Word", e);
		}
	}
	
	private void resetFields(){
		final EditText txtWord = (EditText) defineActivity.findViewById(R.id.txtWord2Define);
		txtWord.setText("");
		txtWord.setTextColor(Color.BLACK);
		txtWord.requestFocus();
		
		this.adapter = new ExpandableListDefinitionsAdapter(defineActivity, new ArrayList<String>(), new ArrayList<ArrayList<String>>());
		final ExpandableListView lstDefinitions = (ExpandableListView) defineActivity.findViewById(R.id.lstDefinitions);
		lstDefinitions.setAdapter(adapter);
		
		final ImageButton btnPlaySound = (ImageButton) defineActivity.findViewById(R.id.btnPlaySound);
		btnPlaySound.setEnabled(false);		
	}

	public void onFocusChange(View v, boolean hasFocus) {	
		if (hasFocus){
			final EditText txtWord = (EditText) v.getRootView().findViewById(R.id.txtWord2Define);
			if (txtWord.getText().toString().equalsIgnoreCase(defineActivity.getResources().getString(R.string.lblDefineWord).toString())) txtWord.setText("");
			txtWord.setTextColor(Color.BLACK);
		} else {
			final EditText txtWord = (EditText) v.getRootView().findViewById(R.id.txtWord2Define);
			if (txtWord.getText().toString().equalsIgnoreCase("")){
				txtWord.setText(R.string.lblDefineWord);
				txtWord.setTextColor(Color.rgb(187, 187, 187));
			}
		}
	}
	
	private void playSound(){
		final TextView lblWordDefined = (TextView) defineActivity.findViewById(R.id.txtWord2Define);
		try {
			Log.i(AppSettings.TAG, "FALA PORRA");
			defineActivity.speech.speak(lblWordDefined.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
		} catch (Exception e){
			Log.e(AppSettings.TAG, "ERRO", e);
		}
	}	

	private void fillListDefinitions(List<DefinitionContainer> definitons){
		this.adapter = new ExpandableListDefinitionsAdapter(defineActivity, new ArrayList<String>(), new ArrayList<ArrayList<String>>());
		final ExpandableListView lstDefinitions = (ExpandableListView) defineActivity.findViewById(R.id.lstDefinitions);
		
		for(DefinitionContainer definition : definitons) adapter.addItem(definition);
		lstDefinitions.setAdapter(adapter);
		
		lstDefinitions.expandGroup((AppSettings.idDefaultDict.equals("gcide")) ? 0 : 1);
	}

	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		
    	final Dialog definitionDialog = new Dialog(v.getContext());

    	definitionDialog.setContentView(R.layout.definition_dialog);
    	definitionDialog.setTitle(adapter.getGroup(groupPosition));
    
    	final Button btnClose = (Button) definitionDialog.findViewById(R.id.btnCloseDefinition);
    	final TextView lblDefinition = (TextView) definitionDialog.findViewById(R.id.lblDefinition);
    	
    	lblDefinition.setText(adapter.getChild(groupPosition, childPosition).toString());
    	
    	btnClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				definitionDialog.dismiss();
			}
    	});
    
    	definitionDialog.show(); 		
		
		return false;
	}
	
}
