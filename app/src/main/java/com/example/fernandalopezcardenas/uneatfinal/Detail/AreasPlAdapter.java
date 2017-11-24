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
 * Created by gabotrugomez on 11/14/17.
 */

public class AreasPlAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<DetailPlate> mDataset;
    private static LayoutInflater inflater = null;

    public AreasPlAdapter(Activity activity, ArrayList<DetailPlate> mDataset) {
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
            vi = inflater.inflate(R.layout.row_plate, null);


        TextView title = (TextView) vi.findViewById(R.id.plate_text); // title
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.image_plate); // thumb image

        DetailPlate plate = mDataset.get(i);

        // Setting all values in listview
        title.setText(plate.getName());
        Glide.with(activity)
                .load(plate.getImagePlate())
                .into(thumb_image);
        return vi;
    }
}
