package com.qiufeng.colorui;

import android.app.*;
import android.os.*;
import com.qiufeng.Color.*;
import android.graphics.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		initViews();
		
    }
	public void initViews(){
		initCSB();
	}
	public void initCSB(){
		ColorSeekBar csb=(ColorSeekBar)findViewById(R.id.csb);
		csb.setMax(255);
		csb.setProgress(0);
		csb.setThumbColor(Color.CYAN);
		ColorSeekBar csb2=(ColorSeekBar)findViewById(R.id.csb2);
		csb2.setMax(255);
		csb2.setProgress(0);
		csb2.setThumbColor(Color.CYAN);
		csb2.setColors(ColorStyle.ALPHA);
		Button mainNext=(Button)findViewById(R.id.mainNext);
		mainNext.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				setContentView(R.layout.cc);
				initCC();
			}
		});
	}
	public void initCC(){
		ColorChooser cc=(ColorChooser)findViewById(R.id.cc);
		ColorChooser cc2=(ColorChooser)findViewById(R.id.cc2);
		cc2.setColors(ColorStyle.ALPHA);
		cc.setColors(ColorStyle.DEFAULT);
		Button ccNext=(Button)findViewById(R.id.ccNext);
		ccNext.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				setContentView(R.layout.cpv);
			}
		});
	}
	public void initCPV(){
		
	}
}
