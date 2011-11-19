package br.com.wordmapper.android.actions;

import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.utils.AppSettings;
import br.com.wordmapper.android.utils.WMService;
import br.com.wordmapper.service.container.UserContainer;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

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
		UserContainer user = new UserContainer();
		
		final EditText txtFirstName = (EditText) this.singUpActivity.findViewById(R.id.txtFirstName);
		final EditText txtLastName = (EditText) this.singUpActivity.findViewById(R.id.txtLastName);
		final EditText txtEmail = (EditText) this.singUpActivity.findViewById(R.id.txtEmail);
		final EditText txtCity = (EditText) this.singUpActivity.findViewById(R.id.txtCity);
		final EditText txtCountry = (EditText) this.singUpActivity.findViewById(R.id.txtCountry);
		final EditText txtZipCode = (EditText) this.singUpActivity.findViewById(R.id.txtPostCode);
		
		user.setFirstName(txtFirstName.getText().toString());
		user.setLastName(txtLastName.getText().toString());
		user.setEmail(txtEmail.getText().toString());
		user.setCity(txtCity.getText().toString());
		user.setCountry(txtCountry.getText().toString());
		user.setZipCode(txtZipCode.getText().toString());
		user.setSqlOperation(UserContainer.INSERT);
		
		WMService service = new WMService();
		service.setTpOperation(WMService.SINGUP_OPERATION);
		
		try {
			
			service.requestServer(user.getJson());
			
			Toast msg = Toast.makeText(singUpActivity, singUpActivity.getResources().getString(R.string.msgSingUpFinished), Toast.LENGTH_LONG);
			msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
			msg.show();
			
			singUpActivity.finish();
	
			
		} catch (Exception e) {
			Log.e(AppSettings.TAG, "Error while trying to sing up", e);
		}
	}

}
