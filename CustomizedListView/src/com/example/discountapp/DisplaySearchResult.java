package com.example.discountapp;

import java.io.InputStream;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplaySearchResult extends Activity {
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb=null;
		
	public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
		
	   Intent intent 		= getIntent();
	   String name			= intent.getStringExtra(DiscountApp.KEY_NAME);
	   String namedesc		= intent.getStringExtra(DiscountApp.KEY_NAME_DESC);
	   String promodesc		= intent.getStringExtra(DiscountApp.KEY_PROMO_DESC);

 	   TextView textview	= new TextView(this);
 	   textview.setTextSize(40);
 	   textview.setText(name + " " + namedesc + " " + promodesc);
 	    	   
 	   setContentView(textview);       		   
	}
}

/* WEB - DATABASE CONNECTION
	   String ct_id		= "default";
       String ct_name	= "default";
       String ct_pass	= "default";
				
       ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
       nameValuePairs.add(new BasicNameValuePair("username","fiona"));
              
       //http post
       try{
            HttpClient httpclient = new DefaultHttpClient();
//	            HttpPost httppost = new HttpPost("http://10.0.2.2/city.php");
            HttpPost httppost = new HttpPost("http://discount.net63.net/");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));            
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }
       	catch(Exception e){
                Log.e("log_tag", "Error in http connection"+e.toString());
        }
       //convert response to string
       	try{
             BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
             sb = new StringBuilder();
             sb.append(reader.readLine() + "\n");

             String line="0";
             while ((line = reader.readLine()) != null) {
                             sb.append(line + "\n");
             }
             is.close();
             result=sb.toString();
        }catch(Exception e){
             Log.e("log_tag", "Error converting result "+e.toString());
        }
       	//paring data
       	try{
             jArray = new JSONArray(result);
             JSONObject json_data=null;
             for(int i=0;i<jArray.length();i++){
                    json_data = jArray.getJSONObject(i);
                    ct_id=json_data.getString("idPromo");                    
                    ct_name=json_data.getString("name");
                    ct_pass=json_data.getString("nameDesc");                    
                }
             }
             catch(JSONException e1){
           	  Toast.makeText(getBaseContext(), "No City Found" ,Toast.LENGTH_LONG).show();
             } catch (ParseException e1) {
       			e1.printStackTrace();
       	}

 	   TextView textview	= new TextView(this);
 	   textview.setTextSize(40);
 	   textview.setText(query + " " + ct_id + " " + ct_name + " " + ct_pass);
 	    	   
 	   setContentView(textview);       	

*/
