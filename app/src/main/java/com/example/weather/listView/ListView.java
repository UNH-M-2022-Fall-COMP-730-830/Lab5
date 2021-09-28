package com.example.weather.listView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * A view group that allows to display views in a scrollable list.
 */
public class ListView extends ScrollView {

    private LinearLayout container;

    private ListViewAdapter adapter;

    public ListView(Context context) {
        super(context);
        init(context);
    }

    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        addView(container, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    public void setAdapter(ListViewAdapter adapter) {
        this.adapter = adapter;
    }

    public void notifyDataChanged() {
        container.removeAllViews();
        for (View view : adapter.getViews(getContext())) {
            container.addView(view, new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        }
    }
}
