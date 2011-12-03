package br.com.wordmapper.android.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MapperActivity extends Activity {

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
		
		setContentView(layout);
		
	}
	
}
