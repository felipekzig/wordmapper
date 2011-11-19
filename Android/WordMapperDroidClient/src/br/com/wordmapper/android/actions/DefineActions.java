package br.com.wordmapper.android.actions;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.com.wordmapper.android.activities.R;
import br.com.wordmapper.android.utils.AppSettings;
import br.com.wordmapper.android.utils.WMService;
import br.com.wordmapper.service.container.DefineContainer;
import br.com.wordmapper.service.container.DefinitionContainer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

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
	
	private void defineWord(){
		
		final EditText txtWord2Define = (EditText) this.defineActivity.findViewById(R.id.txtWord2Define);
		//final Spinner cmbDictionaries = (Spinner) this.defineActivity.findViewById(R.id.cmbDictionaries);
		
		DefineContainer define = new DefineContainer();
		define.setIdMainDict("wn");
		define.setWord(txtWord2Define.getText().toString());
	
		try {
			WMService service = new WMService();
			service.setTpOperation(WMService.DEFINE_OPERATION);			
			
			service.requestServer(define.getJson());
			
			final DefineContainer defineResponse = new Gson().fromJson(service.getResponseJson(), DefineContainer.class);		
			ListItens.removeAll(ListItens);
			for(DefinitionContainer definition: defineResponse.getDefinitions()){
				ListItens.add(definition.getDefinition().trim().substring(0, 20));
			}
			
			AlertDialog.Builder builder = new AlertDialog.Builder(callerView.getContext());
			builder.setTitle(R.string.lblChooseDefinition);
			
			builder.setItems(ListItens.toArray(new CharSequence[ListItens.size()]), new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        
			    	Context mContext = getApplicationContext();
			    	Dialog dialog = new Dialog(mContext);

			    	dialog.setContentView(R.layout.custom_dialog);
			    	dialog.setTitle("Custom Dialog");
			    }
			});	
	}
	
	private void resetFields(){
		Log.d(AppSettings.TAG, "Reset Action");
	}

}
