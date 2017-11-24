package com.example.fernandalopezcardenas.uneatfinal.Detail;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fernandalopezcardenas.uneatfinal.R;

import java.util.ArrayList;

/**
 * Created by gabotrugomez on 11/13/17.
 */

public class AreasResAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<DetailRestaurant> mDataset;
    private static LayoutInflater inflater = null;

    public AreasResAdapter(Activity activity, ArrayList<DetailRestaurant> mDataset) {
        this.activity = activity;
        this.mDataset = mDataset;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if (view == null)
            vi = inflater.inflate(R.layout.row_restaurant, null);


        TextView title = (TextView) vi.findViewById(R.id.restaurant_text); // title
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.image_restaurant); // thumb image

        DetailRestaurant restaurant = mDataset.get(i);

        // Setting all values in listview
        title.setText(restaurant.getName());
        Glide.with(activity)
                .load(restaurant.getImageRestaurant())
                .into(thumb_image);


        return vi;
    }
}
