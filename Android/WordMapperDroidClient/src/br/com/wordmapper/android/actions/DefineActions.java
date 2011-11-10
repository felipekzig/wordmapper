package br.com.wordmapper.android.actions;

import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.utils.AppSettings;
import br.com.wordmapper.android.utils.WMService;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class DefineActions implements OnClickListener {
	
	private View defineActivity;

	public void onClick(View v) {
		this.defineActivity = v.getRootView();
		
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
		
		WMService service = new WMService();
		
		service.setWord(txtWord2Define.getText().toString());
		service.setTpOperation(1);
		
		try {
			service.requestServer();
			
			Log.i(AppSettings.TAG, service.getResponseJson());
			Log.i(AppSettings.TAG, service.getMainDict());
		} catch (Exception e) {
			Log.e(AppSettings.TAG, "Define Action", e);
		}
		
		
		
	}
	
	private void resetFields(){
		Log.d(AppSettings.TAG, "Reset Action");
	}

}
