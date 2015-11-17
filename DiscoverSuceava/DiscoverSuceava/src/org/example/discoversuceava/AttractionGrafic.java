package org.example.discoversuceava;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class AttractionGrafic extends View{
	public int[] values= null;
	public List<String> names = new ArrayList<String>();
	Paint p = new Paint();
	TextPaint paint = new TextPaint();
	Random r = new Random();
	

	public AttractionGrafic(Context context){
		super(context);
	}
	
	public AttractionGrafic(Context context, AttributeSet att){
		super(context, att);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		
		paint.setColor(Color.BLACK);
		paint.setTextSize(30);
		paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
		p.setColor(Color.BLACK);
		p.setStrokeWidth(40);
		canvas.drawLine(0,this.getHeight()-5, this.getWidth(), this.getHeight()-5, p);
		//pt fiecare valoarea vreau sa desenez o bara 
		// trebuie sa determin latimea unei bare = latime view/ nr de valori
		//inaltimea maxima a barei va fi maxim din vector 
		//celeltate inaltimi v/max *h
		if(values != null){
			int barWidth = this.getWidth()/values.length;
			int max = 0;
			for(int i=0; i<values.length; i++){
				if(values[i]>max){
				max = values[i];
				}
			}
			
			//pt fiecare valoare inaltimea barei 
			for(int i=0; i<values.length; i++){
				int barHeight = (int)(((float)values[i]/max) * this.getHeight());
				int x = i%5;
				switch(x){
					case 0: p.setColor(Color.MAGENTA);
					break;
					case 1: p.setColor(Color.GREEN);
					break;
					case 2: p.setColor(Color.YELLOW);
					break;
					case 3: p.setColor(Color.RED);
					break;
					case 4: p.setColor(Color.LTGRAY);
					break;
				}
				Rect bar = new Rect(i*barWidth, this.getHeight()- barHeight, (i+1)* barWidth, this.getHeight()-5 );
				canvas.drawRect(bar, p);
				
				StaticLayout sl = new StaticLayout(names.get(i), paint, (int)bar.width(), Layout.Alignment.ALIGN_CENTER, 1, 1, false);
				canvas.save();
				canvas.translate(bar.left, bar.top);
				sl.draw(canvas);
				canvas.restore();
			}
		}
		
	}
}
