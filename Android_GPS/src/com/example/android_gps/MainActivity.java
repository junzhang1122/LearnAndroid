package com.example.android_gps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView textView;
    Location location;
    Button button;
    LocationManager lmger;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //定义UI组件
        textView = (TextView)this.findViewById(R.id.textView1);
        button = (Button)this.findViewById(R.id.button1);

        //获取LocationManager服务
        lmger  = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        
       //打开GPS
        openGPSSettings();
        
        //定义Criteria对象
        Criteria criteria = new Criteria();
        
        //设置定位精确度 criteria.ACCURACY_COARSE(比较粗略) criteria.ACCURACY_FINE(比较精细)
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        
        //设置是否需要海拔信息 altitude
        criteria.setAltitudeRequired(true);
        
        //设置是否需要方位信息 bearing
        criteria.setBearingRequired(true);
        
        //设置是否运营商收费
        criteria.setCostAllowed(true);
        
        //设置对电源的需求
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        
        //获取GPS的提供者
        String provider = lmger.getBestProvider(criteria, true);
        
        //获取定位信息
        location = lmger.getLastKnownLocation(provider);	
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				updateLocation(location);
			}
		});
        
        lmger.requestLocationUpdates(provider, 500, 0, locationListener);
    }

	private void openGPSSettings() {
		// TODO Auto-generated method stub
		if(lmger.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)){
			Toast.makeText(MainActivity.this, "GPS is opened", 1).show();
			return ;
		}
		Toast.makeText(MainActivity.this, "Please open GPS", 1).show();
		Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivityForResult(intent, 0);
	}

	LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			updateLocation(location);
		}
		
		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public void updateLocation(Location location){
		if(location != null){
				textView.setText("精度："+location.getAltitude()+"\n" 
						+"维度："+location.getLatitude());
		}
		else
			Log.i("updateLocation", "updateLocation faill");
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
