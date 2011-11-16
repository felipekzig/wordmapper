package br.com.wordmapper.android.actions;

import br.com.wordmapper.android.activities.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingsActions implements OnClickListener {

	private Activity settingsActivity;
	
	public SettingsActions(Activity activity){
		this.settingsActivity = activity;
	}
	
	public void onClick(View v) {
		
		switch(v.getId()){
			case R.id.btnApplySettings:
				this.ApplySettings();
			break;
	
			case R.id.btnRegister:
				this.Register();
			break;
			
			case R.id.btnCancelSettings:
				this.CancelSettings();
			break;
		}
		
	}


	private void ApplySettings(){
		
	}
	
	private void Register(){
		
	}
	
	private void CancelSettings(){
		this.settingsActivity.finish();
	}

}
