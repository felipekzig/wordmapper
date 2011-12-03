package br.com.wordmapper.android.activities;

import br.com.wordmapper.android.actions.SingUpActions;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class SingUpActivity extends Activity {
	
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.singup);
	        
	        SingUpActions actions = new SingUpActions(this);
	        
	        final Button btnSingUp = (Button) findViewById(R.id.btnSingUp);
	        btnSingUp.setOnClickListener(actions);
	        
	        final Button btnCancel = (Button) findViewById(R.id.btnCancel);
	        btnCancel.setOnClickListener(actions);	        
	   }
	      
}
