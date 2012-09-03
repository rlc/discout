package com.example.discountapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.example.discountapp.R;
import com.example.model.Promotion;
import com.example.service.PromotionHandler;
import com.example.utils.LazyAdapter;

public class DiscountApp extends Activity {
	public final static String EXTRA_MESSAGE = "Hellow";	
	static final String KEY_SONG = "song"; // parent node
	static final String KEY_ID = "id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_ARTIST = "artist";
	public static final String KEY_DURATION = "duration";
	public static final String KEY_THUMB_URL = "thumb_url";
	
	ListView list;
    LazyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);	// display search textbox and button
		displayList();					// display list of promotion information
		
	}	

    public void searchPromotion (View view) {
    	Intent intent 		= new Intent(this, DisplaySearchResult.class);    	
    	EditText editText	= (EditText) findViewById(R.id.search_message);
    	String message		= editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);    	
    	startActivity(intent);
    }	
	
	public void displayList() {
		ArrayList<HashMap<String, String>> songsList = getList();

		list=(ListView)findViewById(R.id.list);		
        adapter=new LazyAdapter(this, songsList);        
        list.setAdapter(adapter);
        
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// GO TO DetailResult.java

			}
		});				
	}
	
	public ArrayList<HashMap<String, String>> getList() {
		ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

		//	ADD INITIAL DATABASE CONTENT
		 
			PromotionHandler dbpromo = new PromotionHandler(this);
	        Promotion newPromo	= new Promotion
	    	(
	    		1, 2, 1, "Koiki", "Japanese Restaurant", "50% all food", "2012-08-30", "2012-12-31", 
	    		"Plaza Indonesia Ground Floor", "1111000", "http://www.adaapaya.com/userdata/real/7bbb1b10567cca5d6b130019c94dfc37.jpg"
	    	);
	        dbpromo.addPromotion(newPromo);        
	
	        newPromo	= new Promotion
	    	(
	    		2, 2, 1, "Zenbu", "Japanese Restaurant", "50% all food", "2012-08-30", "2012-12-31", 
	    		"Plaza Indonesia 5th Floor", "1111000", "http://www.adaapaya.com/userdata/real/7bbb1b10567cca5d6b130019c94dfc37.jpg"
	    	);
	        dbpromo.addPromotion(newPromo);        
	
	        newPromo	= new Promotion
	    	(
	    		3, 2, 1, "ColdStone", "Ice Cream Stall", "50% all food", "2012-08-30", "2012-12-31", 
	    		"Plaza Indonesia 5th Floor", "1111000", "http://www.adaapaya.com/userdata/thumbnail/87c3e794ade8307e7cb608c16f1a2832.jpg"
	    	);
	        dbpromo.addPromotion(newPromo);        
	        
        
        
        List<Promotion> promotions	= getPromoList();    
        
		// looping through all song nodes <song>
		for (int i = 0; i < promotions.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
									
			map.put(KEY_ID, "1");
			map.put(KEY_TITLE, promotions.get(i).getName());
			map.put(KEY_ARTIST, promotions.get(i).getNameDesc());
			map.put(KEY_DURATION, promotions.get(i).getPromoDesc());
			map.put(KEY_THUMB_URL, promotions.get(i).getImage());

			songsList.add(map);
		}		
		
		return songsList;
	}

    public List<Promotion> getPromoList() {
        PromotionHandler db = new PromotionHandler(this);        
        List<Promotion> contacts = db.getAllPromotions();      
        db.close();        
        return contacts;
    }

}