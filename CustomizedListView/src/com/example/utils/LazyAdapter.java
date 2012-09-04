package com.example.utils;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.discountapp.R;
import com.example.discountapp.DiscountApp;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView name = (TextView)vi.findViewById(R.id.name); // name
        TextView namedesc = (TextView)vi.findViewById(R.id.namedesc); // namedesc name
        TextView promodesc = (TextView)vi.findViewById(R.id.promodesc); // promodesc
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        
        HashMap<String, String> promotion = new HashMap<String, String>();
        promotion = data.get(position);
        
        // Setting all values in listview
        name.setText(promotion.get(DiscountApp.KEY_NAME));
        namedesc.setText(promotion.get(DiscountApp.KEY_NAME_DESC));
        promodesc.setText(promotion.get(DiscountApp.KEY_PROMO_DESC));
        imageLoader.DisplayImage(promotion.get(DiscountApp.KEY_IMAGE_URL), thumb_image);
        return vi;
    }
}