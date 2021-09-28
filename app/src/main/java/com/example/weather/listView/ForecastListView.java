package com.example.weather.listView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.data.Forecast;
import com.example.weather.main.OnForecastCLickListener;

import java.util.List;

/**
 * A view that allows to display {@link Forecast} item views in a scrollable list.
 */
public class ForecastListView extends ScrollView {

    private LinearLayout container;
    private OnForecastCLickListener onForecastClickListener;

    public ForecastListView(Context context) {
        super(context);
        init(context);
    }

    public ForecastListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ForecastListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ForecastListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        addView(container, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    public void show(List<Forecast> data) {
        container.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View view = getView(i, data.get(i), getContext());
            container.addView(view, new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        }
    }

    private View getView(int position, Forecast item, Context context) {
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

    public void setForecastClickListener(OnForecastCLickListener onForecastClickListener) {
        this.onForecastClickListener = onForecastClickListener;
    }
}