package com.qiufeng.Color;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

public class ColorSeekBar extends View
 {
    public ColorSeekBar(Context context) {
        super(context);
        initView();
    }

    public ColorSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    Paint paint = new Paint();
	Paint step=new Paint();
	boolean inited=false;
    LinearGradient lg;
    void initView() {
		step.setColor(color);
    }
	public void setThumbColor(int col){
		color=col;
		step.setColor(col);
	}
	public double progress=0;
	public double max=0;
	public int color=Color.WHITE;
	Bitmap bmp;
	Canvas cv;
	public Bitmap getBitmap(){
		return bmp;
	}
	public void setMax(int m){
		max=m;
		postInvalidate();
	}
	public void setProgress(int p){
		progress=p;
		postInvalidate();
	}
	public double getMax(){
		return max;
	}
	public double getProgress(){
		return progress;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method
		if(event.getY()>getHeight()||event.getY()<0)return false;
		if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){
		  progress=event.getY()/(getHeight()/max);
		  postInvalidate();
		  if(onProgressChangedListener!=null)onProgressChangedListener.onProgressChanged(progress);
		}
		return true;
	}
	public int[] colors= ColorStyle.DEFAULT;
	public void setColors(int[] cols){
		colors=cols;
		inited=false;
		postInvalidate();
	}
	public interface OnProgressChangedListener{
		public void onProgressChanged(double progress);
	}
	public OnProgressChangedListener onProgressChangedListener;
	public void setOnProgressChangedListener(OnProgressChangedListener l){
		onProgressChangedListener=l;
	}
	public int getCurrentColor(){
		if((int)progress*getHeight()/max>=getHeight()||(int)progress*getHeight()/max<0)return 0;
		return bmp.getPixel(getWidth()/2,(int)Math.round((int)progress*getHeight()/max));
	}
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
		if(!inited){
			lg=new LinearGradient(0,0,0, getHeight(),colors, null,Shader.TileMode.CLAMP);
			paint.setShader(lg);
			bmp=Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
			cv=new Canvas(bmp);
			cv.drawRect(getWidth()>21?10:0,0,getWidth()>21?getWidth()-10:getWidth(),getHeight(),paint);
			inited=true;
		}
		canvas.drawRect(getWidth()>21?10:0,0,getWidth()>21?getWidth()-10:getWidth(),getHeight(),paint);
		canvas.drawRoundRect(0,(int)(progress*getHeight()/max)-20,getWidth(),(int)(progress*getHeight()/max)+20,10,10,step);
    }
}
