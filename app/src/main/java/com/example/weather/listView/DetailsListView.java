package com.example.weather.listView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.data.ForecastDetails;

import java.util.List;

/**
 * A view that allows to display {@link ForecastDetails} item views in a scrollable list.
 */
public class DetailsListView extends ScrollView {

    private LinearLayout container;

    public DetailsListView(Context context) {
        super(context);
        init(context);
    }

    public DetailsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DetailsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DetailsListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        addView(container, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    public void show(List<ForecastDetails> data) {
        container.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View view = getView(i, data.get(i), getContext());
            container.addView(view, new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        }
    }

    private View getView(int position, ForecastDetails item, Context context) {
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
