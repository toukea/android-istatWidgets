package istat.android.widget.view;


import istat.android.widget.util.WidgetUtil;
import istat.android.widget.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class ImageTouchMe extends  ImageButton{
Context ctx;
public boolean effect,haveNumber=false;
Bitmap bmp;
int ImagMarg=20;
int Number=0;
public ImageTouchMe(Context context) {
	super(context);	
	addListener();
	ctx=context;
	init();
	// TODO Auto-generated constructor stub
}
	public ImageTouchMe(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		addListener();
		ctx=context;
		init();
		// TODO Auto-generated constructor stub
	}
	public ImageTouchMe(Context context, AttributeSet attrs) {
		super(context, attrs);
		addListener();
		ctx=context;
		init();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(haveNumber)WidgetUtil.drawimag(canvas, WidgetUtil.imag(Number, ctx),ImageTouchMe.this.getWidth()-24, 4,20, 20, new Paint());
		
	
		if(effect){
			WidgetUtil.drawimag(canvas, bmp,0, 0,ImageTouchMe.this.getWidth(), ImageTouchMe.this.getHeight(), new Paint());
		}
		
	}
	void init(){
		bmp=WidgetUtil.imag(R.drawable.efct, ctx);
	}
	public void SetNumber(int num){
		if(num!=0)
		haveNumber=true;else haveNumber=false;
		Number=WidgetUtil.NumberToDrawable(num);
	
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
