package cityu.fyp.planner4trip;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class sightspot_picker extends Activity {
	
	int select_item = 0;
	List<String> category = new ArrayList<String>();
	final List<String> cate = new ArrayList<String>();
	final List<String> label = new ArrayList<String>();
	final List<String> time = new ArrayList<String>();
	final List<String> description = new ArrayList<String>();
	final List<String> posList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sightspot_picker);
		/*
		Intent intent = getIntent();
		String city = intent.getStringExtra("city");
		Bundle extras = getIntent().getExtras();
		int day = extras.getInt("day");
*/
		int length = 0;
		Spinner cat_spinner = (Spinner) findViewById(R.id.cat_spinner);
		final Spinner label_spinner = (Spinner) findViewById(R.id.label_spinner);
		
		final TextView des_textview = (TextView) findViewById(R.id.des_textView2);
		final TextView time_textview = (TextView) findViewById(R.id.time_textView2);
		final Button Mapview = (Button) findViewById(R.id.loc_button);
		final Button OK = (Button) findViewById(R.id.button);
		
		JSONObject jsonObject;
		/*List<String> category = new ArrayList<String>();
		final List<String> cate = new ArrayList<String>();
		final List<String> label = new ArrayList<String>();
		final List<String> time = new ArrayList<String>();
		final List<String> description = new ArrayList<String>();
		final List<String> posList = new ArrayList<String>();*/
		
        try {
            jsonObject = new JSONObject(readFile("Amsterdam.json", this));
        } catch (JSONException je) {
            je.printStackTrace();
            jsonObject = null;
        }

        if (jsonObject != null) {
        	try {
				JSONArray poi = jsonObject.getJSONArray("poi");
				length = poi.length();  
				for(int i = 0; i < length; i++){ 
					//Log.d("length",Integer.toString(i));
		                JSONObject oj = poi.getJSONObject(i); 
		                
		                try{
		                	JSONArray CateArray = oj.getJSONArray("category");
		                	JSONObject cat = CateArray.getJSONObject(0); 
		                	String[] temp_cat = cat.getString("value").split("VenueType");
		                	cate.add(temp_cat[1]);
		                	if(!category.contains(temp_cat[1]))
		                		category.add(temp_cat[1]);
		                } catch (JSONException e) {
		                	cate.add(" ");
		                }
		                
		                try{      
		                	JSONArray labelArray = oj.getJSONArray("label");
		                	JSONObject name = labelArray.getJSONObject(0);     
		                	label.add(name.getString("value"));
		                }catch (JSONException e) {
		                	label.add(" ");
		                }
		                
		                try{      
		                	JSONArray descriptionArray = oj.getJSONArray("description");
		                	JSONObject des = descriptionArray.getJSONObject(1);      
		                	description.add(des.getString("value"));
		                }catch (JSONException e) {
		                	description.add(" ");
		                }
		                
		                try{      
		                	JSONArray timeArray = oj.getJSONArray("time");
		                	JSONObject t = timeArray.getJSONObject(0);
		                	time.add(t.getString("value"));
		                }catch (JSONException e) {
		                	time.add(" ");
		                }
		                
		                try{      
		                	JSONObject loc = oj.getJSONObject("location");    
		                	 try{      
				                	JSONArray pointArray = loc.getJSONArray("point");
				                	JSONObject pt = pointArray.getJSONObject(0);      
				                	JSONObject p = pt.getJSONObject("Point");  
				                	posList.add(p.getString("posList"));
				                }catch (JSONException e) {
				                	e.printStackTrace();
				                	posList.add(" ");
				                }    
		                }catch (JSONException e) {
		                	posList.add(" ");
		                }
		        }  
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("jsonPOI", "Catch");
			}
        	
        } else {
            Log.d("Json2View", "Could not load valid json file");
        }
        
        ArrayAdapter<String> category_list = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, category);
        cat_spinner.setAdapter(category_list);
        cat_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
		        // TODO Auto-generated method stub
		        Object item = arg0.getItemAtPosition(arg2);
		        if (item!=null && !item.toString().equals("") ) {
		        	List<String> temp = new ArrayList<String>();
		        	for(int i = 0 ; i < cate.size() ; i++){
		        		//List<String> temp = new ArrayList<String>();
		        		if(cate.get(i).equals(item.toString()))
		        			temp.add(label.get(i));
		        	}
		        	ArrayAdapter<String> label_list = new ArrayAdapter<String>(sightspot_picker.this,android.R.layout.simple_spinner_item, temp);
		    		label_spinner.setAdapter(label_list);
		        }
		    }
		
		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		        // TODO Auto-generated method stub
		    }
		});
        
        label_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
		        // TODO Auto-generated method stub
		        Object item = arg0.getItemAtPosition(arg2);
		        if (item!=null && !item.toString().equals("") ) {
		        	for(int i = 0 ; i < description.size() ; i++){
		        		if(label.get(i).equals(item.toString())){
		        			select_item = i;
		        			if(description.get(i) != null && description.get(i) != " ")
		        				des_textview.setText(Html.fromHtml(description.get(i)));
		        			else
		        				des_textview.setText("N/A");
		        			if(time.get(i) != null && time.get(i) != " ")
		        				time_textview.setText(Html.fromHtml(time.get(i)));
		        			else
		        				time_textview.setText("N/A");
		        			
		        			String temp_pos = "52.22 4.53";
		        			if(posList.get(i) != null && posList.get(i) != " ")
		        				temp_pos = posList.get(i);
		        
		        			final String[] temp = temp_pos.split(" ");

		        			Mapview.setOnClickListener(new Button.OnClickListener(){ 
		        		            @Override
		        		            public void onClick(View v) {
		        		            	Intent intent = new Intent();
		        		                intent.setClass(sightspot_picker.this, GoogleMapView.class);
		        		            	intent.putExtra("latitude", temp[0]);
		        		            	intent.putExtra("longitude", temp[1]);
		        		                startActivity(intent);
		        		            }         
		        		        }); 
		        		}
		        	}
		        }
		    }
		
		    @Override
		    public void onNothingSelected(AdapterView<?> arg0) {
		        // TODO Auto-generated method stub
		    }
		});
        
        OK.setOnClickListener(new Button.OnClickListener(){ 
            @Override
            public void onClick(View v) {
            	  Intent intentMessage = new Intent();
            	  intentMessage.putExtra("label",label.get(select_item));
            	  intentMessage.putExtra("description",description.get(select_item));
            	  intentMessage.putExtra("posList",posList.get(select_item));
                  setResult(Activity.RESULT_OK,intentMessage);
                  finish();
            }         
        }); 
	}
	
	/**
     * Helper function to load file from assets
     */
    private String readFile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets().open(fileName);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line;
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null) isr.close();
                if (fIn != null) fIn.close();
                if (input != null) input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }
}