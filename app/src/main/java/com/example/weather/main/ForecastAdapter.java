package com.example.weather.main;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.data.Forecast;
import com.example.weather.listView.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter implements ListViewAdapter {

    private List<Forecast> data = new ArrayList<>();
    private OnForecastCLickListener onForecastClickListener = null;

    public void setData(List<Forecast> data) {
        this.data = data;
    }

    public void setForecastClickListener(OnForecastCLickListener onForecastClickListener) {
        this.onForecastClickListener = onForecastClickListener;
    }

    @Override
    public List<View> getViews(Context context) {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            views.add(getView(i, context));
        }
        return views;
    }

    private View getView(int position, Context context) {
        Forecast item = data.get(position);

        // Select layout resource for the view. We use enlarged view for the first item.
        int layoutRes = R.layout.item_forecast;
        if (position == 0) {
            layoutRes = R.layout.item_forecast_primary;
        }

        // Inflate the view from the XML
        View view = LayoutInflater.from(context).inflate(layoutRes, null);

        // Weather Icon
        ImageView weatherIcon = view.findViewById(R.id.weather_icon);
        Resources resources = context.getResources();
        int iconId = resources.getIdentifier(item.getIcon(), "drawable", context.getPackageName());
        weatherIcon.setImageResource(iconId);

        // Date String
        TextView date = view.findViewById(R.id.date);
        String dateString = item.getDateString("EE, MMM dd");
        if (position == 0) {
            dateString = item.getDateString("'Today', MMM dd");
        }
        date.setText(dateString);

        // Weather Description
        TextView weatherDescription = view.findViewById(R.id.weather_description);
        weatherDescription.setText(item.getDescription());

        // High Temp
        TextView highTemperature = view.findViewById(R.id.high_temperature);
        highTemperature.setText(item.getHighTemp() + "\u00b0");

        // Low Temp
        TextView lowTemperature = view.findViewById(R.id.low_temperature);
        lowTemperature.setText(item.getLowTemp() + "\u00b0");

        // OnClickListener
        view.setOnClickListener(v -> {
            if (onForecastClickListener != null) {
                onForecastClickListener.onForecastClick(item);
            }
        });

        // Return final view
        return view;
    }
}
