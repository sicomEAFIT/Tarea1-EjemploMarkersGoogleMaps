package com.example.ejemplogooglemaps;


import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
//import android.R;
import com.example.ejemplogooglemaps.R;
//import android.app.Activity;
//import android.view.View;

/**
*Clase principal.
*/
public class MainActivity extends FragmentActivity implements OnMapClickListener, 
																		OnMarkerClickListener { 
	
	private GoogleMap sicomMap;
	private final LatLng UniEafit = new LatLng(6.200635,-75.578433);  
											//lat y long found in GoogleMaps
	private final LatLng Biblioteca = new LatLng(6.201176,-75.578438);  
	private final LatLng Rectoria = new LatLng(6.199432,-75.578919);  
	private final LatLng Fundadores = new LatLng(6.197963,-75.579557);  
	private final LatLng BloIngenierias = new LatLng(6.200395,-75.578951); 
	
	//eafit area                               //y          x
	private final LatLng tLCorner = new LatLng(6.203500,-75.580197); 
	private final LatLng bLCorner = new LatLng(6.196832,-75.580197);    
	private final LatLng bRCorner = new LatLng(6.196832,-75.577057);
	private final LatLng tRCorner = new LatLng(6.203500,-75.577057);
	ArrayList<String> mainMarkersList = new ArrayList<String>();
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sicomMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
        			.getMap();
        sicomMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        sicomMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UniEafit, 17)); //range: 2-21
        																   //continent-street
        Polygon eafitPolygon = sicomMap.addPolygon(new PolygonOptions().add(tLCorner, bLCorner,
        						bRCorner, tRCorner)
        						.strokeColor(Color.RED));
        sicomMap.setMyLocationEnabled(true);                                
        sicomMap.getUiSettings().setZoomControlsEnabled(false);
        sicomMap.getUiSettings().setCompassEnabled(true);
        //sicomMap.getUiSettings().setZoomGesturesEnabled(false);
        addMainMarkers();   //pre-defined markers
        sicomMap.setOnMapClickListener(this);   
        sicomMap.setOnMarkerClickListener(this);
    }
    
  
    public void addMainMarkers(){
    	Marker mkUniEafit = sicomMap.addMarker(new MarkerOptions()
        .position(UniEafit)
        .title(getString(R.string.UEaf))  //title of the marker
        .snippet(getString(R.string.UEafAbi))  //popUp when pressing it
        .icon(BitmapDescriptorFactory
               .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        .anchor(0.5f, 0.5f));  //point within the marker that correspond to the
    								//exact position we want to show
    	listMainMarkers(mkUniEafit.getId());
    	
    	Marker mkBiblioteca = sicomMap.addMarker(new MarkerOptions()
        .position(Biblioteca)
        .title(getString(R.string.Bibl))  //getString converts that int to String
        .snippet(getString(R.string.BiblLEV)) 
        .icon(BitmapDescriptorFactory
               .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        .anchor(0.5f, 0.5f));  
    	listMainMarkers(mkBiblioteca.getId());
    	
    	Marker mkRectoria = sicomMap.addMarker(new MarkerOptions()
        .position(Rectoria)
        .title(getString(R.string.Rect))  
        .snippet(getString(R.string.RectoBloque))
        .icon(BitmapDescriptorFactory
               .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        .anchor(0.5f, 0.5f));
    	listMainMarkers(mkRectoria.getId());
    	
    	Marker mkFundadores = sicomMap.addMarker(new MarkerOptions()
        .position(Fundadores)
        .title(getString(R.string.Fund))  
        .snippet(getString(R.string.AudFundad))
        .icon(BitmapDescriptorFactory
               .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        .anchor(0.5f, 0.5f));
    	listMainMarkers(mkFundadores.getId());
    	
    	Marker mkBloIngenierias = sicomMap.addMarker(new MarkerOptions()
        .position(BloIngenierias)
        .title(getString(R.string.Ing))  
        .snippet(getString(R.string.BloqueInge))
        .icon(BitmapDescriptorFactory
               .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        .anchor(0.5f, 0.5f));
    	listMainMarkers(mkBloIngenierias.getId());
    }                           
    
    
    @Override
    public void onMapClick(LatLng pressedPoint) {
       sicomMap.addMarker(new MarkerOptions().position(pressedPoint).
          icon(BitmapDescriptorFactory
             .fromResource(R.drawable.icon)));
    }
    
    @Override
    public boolean onMarkerClick(final Marker delMarker){
    	boolean isFixedMarker = false;
    	//System.out.println("jeje");
    	for(int i=0; i<mainMarkersList.size() && !isFixedMarker; i++){
	    	if(delMarker.getId().equals(mainMarkersList.get(i))){
	    	   	delMarker.showInfoWindow();
	    		isFixedMarker = true;
	    	}
    	}
    	if(!isFixedMarker){
    		delMarker.remove();  //removes the marker by pressing it
    	}
    	//delMarker.remove();
    	return true;
    }
    
    
    public void listMainMarkers(String fixedMarker){
    	mainMarkersList.add(fixedMarker);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
