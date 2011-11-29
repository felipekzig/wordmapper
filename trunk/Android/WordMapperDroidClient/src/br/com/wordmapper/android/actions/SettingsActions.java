package br.com.wordmapper.android.actions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.activities.SingUpActivity;
import br.com.wordmapper.android.utils.AppSettings;

public class SettingsActions implements OnClickListener {

	private Activity settingsActivity;
	
	private View callerView;
	
	public SettingsActions(Activity activity){
		this.settingsActivity = activity;
	}
	
	public void onClick(View v) {
		
		this.callerView = v;
		
		switch(v.getId()){
			case R.id.btnApplySettings:
				this.applySettings();
			break;
	
			case R.id.btnSingUp:
				this.showSingUpActivity();
			break;
			
			case R.id.btnCancelSettings:
				this.settingsActivity.finish();
			break;
		}
		
	}

	private String getDictionaryId(Integer position){
		return settingsActivity.getResources().getStringArray(R.array.dictsIds)[position].toString();
	}
	
	private void applySettings(){
		final Spinner cmbDefaultDict = (Spinner) settingsActivity.findViewById(R.id.cmbDefaultDict);
		AppSettings.idDefaultDict = getDictionaryId(cmbDefaultDict.getSelectedItemPosition());
		
		AppSettings.saveSettings(settingsActivity.getApplicationContext());
		
		settingsActivity.finish();
	}
	
	private void showSingUpActivity(){
		Intent intent = new Intent();
		Context context = this.callerView.getRootView().getContext();
		
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClass(context, SingUpActivity.class);
		context.startActivity(intent);

	}

}
