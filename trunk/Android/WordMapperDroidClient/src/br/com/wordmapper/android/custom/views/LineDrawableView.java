package br.com.wordmapper.android.custom.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class LineDrawableView extends View {
	
	float[] positions;

	public LineDrawableView(Context context, float[] positions) {
		super(context);
		this.positions = positions;
		
		setBackgroundColor(Color.TRANSPARENT);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		Paint paint =  new Paint();
		paint.setColor(Color.rgb(51, 153, 51));
		paint.setStrokeWidth(3);
		
		canvas.drawColor(Color.TRANSPARENT);
		canvas.drawLine(positions[0], positions[1], positions[2], positions[3], paint);
		

	}
	
}
