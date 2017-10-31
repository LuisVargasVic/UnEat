package com.example.fernandalopezcardenas.uneatfinal;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LuisVargas on 30/10/2017.
 */

public class IngredientsAdapter extends BaseAdapter{
    private FirebaseDatabase data;
    private Activity activity;

    public IngredientsAdapter(FirebaseDatabase data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public FirebaseDatabase getData() {
        return data;
    }

    public void setData(FirebaseDatabase data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = activity.getLayoutInflater().inflate(R.layout.row_car, null);
        }

        TextView Ingredient = (TextView)view.findViewById(R.id.textView);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        try{

            JSONObject object = data.getJSONObject(i);
            for (){
                
            }
            Ingredient.setText(object.getString("name"));
            studentGrade.setText(object.getDouble("grade") + "");

        }catch(JSONException jse){
            jse.printStackTrace();
        }

        return view;
    }
    }
}
