package com.example.weather.listView;

import android.content.Context;
import android.view.View;

import java.util.List;

public interface ListViewAdapter {
    List<View> getViews(Context context);
}
