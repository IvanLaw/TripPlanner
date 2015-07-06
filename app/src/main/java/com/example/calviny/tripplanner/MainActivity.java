package com.example.calviny.tripplanner;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnItemSelectedListener {
	
	int day_selected = 0;
	String city_selected = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Spinner spinner_city = (Spinner) findViewById(R.id.spinner_city);
		Spinner spinner_day = (Spinner) findViewById(R.id.spinner_day);
		
		String[] city = {"","Amsterdam"};
		String[] day = {"","1", "2"};
		
		ArrayAdapter<String> city_list = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, city);
		ArrayAdapter<String> day_list = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, day);
		
		spinner_city.setAdapter(city_list);
		spinner_day.setAdapter(day_list);
		
		spinner_city.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
		        // TODO Auto-generated method stub
		        Object item = arg0.getItemAtPosition(arg2);
		        if (item!=null && !item.toString().equals("") ) {
		        	city_selected = item.toString();
		        	if(day_selected != 0){
		        		Intent intent = new Intent();
		                intent.setClass(MainActivity.this, sightspot.class);
		            	intent.putExtra("city", city_selected);
		            	intent.putExtra("day", day_selected);
		                startActivity(intent);
		                //finish();
		        	}
		        }
		    }
		
		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		        // TODO Auto-generated method stub
		    }
		});
		
		spinner_day.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
		        // TODO Auto-generated method stub
		        Object item = arg0.getItemAtPosition(arg2);
		        if (item!=null && !item.toString().equals("") ) {
		        	day_selected = Integer.parseInt(item.toString());
		        	if(city_selected != null){
		        		Intent intent = new Intent();
		                intent.setClass(MainActivity.this, sightspot.class);
		            	intent.putExtra("city", city_selected);
		            	intent.putExtra("day", day_selected);
		                startActivity(intent);
		                //finish();
		        	}
		        }
		    }
		
		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		        // TODO Auto-generated method stub
		    }
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
