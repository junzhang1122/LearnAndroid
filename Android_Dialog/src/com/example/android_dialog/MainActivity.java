package com.example.android_dialog;

import android.R.bool;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	public AlertDialog.Builder builder;
	public Button button;
	public Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builder = new AlertDialog.Builder(MainActivity.this);
    	button = (Button)this.findViewById(R.id.button1);
    	builder.setTitle("提示框");
    	builder.setMessage("你确定要删除吗");
    	builder.setIcon(R.drawable.ic_launcher);
    	final boolean[] selected = {false, false, false, false};
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				builder.show();
			}
		});
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "确定", 1).show();
			}
		});
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "取消", 1).show();
			}
		});
        button2 = (Button)this.findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				 builder.setTitle("请选择爱好");
				 String[] str = {"打篮球", "打羽毛球", "打乒乓球", "跑步"};
				 builder.setMultiChoiceItems(str, selected, new OnMultiChoiceClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
						// TODO Auto-generated method stub
						if(arg2 == true)
							selected[arg1] = true;
					}
				});
				 builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
				});
				 builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
						}
					});
				 builder.show();
			}
		});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

