package cityu.fyp.planner4trip;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class sightspot extends Activity {
	
	int day;
	List<String> m = new ArrayList<String>();
	List<String> a = new ArrayList<String>();
	List<String> n = new ArrayList<String>();
	List<String> m2 = new ArrayList<String>();
	List<String> a2 = new ArrayList<String>();
	List<String> n2 = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		String city = intent.getStringExtra("city");
		Bundle extras = getIntent().getExtras();
		day = extras.getInt("day");
		
		ImageButton m2_search = null;
		ImageButton a2_search = null;
		ImageButton n2_search = null;
		
		if(day == 1)
			setContentView(R.layout.activity_sightspot_1);
		else{
			setContentView(R.layout.activity_sightspot_2);
			m2_search = (ImageButton) findViewById(R.id.m2_searchView);
			a2_search = (ImageButton) findViewById(R.id.a2_searchView);
			n2_search = (ImageButton) findViewById(R.id.n2_searchView);
			m2_search.setOnClickListener(new Button.OnClickListener(){ 
	            @Override
	            public void onClick(View v) {
	            	m2 = new ArrayList<String>();
	            	Intent intent = new Intent();
	                intent.setClass(sightspot.this, sightspot_picker.class);
	                startActivityForResult(intent, 4);
	            }         
	        }); 
			a2_search.setOnClickListener(new Button.OnClickListener(){ 
	            @Override
	            public void onClick(View v) {
	            	a2 = new ArrayList<String>();
	            	Intent intent = new Intent();
	                intent.setClass(sightspot.this, sightspot_picker.class);
	                startActivityForResult(intent, 5);
	            }         
	        }); 
			n2_search.setOnClickListener(new Button.OnClickListener(){ 
	            @Override
	            public void onClick(View v) {
	            	n2 = new ArrayList<String>();
	            	Intent intent = new Intent();
	                intent.setClass(sightspot.this, sightspot_picker.class);
	                startActivityForResult(intent, 6);
	            	
	            }         
	        }); 
			
		}
	
		ImageButton m_search = (ImageButton) findViewById(R.id.m_searchView);
		ImageButton a_search = (ImageButton) findViewById(R.id.a_searchView);
		ImageButton n_search = (ImageButton) findViewById(R.id.n_searchView);
		
		m_search.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
            	/*
            	Intent intent = new Intent();
                intent.setClass(sightspot.this, Transit.class);
                startActivity(intent);
                */
            	
            	m = new ArrayList<String>();
            	Intent intent = new Intent();
                intent.setClass(sightspot.this, sightspot_picker.class);
                startActivityForResult(intent, 1);
                
            }         
        }); 
		a_search.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
            	a = new ArrayList<String>();
            	Intent intent = new Intent();
                intent.setClass(sightspot.this, sightspot_picker.class);
                startActivityForResult(intent, 2);
            }         
        }); 
		n_search.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
            	n = new ArrayList<String>();
            	Intent intent = new Intent();
                intent.setClass(sightspot.this, sightspot_picker.class);
                startActivityForResult(intent, 3);
            	
            }         
        }); 
	}

	 // Call Back method  to get the Message form other Activity    override the method
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		// TODO Auto-generated method stub
    	
		super.onActivityResult(requestCode, resultCode, data);
		 
		TextView m_text = (TextView) findViewById(R.id.m_textView2);
		TextView a_text = (TextView) findViewById(R.id.a_textView2);
		TextView n_text = (TextView) findViewById(R.id.n_textView2);
		TextView m2_text = null;
		TextView a2_text = null;
		TextView n2_text = null;
		Button transit3 = null;
		Button transit4 = null;
		
		if(day == 2){
			m2_text = (TextView) findViewById(R.id.m2_textView2);
			a2_text = (TextView) findViewById(R.id.a2_textView2);
			n2_text = (TextView) findViewById(R.id.n2_textView2);
			transit3 = (Button) findViewById(R.id.button_transit3);
			transit4 = (Button) findViewById(R.id.button_transit4);
		}
		
		Button transit1 = (Button) findViewById(R.id.button_transit);
		Button transit2 = (Button) findViewById(R.id.button_transit2);
		
		// check if the request code is same as what is passed  here it is 2
		if(requestCode == 1){
			m.add(data.getStringExtra("label"));
			m.add(data.getStringExtra("description"));
			m.add(data.getStringExtra("posList"));
			m_text.setText(Html.fromHtml(m.get(0) + "<br></br>" + m.get(1)));
		} else if(requestCode == 2){
			a.add(data.getStringExtra("label"));
			a.add(data.getStringExtra("description"));
			a.add(data.getStringExtra("posList"));
			a_text.setText(Html.fromHtml(a.get(0) + "<br></br>" + a.get(1)));
		} else if(requestCode == 3){
			n.add(data.getStringExtra("label"));
			n.add(data.getStringExtra("description"));
			n.add(data.getStringExtra("posList"));
			n_text.setText(Html.fromHtml(n.get(0) + "<br></br>" + n.get(1)));
		} else if(requestCode == 4){
			m2.add(data.getStringExtra("label"));
			m2.add(data.getStringExtra("description"));
			m2.add(data.getStringExtra("posList"));
			m2_text.setText(Html.fromHtml(m2.get(0) + "<br></br>" + m2.get(1)));
		} else if(requestCode == 5){
			a2.add(data.getStringExtra("label"));
			a2.add(data.getStringExtra("description"));
			a2.add(data.getStringExtra("posList"));
			a2_text.setText(Html.fromHtml(a2.get(0) + "<br></br>" + a2.get(1)));
		} else if(requestCode == 6){
			n2.add(data.getStringExtra("label"));
			n2.add(data.getStringExtra("description"));
			n2.add(data.getStringExtra("posList"));
			n2_text.setText(Html.fromHtml(n2.get(0) + "<br></br>" + n2.get(1)));
		}
		
		if(m.size() > 1 && a.size() > 1){
			transit1.setOnClickListener(new Button.OnClickListener(){ 
	            @Override
	            public void onClick(View v) {
	            	Intent intent = new Intent();
	            	intent.putExtra("from", m.get(2).replace(' ', ','));
	            	intent.putExtra("to", a.get(2).replace(' ', ','));
	                intent.setClass(sightspot.this, Transit.class);
	                startActivity(intent);
	            }         
	        }); 
		}
		
		if(n.size() > 1 && a.size() > 1){
			transit2.setOnClickListener(new Button.OnClickListener(){ 
	            @Override
	            public void onClick(View v) {
	            	Intent intent = new Intent();
	            	intent.putExtra("from", a.get(2).replace(' ', ','));
	            	intent.putExtra("to", n.get(2).replace(' ', ','));
	                intent.setClass(sightspot.this, Transit.class);
	                startActivity(intent);
	            }         
	        }); 
		}
		
		if(m2.size() > 1 && a2.size() > 1){
			transit3.setOnClickListener(new Button.OnClickListener(){ 
	            @Override
	            public void onClick(View v) {
	            	Intent intent = new Intent();
	            	intent.putExtra("from", m2.get(2).replace(' ', ','));
	            	intent.putExtra("to", a2.get(2).replace(' ', ','));
	                intent.setClass(sightspot.this, Transit.class);
	                startActivity(intent);
	            }         
	        }); 
		}
		
		if(n2.size() > 1 && a2.size() > 1){
			transit4.setOnClickListener(new Button.OnClickListener(){ 
	            @Override
	            public void onClick(View v) {
	            	Intent intent = new Intent();
	            	intent.putExtra("from", a2.get(2).replace(' ', ','));
	            	intent.putExtra("to", n2.get(2).replace(' ', ','));
	                intent.setClass(sightspot.this, Transit.class);
	                startActivity(intent);
	            }         
	        }); 
		}
		
		Button map = (Button) findViewById(R.id.button_final);
		if(day == 2){
			if(n.size() > 1 && a.size() > 1 && m.size() > 1 && n2.size() > 1 && a2.size() > 1 && m2.size() > 1){
				map.setOnClickListener(new Button.OnClickListener(){ 
		            @Override
		            public void onClick(View v) {
		            	Intent intent = new Intent();
		            	intent.putExtra("day", "2");
		            	intent.putExtra("m", m.get(2));
		            	intent.putExtra("a", a.get(2));
		            	intent.putExtra("n", n.get(2));
		            	intent.putExtra("m2", m2.get(2));
		            	intent.putExtra("a2", a2.get(2));
		            	intent.putExtra("n2", n2.get(2));
		                intent.setClass(sightspot.this, GoogleMapViewFinal.class);
		                startActivity(intent);
		            }         
		        }); 
			}
		} else if(n.size() > 1 && a.size() > 1 && m.size() > 1){
			map.setOnClickListener(new Button.OnClickListener(){ 
	            @Override
	            public void onClick(View v) {
	            	Intent intent = new Intent();
	            	intent.putExtra("day", "1");
	            	intent.putExtra("m", m.get(2));
	            	intent.putExtra("a", a.get(2));
	            	intent.putExtra("n", n.get(2));
	                intent.setClass(sightspot.this, GoogleMapViewFinal.class);
	                startActivity(intent);
	            }         
	        }); 
		}
	}
}
