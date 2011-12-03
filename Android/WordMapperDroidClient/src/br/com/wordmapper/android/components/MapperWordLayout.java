package br.com.wordmapper.android.components;

import br.com.wordmapper.android.activities.R;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MapperWordLayout extends RelativeLayout {
	
	public TextView txtWord;
	public ImageView imgMapper;

	public MapperWordLayout(Context context, String id, String word) {
		super(context);

		txtWord = new TextView(context, null, R.style.relatedWord);
		txtWord.setText(word);
		
		imgMapper = new ImageView(context, null, R.style.relatedWord_img);
		imgMapper.setImageResource(R.id.imgMapper);
	}


}
