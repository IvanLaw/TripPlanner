package cityu.fyp.planner4trip;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
 
public class GoogleMapView extends FragmentActivity{
 
    GoogleMap googleMap;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);
        
    	Intent intent = getIntent();
		String latitude = intent.getStringExtra("latitude");
		String longitude = intent.getStringExtra("longitude");

        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
 
        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
 
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
 
        }else { // Google Play Services are available
 
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            googleMap = fm.getMap();
            googleMap.setMyLocationEnabled(true);

            double lat = Double.parseDouble(latitude);
            double lng = Double.parseDouble(longitude);
            Log.d("lat", latitude);
            Log.d("lng", longitude);

            LatLng latLng = new LatLng(lat, lng);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.addMarker(new MarkerOptions().position(latLng)).setVisible(true);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
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