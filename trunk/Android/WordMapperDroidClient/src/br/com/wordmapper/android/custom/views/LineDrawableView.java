package br.com.wordmapper.android.custom.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

public class LineDrawableView extends View {
	
	private final int margin_h = 5;
	private final int margin_v = 11;

	private boolean isSynonym;
	private RelativeLayout relatedLayout;
	private RelativeLayout mainLayout;
	
	private Paint paint = new Paint();
	
	public LineDrawableView(Context context) {
		super(context);
		paint.setStrokeWidth(3);
		setBackgroundColor(Color.TRANSPARENT);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawColor(Color.TRANSPARENT);
		
		if (isSynonym){
			canvas.drawLine(relatedLayout.getRight() + margin_h, relatedLayout.getTop() + margin_v, mainLayout.getLeft() - margin_h, mainLayout.getTop() + margin_v, paint);
		} else {
			canvas.drawLine(relatedLayout.getLeft() - margin_h, relatedLayout.getTop() + margin_v, mainLayout.getRight() + margin_h, mainLayout.getTop() + margin_v, paint);
		}
	}
	
	public void setRelatedLayout(RelativeLayout layout){
		this.relatedLayout = layout;
	}
	
	public void setMainLayout(RelativeLayout layout){
		this.mainLayout = layout;
	}
	
	public void setIsSynonym(boolean isSynonym){
		this.isSynonym = isSynonym;

		if (isSynonym){
			paint.setColor(Color.rgb(51, 153, 51));
		} else {
			paint.setColor(Color.rgb(153, 00, 00));
		}
	}
	
}
