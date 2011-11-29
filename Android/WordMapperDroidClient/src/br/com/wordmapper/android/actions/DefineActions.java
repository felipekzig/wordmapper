package br.com.wordmapper.android.actions;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.service.DefineService;
import br.com.wordmapper.android.utils.AppSettings;
import br.com.wordmapper.service.container.DefinitionContainer;

public class DefineActions implements OnClickListener {
	
	private Activity defineActivity;
	
	private View callerView;
	
	public List<CharSequence> ListItens = new ArrayList<CharSequence>(); 
	
	public DefineActions(Activity activity){
		this.defineActivity = activity;
	}

	public void onClick(View v) {
		
		this.callerView = v;
		
		switch(v.getId()){
			case R.id.btnDefine:
				this.defineWord();
			break;

			case R.id.btnReset:
				this.resetFields();
			break;
		}
	}
	
	private String getDictionaryId(Integer position){
		return defineActivity.getResources().getStringArray(R.array.dictsIds)[position].toString();
	}
	
	private void defineWord(){
		
		final EditText txtWord2Define = (EditText) this.defineActivity.findViewById(R.id.txtWord2Define);
		final Spinner cmbDictionaries = (Spinner) this.defineActivity.findViewById(R.id.cmbDictionaries);
	
		try {
			Log.d(AppSettings.TAG, this.getDictionaryId(cmbDictionaries.getSelectedItemPosition()));
			final DefineService service = new DefineService(txtWord2Define.getText().toString(), this.getDictionaryId(cmbDictionaries.getSelectedItemPosition()));
			service.execute();
				
			ListItens.removeAll(ListItens);
			for(DefinitionContainer definition: service.getResponseObject().getDefinitions()){
				ListItens.add(definition.getDefinition().trim().substring(0, 20));
			}
			
			AlertDialog.Builder builder = new AlertDialog.Builder(callerView.getContext());
			builder.setTitle(R.string.lblChooseDefinition);
			
			builder.setItems(ListItens.toArray(new CharSequence[ListItens.size()]), new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        
			    	final Dialog definitionDialog = new Dialog(callerView.getContext());

			    	definitionDialog.setContentView(R.layout.definition_dialog);
			    	definitionDialog.setTitle(service.getResponseObject().getDefinitions().get(item).getDictionary());
			    
			    	final Button btnClose = (Button) definitionDialog.findViewById(R.id.btnCloseDefinition);
			    	final TextView lblDefinition = (TextView) definitionDialog.findViewById(R.id.lblDefinition);
			    	
			    	lblDefinition.setText(service.getResponseObject().getDefinitions().get(item).getDefinition());
			    	
			    	btnClose.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							definitionDialog.dismiss();
							
						}
			    	});
			    	
			    	dialog.dismiss();
			    	definitionDialog.show();
			    }
			});
			
			builder.show();

		} catch (Exception e){
			Log.e(AppSettings.TAG, "Defining Word", e);
		}
	}
	
	private void resetFields(){
		Log.d(AppSettings.TAG, "Reset Action");
	}

}
