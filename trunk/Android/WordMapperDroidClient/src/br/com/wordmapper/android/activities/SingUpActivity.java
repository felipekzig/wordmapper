package br.com.wordmapper.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import br.com.wordmapper.android.actions.SingUpActions;

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
	        
	        final Spinner cmbCountry = (Spinner) findViewById(R.id.cmbCountry);
	        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.codeCountries, android.R.layout.simple_spinner_dropdown_item);
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        
	        cmbCountry.setAdapter(adapter);
	      
	   }
	      
}
