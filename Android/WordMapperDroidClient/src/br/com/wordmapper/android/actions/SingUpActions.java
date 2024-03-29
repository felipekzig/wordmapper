package br.com.wordmapper.android.actions;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.service.SingUpService;
import br.com.wordmapper.android.utils.AppSettings;
import br.com.wordmapper.service.container.UserContainer;

public class SingUpActions implements OnClickListener {
	
	private Activity singUpActivity;
	
	public SingUpActions(Activity activity){
		this.singUpActivity = activity;
	}
	
	public void onClick(View v) {
		
		switch(v.getId()){
		
			case R.id.btnSingUp:
				this.singUp();
			break;
			
			case R.id.btnCancel:
				this.singUpActivity.finish();
			break;
			
		}
		
	}
	
	private String getCountryCode(int position){
		return "BR";
	}
	
	private void singUp(){
		UserContainer user = new UserContainer();
		
		final EditText txtFirstName = (EditText) this.singUpActivity.findViewById(R.id.txtFirstName);
		final EditText txtLastName = (EditText) this.singUpActivity.findViewById(R.id.txtLastName);
		final EditText txtEmail = (EditText) this.singUpActivity.findViewById(R.id.txtEmail);
		final EditText txtCity = (EditText) this.singUpActivity.findViewById(R.id.txtCity);
		final Spinner cmbCountry = (Spinner) this.singUpActivity.findViewById(R.id.cmbCountry);
		final EditText txtZipCode = (EditText) this.singUpActivity.findViewById(R.id.txtPostCode);
		
		user.setFirstName(txtFirstName.getText().toString());
		user.setLastName(txtLastName.getText().toString());
		user.setEmail(txtEmail.getText().toString());
		user.setCity(txtCity.getText().toString());
		user.setCountry(getCountryCode(cmbCountry.getSelectedItemPosition()));
		user.setZipCode(txtZipCode.getText().toString());
		user.setSqlOperation(UserContainer.INSERT);
		
		SingUpService service = new SingUpService(user);
		
		try {
			
			service.execute();
			
			Log.i(AppSettings.TAG, service.getResponseJson());
			
			Toast msg = Toast.makeText(singUpActivity, singUpActivity.getResources().getString(R.string.msgSingUpFinished), Toast.LENGTH_LONG);
			msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
			msg.show();
			
			AppSettings.userLicense = user.getLicense();
			
			singUpActivity.finish();
	
			
		} catch (Exception e) {
			Log.e(AppSettings.TAG, "Error while trying to sing up", e);
		}
	}

}
