package com.example.discountapp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.example.model.Merchant;
import com.example.model.Promotion;
import com.example.service.MerchantHandler;
import com.example.service.PromotionHandler;

public class DiscountApp extends ListActivity  {
	public final static String EXTRA_MESSAGE = "Hellow"; 
    public static SQLiteDatabase ourDatabase;	
    private final String PATH = "/mnt/sdcard/.data/";  //put the downloaded file here
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
               
        PromotionHandler dbpromo = new PromotionHandler(this);
        
        DownloadFromUrl("http://www.adaapaya.com/userdata/real/7bbb1b10567cca5d6b130019c94dfc37.jpg", "koiki"); 
        String fileurl		= PATH + "koiki" +".jpg";
        
        Promotion newPromo	= new Promotion
    	(
    		2, 2, 1, "Koiki", "Japanese Restaurant", "50% all food", "2012-08-30", "2012-12-31", 
    		"Plaza Indonesia Ground Floor", "1111000", fileurl
    	);
        
        dbpromo.addPromotion(newPromo);        
              
  		ListAdapter adapter = createAdapter();
        setListAdapter(adapter);         

    }
    
    public void DownloadFromUrl(String imageURL, String fileName) {  //this is the downloader method
        try {
            URL url = new URL(imageURL); //you can write here any link
            File file = new File(PATH + fileName + ".jpg");

            long startTime = System.currentTimeMillis();
            Log.d("ImageManager", "download begining");
            Log.d("ImageManager", "download url:" + url);
            Log.d("ImageManager", "downloaded file name:" + fileName);

            URLConnection ucon = url.openConnection();

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;
            while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baf.toByteArray());
            fos.close();
            Log.d("ImageManager", "download ready in"
                            + ((System.currentTimeMillis() - startTime) / 1000)
                            + " sec");

        } catch (IOException e) {
                Log.d("ImageManager", "Error: " + e);
        }
    }
    
    public List<Promotion> getPromoList() {
        PromotionHandler db = new PromotionHandler(this);        
        List<Promotion> contacts = db.getAllPromotions();      
        db.close();        
        return contacts;
    }
    
    public List<Merchant> getMerchantList() {
        MerchantHandler db = new MerchantHandler(this);        
        List<Merchant> contacts = db.getAllMerchants();      
        db.close();        
        return contacts;
    }
    
    protected ListAdapter createAdapter()
    {    	 
    	List<Promotion> promotions	= getPromoList();    
    	System.out.println("size = " + promotions.size());
    	String promoList[] = new String[promotions.size()];
    	
    	int i=0;
        ImageView jpgView 	= (ImageView)findViewById(R.id.imageView);

    	for (Promotion cn : promotions) {
            Bitmap bitmap = BitmapFactory.decodeFile(cn.getImage());
            jpgView.setImageBitmap(bitmap);
        	
        	promoList[i] = 
        		cn.getName() 
        		+ ", " + cn.getNameDesc() 
        		+ ", " + cn.getPromoDesc() 
        		+ ", " + cn.getStartDate() 
        		+ " - " + cn.getEndDate()
        		+ " " + cn.getLocation();
    		i++;
    	}
        
    	ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, promoList);
    	return adapter;    
    }    

    
    public void searchPromotion (View view) {
    	Intent intent 		= new Intent(this, DisplayMessageActivity.class);    	
    	EditText editText	= (EditText) findViewById(R.id.search_message);
    	String message		= editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);    	
    	startActivity(intent);
    }
}

/*    	
	List<Merchant> merchants	= getMerchantList();    	
String merchantList[] = new String[merchants.size()];

int i=0;
for (Merchant cn : merchants) {
	merchantList[i] = "Id: "+cn.getID()+" ,Name: " + cn.getName() ;
	i++;
}
*/