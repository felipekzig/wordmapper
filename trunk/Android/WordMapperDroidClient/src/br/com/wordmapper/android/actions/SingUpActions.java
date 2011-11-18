package br.com.wordmapper.android.actions;

import br.com.wordmapper.android.activities.R;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class SingUpActions implements OnClickListener {
	
	private Activity singUpActivity;
	
	private View callerView;

	public SingUpActions(Activity activity){
		this.singUpActivity = activity;
	}
	
	public void onClick(View v) {
		
		this.callerView = v;
		
		switch(v.getId()){
		
			case R.id.btnSingUp:
				this.singUp();
			break;
			
			case R.id.btnCancel:
				this.singUpActivity.finish();
			break;
			
		}
		
	}
	
	private void singUp(){
		
	}

}
