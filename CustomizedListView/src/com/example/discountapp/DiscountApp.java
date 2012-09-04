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
	public static final String SEARCH_QUERY = "Hellow";	
	static final String KEY_PROMOTION = "promotion"; // parent node
	static final String KEY_ID_PROMO = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_NAME_DESC = "namedesc";
	public static final String KEY_PROMO_DESC = "promodesc";
	public static final String KEY_IMAGE_URL = "thumb_url";
	
	ListView list;
    LazyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent 	= getIntent();
		String query	= intent.getStringExtra(DiscountApp.SEARCH_QUERY);

		if (query == null)	query = "all";
		
		setContentView(R.layout.main);	// display search textbox and button
		displayList(query);				// display list of promotion information
		
	}	

    public void searchPromotion (View view) {
    	Intent intent 		= new Intent(this, DiscountApp.class);    	
    	EditText editText	= (EditText) findViewById(R.id.search_message);
    	String message		= editText.getText().toString();
    	intent.putExtra(SEARCH_QUERY, message);    	
    	startActivity(intent);
    }	
	
	public void displayList(String query) {
		final ArrayList<HashMap<String, String>> promolist = getList(query);

		list=(ListView)findViewById(R.id.list);		
        adapter=new LazyAdapter(this, promolist);        
        list.setAdapter(adapter);
        
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// GO TO DetailResult.java
				Intent in = new Intent(getApplicationContext(), DisplaySearchResult.class);
				in.putExtra(KEY_NAME, promolist.get(position).get(KEY_NAME));
				in.putExtra(KEY_NAME_DESC, promolist.get(position).get(KEY_NAME_DESC));
//				in.putExtra(KEY_THUMB_URL, Image);
				in.putExtra(KEY_PROMO_DESC, promolist.get(position).get(KEY_PROMO_DESC));
				startActivity(in);

			}
		});				
	}
	
	public ArrayList<HashMap<String, String>> getList(String query) {
		ArrayList<HashMap<String, String>> promotionsList = new ArrayList<HashMap<String, String>>();

		//	ADD INITIAL DATABASE CONTENT
		/* 
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
	    */    
                
        List<Promotion> promotions	= getPromoList(query);    
        
		// looping through all song nodes <song>
		for (int i = 0; i < promotions.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
									
			map.put(KEY_ID_PROMO, "1");
			map.put(KEY_NAME, promotions.get(i).getName());
			map.put(KEY_NAME_DESC, promotions.get(i).getNameDesc());
			map.put(KEY_PROMO_DESC, promotions.get(i).getPromoDesc());
			map.put(KEY_IMAGE_URL, promotions.get(i).getImage());

			promotionsList.add(map);
		}		
		
		return promotionsList;
	}

    public List<Promotion> getPromoList(String query) {
        PromotionHandler db = new PromotionHandler(this);        
        List<Promotion> contacts = db.getAllPromotions(query);      
        db.close();        
        return contacts;
    }

}