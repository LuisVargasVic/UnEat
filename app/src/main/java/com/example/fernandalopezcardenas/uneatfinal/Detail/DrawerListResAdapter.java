package com.example.fernandalopezcardenas.uneatfinal.Detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fernandalopezcardenas.uneatfinal.Client.MainClientActivity;
import com.example.fernandalopezcardenas.uneatfinal.R;
import com.example.fernandalopezcardenas.uneatfinal.Restaurant.MainRestaurantActivity;

import java.util.ArrayList;

/**
 * Created by fernandalopezcardenas on 24/11/17.
 */

public class DrawerListResAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<MainRestaurantActivity.NavItem> mNavResItems;


    public DrawerListResAdapter(Context context, ArrayList<MainRestaurantActivity.NavItem> navResItems) {
        mContext = context;
        mNavResItems = navResItems;
    }


    @Override
    public int getCount() {
        return mNavResItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavResItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);

        titleView.setText( mNavResItems.get(position).mTitle );
        subtitleView.setText( mNavResItems.get(position).mSubtitle );
        iconView.setImageResource(mNavResItems.get(position).mIcon);

        return view;
    }
}
