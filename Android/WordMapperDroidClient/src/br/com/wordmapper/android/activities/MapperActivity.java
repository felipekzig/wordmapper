package br.com.wordmapper.android.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.wordmapper.android.custom.views.LineDrawableView;
import br.com.wordmapper.android.utils.AppSettings;

public class MapperActivity extends Activity {
	
	private static int idMainWord = 21329128;
	
	private final int MAX_WORDS = 5;
	
	private int countSynWords = 1;
	private int countAntWords = 1;
	
	private int qtdSynWords;
	private int qtdAntWords;
	
	private RelativeLayout mapperLayout;

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
		
		mapperLayout = new RelativeLayout(this);
		mapperLayout.setBackgroundColor(Color.WHITE);
		setContentView(mapperLayout);
		
		final RelativeLayout mainWord = addMainWord("Understand");
		
		qtdSynWords = 3;
		
		qtdAntWords = 3;
		
		addRelatedWord("Lazylazy1", mainWord, true);
		addRelatedWord("Lazylazy1", mainWord, true);
		addRelatedWord("Lazylazy1", mainWord, true);
		//addRelatedWord("Lazylazy1", mainWord, true);
		//addRelatedWord("Lazylazy1", mainWord, true);
		
		addRelatedWord("Lazylazy4", mainWord, false);
		addRelatedWord("Lazylazy4", mainWord, false);
		addRelatedWord("Lazylazy4", mainWord, false);
		
	}
	
	private TextView createTextView(Context context, String text){
		TextView textView = new TextView(context);
		textView.setText(text);
		textView.setSingleLine(true);
		textView.setTextAppearance(context, android.R.style.TextAppearance_Medium);
		textView.setTextColor(Color.BLACK);		
		
		return textView;
	}

/*	
	private ImageView createImageView(Context context, int id){
		ImageView imgRelated = new ImageView(context);
		imgRelated.setImageResource(R.drawable.img_mapper);
		imgRelated.setId(id);
		
		return imgRelated;
	}
*/	

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
			
			//ImageView imageView = createImageView(container.getContext(), Math.abs((word + "_img").hashCode()));
			//imageView.setLayoutParams(getImageParams(isSynonym, txtWord.getId()));
			
			container.addView(txtWord);
			//container.addView(imageView);
			mapperLayout.addView(container);
			
			linkWithMainWord(container, isSynonym);
			
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
			params.rightMargin = 65;
			
			
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
	
/*	
	private RelativeLayout.LayoutParams getImageParams(boolean isSynonym, int id){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		
		if (isSynonym){
			params.addRule(RelativeLayout.RIGHT_OF, id);
			params.leftMargin = 3;
		} else {
			params.addRule(RelativeLayout.LEFT_OF, id);
			params.rightMargin = 60;	
		}
			
		return params;
	}	
*/	

	private void linkWithMainWord(RelativeLayout layout, boolean isSynonym){
		
		float top;
		
		float x0;
		float x1;
		
		if (isSynonym){
			top = (float) (((countSynWords - 1) * (30 + ((5 - qtdSynWords) * 9)) + 8.7));
			x0 = 99;
			x1 = 160;		
		} else {
			top = (float) (((countAntWords - 1) * (30 + ((5 - qtdAntWords) * 9)) + 8.7));
			x0 = 315;
			x1 = 241;			
		}

		
		LineDrawableView line = new LineDrawableView(layout.getContext(), new float[]{x0, top, x1, 101});
		line.setBackgroundColor(Color.TRANSPARENT);
	
		mapperLayout.addView(line);

	}
	
}
