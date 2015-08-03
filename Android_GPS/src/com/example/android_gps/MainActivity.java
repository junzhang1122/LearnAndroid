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
        
        //����UI���
        textView = (TextView)this.findViewById(R.id.textView1);
        button = (Button)this.findViewById(R.id.button1);

        //��ȡLocationManager����
        lmger  = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        
       //��GPS
        openGPSSettings();
        
        //����Criteria����
        Criteria criteria = new Criteria();
        
        //���ö�λ��ȷ�� criteria.ACCURACY_COARSE(�Ƚϴ���) criteria.ACCURACY_FINE(�ȽϾ�ϸ)
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        
        //�����Ƿ���Ҫ������Ϣ altitude
        criteria.setAltitudeRequired(true);
        
        //�����Ƿ���Ҫ��λ��Ϣ bearing
        criteria.setBearingRequired(true);
        
        //�����Ƿ���Ӫ���շ�
        criteria.setCostAllowed(true);
        
        //���öԵ�Դ������
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        
        //��ȡGPS���ṩ��
        String provider = lmger.getBestProvider(criteria, true);
        
        //��ȡ��λ��Ϣ
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
				textView.setText("���ȣ�"+location.getAltitude()+"\n" 
						+"ά�ȣ�"+location.getLatitude());
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
