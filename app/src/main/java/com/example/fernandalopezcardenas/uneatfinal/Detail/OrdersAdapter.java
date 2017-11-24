package com.example.fernandalopezcardenas.uneatfinal.Detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.fernandalopezcardenas.uneatfinal.Client.PayActivity;
import com.example.fernandalopezcardenas.uneatfinal.R;

import java.util.ArrayList;

/**
 * Created by fernandalopezcardenas on 21/11/17.
 */


//establecer horario de entrega, push notifications when text equals to deliver is ready push notification
public class OrdersAdapter extends ArrayAdapter<DetailCart> {
    public ArrayList<DetailCart> ordersChosen = new ArrayList<>();

    public OrdersAdapter(Context context, ArrayList<DetailCart> users) {
        super(context, 0, users);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final DetailCart user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_orders, parent, false);
        }
        // Lookup view for data population
        final TextView Order = convertView.findViewById(R.id.order);
        final CheckBox checkBox = convertView.findViewById(R.id.checkBox);

        // chekbox
        // checkbox.onvalue
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    ordersChosen.add(user);

                }
                else {
                    ordersChosen.remove(user);
                }
            }
        });
        // Populate the data into the template view using the data object
        Order.setText(user.getCart().getName());
        // Return the completed view to render on screen
        return convertView;
    }

    public ArrayList<String> getOrdersChosen(){
        ArrayList<String> UidrequestArray = new ArrayList<>();
        for (DetailCart user : ordersChosen) {
            UidrequestArray.add(user.getUidrequest());
        }
        return UidrequestArray;
    }

}
