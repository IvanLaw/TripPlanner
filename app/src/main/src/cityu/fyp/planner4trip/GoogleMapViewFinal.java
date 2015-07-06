package cityu.fyp.planner4trip;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
 
public class GoogleMapViewFinal extends FragmentActivity{
 
    GoogleMap googleMap;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);
        
    	Intent intent = getIntent();
    	String day = intent.getStringExtra("day");
		String m = intent.getStringExtra("m");
		String a = intent.getStringExtra("a");
		String n = intent.getStringExtra("n");
		String m2 = null;
		String a2 = null;
		String n2 = null;
		if(day.equals("2")){
			m2 = intent.getStringExtra("m2");
			a2 = intent.getStringExtra("a2");
			n2 = intent.getStringExtra("n2");
		}

        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
 
        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
 
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
 
        }else { // Google Play Services are available
        	// pan to see all markers on map:
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            googleMap = fm.getMap();
            googleMap.setMyLocationEnabled(true);

            com.google.android.gms.maps.model.LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
            
            double lat = Double.parseDouble(m.split(" ")[0]);
            double lng = Double.parseDouble(m.split(" ")[1]);
            LatLng latLng = new LatLng(lat, lng);
            boundsBuilder.include(latLng);
            googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))).setVisible(true);
            
            lat = Double.parseDouble(a.split(" ")[0]);
            lng = Double.parseDouble(a.split(" ")[1]);
            latLng = new LatLng(lat, lng);
            boundsBuilder.include(latLng);
            googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))).setVisible(true);
            
            lat = Double.parseDouble(n.split(" ")[0]);
            lng = Double.parseDouble(n.split(" ")[1]);
            latLng = new LatLng(lat, lng);
            boundsBuilder.include(latLng);
            googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))).setVisible(true);
            
            googleMap.addPolyline(new PolylineOptions()
            .add(new LatLng(Double.parseDouble(m.split(" ")[0]), Double.parseDouble(m.split(" ")[1])), new LatLng(Double.parseDouble(a.split(" ")[0]), Double.parseDouble(a.split(" ")[1])), new LatLng(Double.parseDouble(n.split(" ")[0]), Double.parseDouble(n.split(" ")[1])))
            .width(5)
            .color(Color.BLUE));

            
            if(day.equals("2")){
            	 lat = Double.parseDouble(m2.split(" ")[0]);
                 lng = Double.parseDouble(m2.split(" ")[1]);
                 latLng = new LatLng(lat, lng);
                 boundsBuilder.include(latLng);
                 googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN ))).setVisible(true);
                 
                 lat = Double.parseDouble(a2.split(" ")[0]);
                 lng = Double.parseDouble(a2.split(" ")[1]);
                 latLng = new LatLng(lat, lng);
                 boundsBuilder.include(latLng);
                 googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN ))).setVisible(true);
                 
                 lat = Double.parseDouble(n2.split(" ")[0]);
                 lng = Double.parseDouble(n2.split(" ")[1]);
                 latLng = new LatLng(lat, lng);
                 boundsBuilder.include(latLng);
                 googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN ))).setVisible(true);
                 
                 googleMap.addPolyline(new PolylineOptions()
                 .add(new LatLng(Double.parseDouble(m2.split(" ")[0]), Double.parseDouble(m2.split(" ")[1])), new LatLng(Double.parseDouble(a2.split(" ")[0]), Double.parseDouble(a2.split(" ")[1])), new LatLng(Double.parseDouble(n2.split(" ")[0]), Double.parseDouble(n2.split(" ")[1])))
                 .width(5)
                 .color(Color.GREEN));
    		}
            
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            
            LatLngBounds bounds = boundsBuilder.build();          
           // googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 3));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        }
    }
    
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}