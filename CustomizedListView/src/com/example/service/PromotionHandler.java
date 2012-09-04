package com.example.service;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.model.Promotion;

public class PromotionHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "db_promotion";
 
    // Contacts table name
    private static final String TABLE_NAME = "promotion";
 
    // Contacts Table Columns names
    private static final String KEY_ID 				= "idPromo";
    private static final String KEY_ID_MERCHANT 	= "idPromotion";
    private static final String KEY_ID_CARD 		= "idCard";
    private static final String KEY_NAME 			= "name";
    private static final String KEY_NAME_DESC		= "nameDesc";
    private static final String KEY_PROMO_DESC		= "promoDesc";
    private static final String KEY_START_DATE		= "startDate";
    private static final String KEY_END_DATE		= "endDate";
    private static final String KEY_LOCATION		= "location";
    private static final String KEY_WEEKDAYS 		= "weekdays";
    private static final String KEY_IMAGE			= "image";
	
    public PromotionHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	String CREATE_CONTACTS_TABLE = 
    		"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," 
                + KEY_ID_MERCHANT + " INTEGER," 
                + KEY_ID_CARD + " INTEGER," 
                + KEY_NAME + " TEXT," 
                + KEY_NAME_DESC + " TEXT," 
                + KEY_PROMO_DESC + " TEXT," 
                + KEY_START_DATE + " TEXT," 
                + KEY_END_DATE + " TEXT," 
                + KEY_LOCATION + " TEXT," 
    			+ KEY_WEEKDAYS + " TEXT," 
    			+ KEY_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);          
    }
    
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if(oldVersion >= newVersion) return;
        
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
  
    // Add
    public void addPromotion(Promotion promotion) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(KEY_ID, promotion.getIdPromo());
        values.put(KEY_ID_MERCHANT, promotion.getIdMerchant());
        values.put(KEY_ID_CARD, promotion.getIdCard());
        values.put(KEY_NAME, promotion.getName());     
        values.put(KEY_NAME_DESC, promotion.getNameDesc());     
        values.put(KEY_PROMO_DESC, promotion.getPromoDesc());     
        values.put(KEY_START_DATE, promotion.getStartDate());     
        values.put(KEY_END_DATE, promotion.getEndDate());     
        values.put(KEY_LOCATION, promotion.getLocation());     
        values.put(KEY_WEEKDAYS, promotion.getWeekdays());     
        values.put(KEY_IMAGE, promotion.getImage());
        
        try {
        	db.insertOrThrow(TABLE_NAME, null, values);
        } catch (Exception e) {System.out.println(e);}
        db.close();     	    	
    }
     
    // Getting single contact
    public Promotion getPromotion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                KEY_NAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Promotion contact = new Promotion
        (
       		Integer.parseInt(cursor.getString(0)),
       		Integer.parseInt(cursor.getString(1)),
       		Integer.parseInt(cursor.getString(2)),
       		cursor.getString(3),
       		cursor.getString(4),
       		cursor.getString(5),
       		cursor.getString(6),
       		cursor.getString(7),
       		cursor.getString(8),
       		cursor.getString(9),
       		cursor.getString(10)
        );
        // return contact
        return contact;    	    	
    }
     
    // Getting All Contacts
    public List<Promotion> getAllPromotions(String query) {
    	
   		String[] keywords = query.split(" ");
    	
        List<Promotion> contactList = new ArrayList<Promotion>();

        // Select All Query
        int size = keywords.length;
        
        String selectQuery = "";
        if(size == 1 && keywords[0].contentEquals("all")) {
	        selectQuery = "	SELECT  * FROM " + TABLE_NAME;   
	        SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	     
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                Promotion contact = new Promotion();
	                contact.setIdPromo(Integer.parseInt(cursor.getString(0)));
	                contact.setIdMerchant(Integer.parseInt(cursor.getString(1)));
	                contact.setIdCard(Integer.parseInt(cursor.getString(2)));
	                contact.setName(cursor.getString(3));
	                contact.setNameDesc(cursor.getString(4));
	                contact.setPromoDesc(cursor.getString(5));
	                contact.setStartDate(cursor.getString(6));
	                contact.setEndDate(cursor.getString(7));
	                contact.setLocation(cursor.getString(8));
	                contact.setWeekdays(cursor.getString(9));
	                contact.setImage(cursor.getString(10));
	                // Adding contact to list
	                contactList.add(contact);
	            } while (cursor.moveToNext());
	        }	        
        }
        else {        
	        for(int i=0; i<size; i++) {
		        selectQuery = 
		        	"	SELECT  * FROM " + TABLE_NAME + " WHERE " +
			        	KEY_NAME + " LIKE '%" + keywords[i] + "%' OR " +
			        	KEY_NAME_DESC + " LIKE '%" + keywords[i] + "%' OR " +
			        	KEY_PROMO_DESC + " LIKE '%" + keywords[i] + "%' OR " +
			        	KEY_LOCATION + " LIKE '%" + keywords[i] + "%'";
		     
		        SQLiteDatabase db = this.getWritableDatabase();
		        Cursor cursor = db.rawQuery(selectQuery, null);
		     
		        // looping through all rows and adding to list
		        if (cursor.moveToFirst()) {
		            do {
		                Promotion contact = new Promotion();
		                contact.setIdPromo(Integer.parseInt(cursor.getString(0)));
		                contact.setIdMerchant(Integer.parseInt(cursor.getString(1)));
		                contact.setIdCard(Integer.parseInt(cursor.getString(2)));
		                contact.setName(cursor.getString(3));
		                contact.setNameDesc(cursor.getString(4));
		                contact.setPromoDesc(cursor.getString(5));
		                contact.setStartDate(cursor.getString(6));
		                contact.setEndDate(cursor.getString(7));
		                contact.setLocation(cursor.getString(8));
		                contact.setWeekdays(cursor.getString(9));
		                contact.setImage(cursor.getString(10));
		                // Adding contact to list
		                contactList.add(contact);
		            } while (cursor.moveToNext());
		        }
	        }
        }
     
        // return contact list
        return contactList;    	    	
    }
     
    // Getting contacts Count
    public int getPromotionCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();    	    	
    }
    
    // Updating single contact
    public int updatePromotion(Promotion promotion) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(KEY_ID, promotion.getIdPromo());
        values.put(KEY_ID_MERCHANT, promotion.getIdMerchant());
        values.put(KEY_ID_CARD, promotion.getIdCard());
        values.put(KEY_NAME, promotion.getName());     
        values.put(KEY_NAME_DESC, promotion.getNameDesc());     
        values.put(KEY_PROMO_DESC, promotion.getPromoDesc());     
        values.put(KEY_START_DATE, promotion.getStartDate());     
        values.put(KEY_END_DATE, promotion.getEndDate());     
        values.put(KEY_LOCATION, promotion.getLocation());     
        values.put(KEY_WEEKDAYS, promotion.getWeekdays());     
        values.put(KEY_IMAGE, promotion.getImage());             
     
        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
            new String[] 
            { 
    			String.valueOf(promotion.getIdPromo()), 
    			String.valueOf(promotion.getIdMerchant()), 
    			String.valueOf(promotion.getIdCard()),
    			String.valueOf(promotion.getName()),
    			String.valueOf(promotion.getNameDesc()), 
    			String.valueOf(promotion.getPromoDesc()), 
    			String.valueOf(promotion.getStartDate()), 
    			String.valueOf(promotion.getEndDate()), 
    			String.valueOf(promotion.getLocation()),
    			String.valueOf(promotion.getWeekdays()),        			
    			String.valueOf(promotion.getImage())        			    			
    		}
        );    	   	
    }
     
    // Deleting single contact
    public void deletePromotion(Promotion promotion) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(promotion.getIdPromo()) });
        db.close();    	
    }    

}
