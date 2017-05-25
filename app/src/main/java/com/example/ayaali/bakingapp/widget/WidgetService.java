package com.example.ayaali.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.example.ayaali.bakingapp.R;
import com.example.ayaali.bakingapp.models.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * WidgetService is the {@link RemoteViewsService} that will return our RemoteViewsFactory
 */
public class WidgetService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {


        //Toast.makeText(context, title1,Toast.LENGTH_LONG).show()
        return new WidgetDataProvider(this, intent);
    }
    @Override
    public void onStart(Intent intent, int startId) {
        this.onGetViewFactory(intent);
  //  WidgetDataProvider widgetDataProvider=new WidgetDataProvider(this, intent);
    }


}
