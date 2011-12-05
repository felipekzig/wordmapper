package br.com.wordmapper.android.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import br.com.wordmapper.android.utils.AppSettings;

public class MapperActivity extends Activity {
	
	private final static int idMainWord = 2228241;
	
	private int countWords = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final ArrayList<String> syn = new ArrayList<String>();
		syn.add("worker");
		syn.add("worker1");
		syn.add("worker2");
		syn.add("worker3");
		syn.add("worker4");
		
		final ArrayList<String> ant = new ArrayList<String>();
		ant.add("lazy");
		ant.add("lazy1");
		ant.add("lazy2");
		ant.add("lazy3");
		ant.add("lazy4");
		
		final RelativeLayout layout = new RelativeLayout(this);
		
		final RelativeLayout mainWord = addMainWord("Understand");
		
		layout.addView(mainWord);
		
		layout.addView(addRelatedWord("Lazylazy", mainWord, false));
		layout.addView(addRelatedWord("Lazylazy2", mainWord, false));
		layout.addView(addRelatedWord("Lazylazy3", mainWord, false));
		
		layout.addView(addRelatedWord("Lazylazy4", mainWord, false));
		layout.addView(addRelatedWord("Lazylazy5", mainWord, false));
		layout.addView(addRelatedWord("Lazylazy6", mainWord, false));
		
		setContentView(layout);
		
	}
	
	private RelativeLayout addWord(String word){
		try {
			
			RelativeLayout container = new RelativeLayout(this);
		
			Button btnWord = new Button(container.getContext());
			btnWord.setText(word);
			btnWord.setSingleLine(true);
			btnWord.setMinWidth(80);
			
			container.addView(btnWord);
			return container;
			
		} catch (Exception e){
			Log.e(AppSettings.TAG, "Error while adding a new word", e);
		}
		
		return null;
	}
	
	private RelativeLayout addMainWord(String word){
		try {
			RelativeLayout container = this.addWord(word);
			container.setId(idMainWord);
			
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			
			container.setLayoutParams(params);
			
			return container;
		} catch (Exception e) {
			Log.e(AppSettings.TAG, "Error while adding a new word", e);
		}
		
		return null;
	}
	
	private RelativeLayout addRelatedWord(String word, RelativeLayout mainWord, boolean isSynonym){
		
		try {
			
			RelativeLayout container = this.addWord(word);
			container.setLayoutParams(getLayoutParams());
			
			countWords++;
			
			return container;
			
		} catch (Exception e){
			Log.e(AppSettings.TAG, "Error while adding a new word", e);
		}
		
		return null;
	}
	
	private RelativeLayout.LayoutParams getLayoutParams(){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		if (countWords == 0) {
			Log.i(AppSettings.TAG, "0");
			params.addRule(RelativeLayout.LEFT_OF, idMainWord);
			params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
			params.setMargins(0, 0, 70, 0);
			
		} else if (countWords <= 2) {
			Log.i(AppSettings.TAG, "1 2");
			params = layoutParams1();
			
		} else if (countWords == 3){
			Log.i(AppSettings.TAG, "3");
			params.addRule(RelativeLayout.ABOVE, idMainWord);
			params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
			params.setMargins(0, 0, 0, 45);
			
		} else if (countWords <= 5) {
			Log.i(AppSettings.TAG, "4 5");
			params = layoutParams2();
			
		} else if (countWords <= 8) {
			
			params = layoutParams3();
			
		} else if (countWords <= 11) {
			
			params = layoutParams4();
			
		}
		
		return params;
	}
	
	private RelativeLayout.LayoutParams layoutParams1(){
		Log.d(AppSettings.TAG, "Defining layout params 1");
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		params.addRule(RelativeLayout.LEFT_OF, idMainWord);
		params.addRule(RelativeLayout.ABOVE, idMainWord);
		params.setMargins(0, 0, 70 - (countWords * 30), (countWords-1) * 35);
		
		return params;
	}
	
	private RelativeLayout.LayoutParams layoutParams2(){
		Log.d(AppSettings.TAG, "Defining layout params 2");
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		params.addRule(RelativeLayout.RIGHT_OF, idMainWord);
		params.addRule(RelativeLayout.ABOVE, idMainWord);
		params.setMargins(((Math.round(countWords+1/2) - 1) * 10), 0, 0, (Math.round(countWords+1/2)-1) * 35);
		
		return params;	
	}
	
	private RelativeLayout.LayoutParams layoutParams3(){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		return params;		
	}
	
	private RelativeLayout.LayoutParams layoutParams4(){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		return params;		
	}	
}
