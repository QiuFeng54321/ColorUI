package com.qiufeng.Color;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class ColorPickerView extends LinearLayout
{
    public ColorPickerView(Context context) {
        super(context);
		initViews(context);
    }

    public ColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
		initViews(context);
    }
	LinearLayout cl;
    ColorSeekBar colorSeekBar;
	ColorSeekBar alphaSeekBar;
	ColorChooser colorPalette;
	int fcol;
	int scol;
	void initViews(Context cx){
		cl=new LinearLayout(cx);
		colorSeekBar=new ColorSeekBar(cx);
		alphaSeekBar=new ColorSeekBar(cx);
		colorPalette=new ColorChooser(cx);
		colorSeekBar.setThumbColor(Color.CYAN);
		alphaSeekBar.setThumbColor(Color.CYAN);
		setGravity(Gravity.CENTER);
		setLeft(20);
		setRight(20);
		setTop(20);
		setBottom(20);
		cl.setGravity(Gravity.CENTER);
		addView(cl);
		cl.addView(colorSeekBar,new ViewGroup.LayoutParams(75,500));
		cl.addView(alphaSeekBar,new ViewGroup.LayoutParams(75,500));
		alphaSeekBar.setColors(ColorStyle.ALPHA);
		addView(colorPalette,new ViewGroup.LayoutParams(500,500));
		colorSeekBar.setMax(255);
		colorSeekBar.setProgress(128);
		scol=colorSeekBar.getCurrentColor();
		alphaSeekBar.setMax(255);
		alphaSeekBar.setProgress(128);
		fcol=alphaSeekBar.getCurrentColor();
		colorPalette.setColors(new int[]{fcol,scol});
		colorSeekBar.setOnProgressChangedListener(new ColorSeekBar.OnProgressChangedListener(){
			public void onProgressChanged(double progress){
				scol=colorSeekBar.getCurrentColor();
				colorPalette.setColors(new int[]{fcol,scol});
			}
		});
		alphaSeekBar.setOnProgressChangedListener(new ColorSeekBar.OnProgressChangedListener(){
			public void onProgressChanged(double progress){
				fcol=alphaSeekBar.getCurrentColor();
				colorPalette.setColors(new int[]{fcol,scol});
			}
		});
	}
	public ColorSeekBar getColorSeekBar(){
		return colorSeekBar;
	}
	public ColorSeekBar getAlphaSeekBar(){
		return alphaSeekBar;
	}
	public ColorChooser getColorPalette(){
		return colorPalette;
	}
	public LinearLayout getColorLayout(){
		return cl;
	}
	public LinearLayout getMainLayout(){
		return this;
	}
}
