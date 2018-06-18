package com.qiufeng.Color;
import android.content.*;
import android.util.*;

public class ColorMixerView extends ColorPickerView
{
	public ColorMixerView(Context cx){
		super(cx);
		alphaSeekBar.setColors(ColorStyle.DEFAULT);
	}
	public ColorMixerView(Context context, AttributeSet attrs) {
        super(context, attrs);
		alphaSeekBar.setColors(ColorStyle.DEFAULT);
    }
}
