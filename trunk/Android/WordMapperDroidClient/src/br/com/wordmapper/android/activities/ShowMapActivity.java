package br.com.wordmapper.android.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.wordmapper.android.custom.views.LineDrawableView;
import br.com.wordmapper.android.utils.AppSettings;

public class ShowMapActivity extends Activity {
	
	public final static String mainKey = "Main";
	public final static String synKey = "Syns";
	public final static String antKey = "Ants";
	
	private static int idMainWord = 21329128;
	
	private final int MAX_WORDS = 5;
	
	private int countSynWords = 1;
	private int countAntWords = 1;
	
	private int qtdSynWords;
	private int qtdAntWords;
	
	private String strMainWord;
	
	private RelativeLayout mapperLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle b = getIntent().getExtras();
	
		final ArrayList<String> listSyn = b.getStringArrayList(synKey);
		qtdSynWords = (listSyn.size()>MAX_WORDS) ? MAX_WORDS : listSyn.size();
		
		final ArrayList<String> listAnt = b.getStringArrayList(antKey);
		qtdAntWords = (listAnt.size()>MAX_WORDS) ? MAX_WORDS : listAnt.size();
		
		mapperLayout = new RelativeLayout(this);
		mapperLayout.setBackgroundColor(Color.WHITE);
		
		strMainWord = b.getCharSequence(mainKey).toString();
		final RelativeLayout mainWord = addMainWord(strMainWord);
		
		for(int i=0;i<qtdSynWords;i++)	addRelatedWord(listSyn.get(i).toString(), mainWord, true);
		for(int i=0;i<qtdAntWords;i++)	addRelatedWord(listAnt.get(i).toString(), mainWord, false);
		
		
		setContentView(mapperLayout);

	}
	
	private TextView createTextView(Context context, String text){
		final TextView textView = new TextView(context);
		textView.setText(text);
		textView.setTag(text);
		textView.setSingleLine(true);
		textView.setTextAppearance(context, android.R.style.TextAppearance_Medium);
		textView.setTextColor(Color.BLACK);	
		
		textView.setOnLongClickListener(new OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				
				Intent i = new Intent(v.getContext(), DefineActivity.class);
				
				Bundle b = new Bundle();
				b.putString(DefineActivity.defineWordKey, (String) v.getTag());		
				
				i.putExtras(b);
				ShowMapActivity.this.startActivity(i);
				
				return false;
			}
		});
		
		return textView;
	}

	private RelativeLayout addMainWord(String word){
		try {
			
			RelativeLayout container = new RelativeLayout(this);	
			
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			
			container.setLayoutParams(params);
			container.setId(idMainWord);
			
			container.addView(createTextView(container.getContext(), word));		
			
			mapperLayout.addView(container);
			
			return container;
			
		} catch (Exception e) {
			Log.e(AppSettings.TAG, "Error while adding a new word", e);
		}
		
		return null;
	}

	private void addRelatedWord(String word, RelativeLayout mainWord, boolean isSynonym){
		
		try {
	
			RelativeLayout container = new RelativeLayout(this);
			container.setLayoutParams(getLayoutParams(isSynonym));
			
			TextView txtWord = createTextView(container.getContext(), word);
			txtWord.setId(Math.abs(word.hashCode()));
		
			container.addView(txtWord);
			mapperLayout.addView(container);
			
			linkWithMainWord(container, mainWord, isSynonym);
			
		} catch (Exception e){
			Log.e(AppSettings.TAG, "Error while adding a new word", e);
		}
		
	}

	private RelativeLayout.LayoutParams getLayoutParams(boolean isSynonym){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		int index;
		
		int aux = 30;
		
		if (isSynonym){
			params.addRule(RelativeLayout.LEFT_OF, idMainWord);
			params.rightMargin = 80;
				
			aux += (5 - qtdSynWords) * 9;
			
			index = countSynWords;	
			countSynWords++;
		} else {
			params.addRule(RelativeLayout.RIGHT_OF, idMainWord);
			params.leftMargin = 80;
			
			aux += (5 - qtdAntWords) * 9;
			
			index = countAntWords;	
			countAntWords++;
		}
		
		params.topMargin = index * aux;
		
		return params;
	}

	private void linkWithMainWord(RelativeLayout layout, RelativeLayout mainLayout, boolean isSynonym){
		
		LineDrawableView line = new LineDrawableView(layout.getContext());
		line.setBackgroundColor(Color.TRANSPARENT);
		line.setRelatedLayout(layout);
		line.setMainLayout(mainLayout);
		line.setIsSynonym(isSynonym);
		
		
		mapperLayout.addView(line);

	}
	
}
