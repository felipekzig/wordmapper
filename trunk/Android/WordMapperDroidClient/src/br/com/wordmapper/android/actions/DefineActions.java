package br.com.wordmapper.android.actions;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import br.com.wordmapper.android.activities.DefinitionsActivity;
import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.service.DefineService;
import br.com.wordmapper.android.utils.AppSettings;
import br.com.wordmapper.service.container.DefinitionContainer;

public class DefineActions implements OnClickListener {
	
	private Activity defineActivity;
	
	private View callerView; 
	
	public DefineActions(Activity activity){
		this.defineActivity = activity;
	}

	public void onClick(View v) {
		
		this.callerView = v;
		
		switch(v.getId()){
			case R.id.btnDefine:
				this.defineWord();
			break;

			case R.id.btnReset:
				this.resetFields();
			break;
		}
	}
	
	private String getDictionaryId(Integer position){
		return defineActivity.getResources().getStringArray(R.array.dictsIds)[position].toString();
	}
	
	private void defineWord(){
		
		final EditText txtWord2Define = (EditText) this.defineActivity.findViewById(R.id.txtWord2Define);
		final Spinner cmbDictionaries = (Spinner) this.defineActivity.findViewById(R.id.cmbDictionaries);
	
		try {
			
			final DefineService service = new DefineService(txtWord2Define.getText().toString(), this.getDictionaryId(cmbDictionaries.getSelectedItemPosition()));
			service.execute();
			
			ArrayList<CharSequence> lstDictionaries = new ArrayList<CharSequence>();
			lstDictionaries.removeAll(lstDictionaries);
			
			ArrayList<CharSequence> lstItensDefinition = new ArrayList<CharSequence>();
			lstItensDefinition.removeAll(lstItensDefinition);			
			
			for(DefinitionContainer definition: service.getResponseObject().getDefinitions()){
				lstDictionaries.add(definition.getDictionary());
				lstItensDefinition.add(definition.getDefinition().trim());
			}
			
			
			Bundle b = new Bundle();
			b.putString(DefinitionsActivity.WordKey, txtWord2Define.getText().toString());
			b.putCharSequenceArrayList(DefinitionsActivity.DictionariesKey, lstDictionaries);
			b.putCharSequenceArrayList(DefinitionsActivity.DefinitionsKey, lstItensDefinition);
			
			Intent i = new Intent(this.callerView.getContext(), DefinitionsActivity.class);
			i.putExtras(b);
			
			defineActivity.startActivity(i);
			defineActivity.finish();			
			
		} catch (Exception e){
			Log.e(AppSettings.TAG, "Defining Word", e);
		}
	}
	
	private void resetFields(){
		Log.d(AppSettings.TAG, "Reset Action");
	}

}
