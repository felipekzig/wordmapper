package br.com.wordmapper.android.actions;

import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.utils.AppSettings;
import br.com.wordmapper.android.utils.WMService;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;

public class DefineActions implements OnClickListener {
	
	private Activity defineActivity;
	
	public DefineActions(Activity activity){
		this.defineActivity = activity;
	}

	public void onClick(View v) {
		
		switch(v.getId()){
			case R.id.btnDefine:
				this.defineWord();
			break;

			case R.id.btnReset:
				this.resetFields();
			break;
		}
	}
	
	private void defineWord(){
		
		final EditText txtWord2Define = (EditText) this.defineActivity.findViewById(R.id.txtWord2Define);
		final Spinner cmbDictionaries = (Spinner) this.defineActivity.findViewById(R.id.cmbDictionaries);
		
		Log.i(AppSettings.TAG, ((Long)cmbDictionaries.getSelectedItemId()).toString());
		
		WMService service = new WMService();

		service.setTpOperation(1);
		
		try {
		
			
		} catch (Exception e) {
			Log.e(AppSettings.TAG, "Define Action", e);
		}
		
	}
	
	private void resetFields(){
		Log.d(AppSettings.TAG, "Reset Action");
	}

}
