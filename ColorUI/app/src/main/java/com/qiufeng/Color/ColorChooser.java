package com.qiufeng.Color;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

public class ColorChooser extends View
{
    public ColorChooser(Context context) {
        super(context);
        initView();
    }

    public ColorChooser(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    Paint paint = new Paint();
	Paint point=new Paint();
	boolean inited=false;
	boolean n=false;
    LinearGradient lg;
	float[] position;
    void initView() {
		point.setStyle(Paint.Style.STROKE);
		point.setStrokeWidth(5);
		point.setAntiAlias(true);
		point.setDither(true);
		point.setColor(color);
    }
	public void setPointColor(int col){
		color=col;
		point.setColor(col);
	}
	public int color=Color.WHITE;
	Bitmap bmp;
	Canvas cv;
	public Bitmap getBitmap(){
		return bmp;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method
		//if(event.getX()>=getWidth()||event.getX()<0||event.getY()>=getHeight()||event.getY()<0)return false;
		float x=event.getX();
		float y=event.getY();
		if(x>=getWidth())x=getWidth()-1;
		if(x<0)x=0;
		if(y>=getHeight())y=getHeight()-1;
		if(y<0)y=0;
		position[0]=x;
		position[1]=y;
		if(onChooseChangedListener!=null)onChooseChangedListener.onChooseChanged(position);
		postInvalidate();
		return true;
	}
	public int[] colors= ColorStyle.ALPHA;
	public void setColors(int[] cols){
		colors=cols;
		inited=false;
		postInvalidate();
	}
	public interface OnChooseChangedListener{
		public void onChooseChanged(float[] progress);
	}
	public OnChooseChangedListener onChooseChangedListener;
	public void setOnProgressChangedListener(OnChooseChangedListener l){
		onChooseChangedListener=l;
	}
	public int getCurrentColor(){
		return bmp.getPixel(Math.round(position[0]),Math.round(position[1]));
	}
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
		if(!inited){
			if(!n){
				position=new float[]{getWidth()/2,getHeight()/2};
				n=true;
			}
			lg=new LinearGradient(0,0,getWidth(), getHeight(),colors, null,Shader.TileMode.CLAMP);
			paint.setShader(lg);
			bmp=Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
			cv=new Canvas(bmp);
			cv.drawRect(0,0,getWidth(),getHeight(),paint);
			inited=true;
		}
		canvas.drawRect(0,0,getWidth(),getHeight(),paint);
		canvas.drawCircle(position[0],position[1],20,point);
    }
}
