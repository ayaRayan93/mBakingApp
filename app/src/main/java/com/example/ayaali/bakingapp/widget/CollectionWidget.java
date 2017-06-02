package com.example.ayaali.bakingapp.widget;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.ayaali.bakingapp.R;
import com.example.ayaali.bakingapp.models.Ingredient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.action;
import static android.R.attr.streamType;

/**
 * Implementation of App Widget functionality.
 */
public class CollectionWidget extends AppWidgetProvider {

    static List<Ingredient> ingredients;
    static String con="Ingredients";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId,String widgetText) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.collection_widget);
       views.setTextViewText(R.id.single_note_title, "Ingredients");

    views.setTextViewText(R.id.text, widgetText);



        // Set up the collection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            setRemoteAdapter(context, views);
        } else {
            setRemoteAdapterV11(context, views);
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    static String myIngredients() {

String dd="";
            for (int i = 0; i < ingredients.size(); i++) {
                dd+=ingredients.get(i).getIngredient() + " " +
                        ingredients.get(i).getQuantity() + " " +
                        ingredients.get(i).getMeasure()+"\n";
            }
            return dd;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        String title1 = extras.getString("title");
        ingredients= (List<Ingredient>) extras.get("ingredients");
       // updateAppWidget(context, null, 0,"Ingredients");
        if(ingredients!=null)
       con=myIngredients();
       // Toast.makeText(context, title1,Toast.LENGTH_LONG).show();//this gives an empty space
        Intent mintent = new Intent(context.getApplicationContext(),
                WidgetService.class);
        mintent.putExtra("in", (Serializable) ingredients);
context.startService(mintent);
        if (action != null ) {
            final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName name = new ComponentName(context, CollectionWidget.class);
            int[] appWidgetId = AppWidgetManager.getInstance(context).getAppWidgetIds(name);
            final int N = appWidgetId.length;
            if (N < 1)
            {
                return ;
            }
            else {
                int id = appWidgetId[N-1];
                updateAppWidget(context, appWidgetManager, id ,con);
            }
        }

        else {
            super.onReceive(context, intent);
        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId,con);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /**
     * Sets the remote adapter used to fill in the list items
     *
     * @param views RemoteViews to set the RemoteAdapter
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.widget_list,
                new Intent(context, WidgetService.class));
    }

    /**
     * Sets the remote adapter used to fill in the list items
     *
     * @param views RemoteViews to set the RemoteAdapter
     */
    @SuppressWarnings("deprecation")
    private static void setRemoteAdapterV11(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(0, R.id.widget_list,
                new Intent(context, WidgetService.class));
    }
}

