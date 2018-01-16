package istat.android.widget.view;


import istat.android.widget.utils.WidgetUtil;
import istat.android.widget.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchMe extends View{
Context ctx;
public boolean effect,haveNumber=false;
Bitmap bmp;
int ImagMarg=20;
int Number=0;
	public TouchMe(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		addListener();
		ctx=context;
		init();
		
	}
	public TouchMe(Context context, AttributeSet attrs) {
		super(context, attrs);
		addListener();
		ctx=context;
		init();
		
	}
/*	@Override
		public void setBackgroundResource(int resid) {

			DrawUtil.drawimag(new Canvas(),DrawUtil.imag(resid, ctx),0+ImagMarg, 0+ImagMarg,TouchMe.this.getWidth()-ImagMarg, TouchMe.this.getHeight()-ImagMarg, new Paint());
			
		}*/
	@Override
	protected void onDraw(Canvas canvas) {

		//TouchMe.this.setBackgroundDrawable(ctx.getResources().getDrawable(TODraw));
		//if(haveNumber)DrawUtil.drawimag(canvas, DrawUtil.imag(Number, ctx),TouchMe.this.getWidth()/2-10, TouchMe.this.getHeight()/2-10,20, 20, new Paint());
		if(haveNumber)WidgetUtil.drawimag(canvas, WidgetUtil.imag(Number, ctx),TouchMe.this.getWidth()-24, 4,20, 20, new Paint());
		
		super.onDraw(canvas);
		if(effect){
			WidgetUtil.drawimag(canvas, bmp,0, 0,TouchMe.this.getWidth(), TouchMe.this.getHeight(), new Paint());
		}
		
	}
	void init(){
		bmp=WidgetUtil.imag(R.drawable.efct, ctx);
	}
	public void SetNumber(int num){
		if(num!=0)
		haveNumber=true;else haveNumber=false;
		Number=WidgetUtil.NumberToDrawable(num);
		this.invalidate();
	
	}
	public void addListener(){
	this.setOnTouchListener(new OnTouchListener(){
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			
			//if(arg1.getAction())
			
				switch(arg1.getAction()){
				case MotionEvent.ACTION_DOWN:
					OnTouchMode();
					break;
				
				case MotionEvent.ACTION_UP:
					OnRelease();
					break;
				/*case MotionEvent.ACTION_SCROLL:
					OnRelease();
					break;*/
				case MotionEvent.ACTION_CANCEL:
					OnRelease();
					break;
				}
				invalidate();
			return false;
		}});
	}
	public void OnTouchMode(){
		effect=true;
		
	}
	public void OnRelease(){
		//Log.i("OnRelease","activate");
		effect=false;
		
	}

}
