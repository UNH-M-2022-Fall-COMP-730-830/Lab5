package com.example.weather.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.data.ForecastDetails;
import com.example.weather.listView.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter implements ListViewAdapter {

    private List<ForecastDetails> data = new ArrayList<>();

    public void setData(List<ForecastDetails> data) {
        this.data = data;
    }

    @Override
    public List<View> getViews(Context context) {
        List<View> views = new ArrayList<>();
        for (ForecastDetails item : data) {
            views.add(getView(item, context));
        }
        return views;
    }

    private View getView(ForecastDetails item, Context context) {
        // Inflate the view from XML
        View view = LayoutInflater.from(context).inflate(R.layout.item_details, null);

        // Description
        TextView description = view.findViewById(R.id.name);
        description.setText(item.getDescription());

        // Value
        TextView value = view.findViewById(R.id.value);
        value.setText(item.getValue());

        // Return view
        return view;
    }
}
