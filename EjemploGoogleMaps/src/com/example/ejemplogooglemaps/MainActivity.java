package com.example.ejemplogooglemaps;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
//import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements OnMapClickListener { 
	
	private GoogleMap sicomMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sicomMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        sicomMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        sicomMap.getUiSettings().setZoomControlsEnabled(false);
        sicomMap.getUiSettings().setCompassEnabled(true);
        sicomMap.setOnMapClickListener(this);
    }
    
    
    @Override
    public void onMapClick(LatLng pressedPoint) {
       sicomMap.addMarker(new MarkerOptions().position(pressedPoint).
          icon(BitmapDescriptorFactory
             .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
