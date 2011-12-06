package br.com.wordmapper.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import br.com.wordmapper.android.actions.MapperActions;

public class MapperActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapper);
		
		MapperActions actions = new MapperActions(this);
		
        final EditText txtWord2Map = (EditText) findViewById(R.id.txtWord2Map);
        txtWord2Map.setOnFocusChangeListener(actions);
		
		final Button btnMapWord = (Button) findViewById(R.id.btnMapWord);
		btnMapWord.setOnClickListener(actions);
		
		final Button btnCancel = (Button) findViewById(R.id.btnCancelAction);
		btnCancel.setOnClickListener(actions);	
	}
	
}
