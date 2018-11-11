package in.connectitude.newsx.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Random;

import in.connectitude.newsx.R;
import in.connectitude.newsx.ui.MainActivity;
import in.connectitude.newsx.utils.Constants;

/**
 * Implementation of App Widget functionality.
 */
public class NewsWidgetProvider extends AppWidgetProvider {

    RemoteViews views;




    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.news_widget_provider);

        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        views.setOnClickPendingIntent(R.id.linearLayout_Widget,pendingIntent);


        if(NewsWidgetProvider.getNewsTitle(context)==null){
            views.setTextViewText(R.id.newsWidgetTitle, context.getString(R.string.TitleWidget));
        }else{
            views.setTextViewText(R.id.newsWidgetTitle, getNewsTitle(context));
        }
        if(NewsWidgetProvider.getNewsDescription(context)==null){
            views.setTextViewText(R.id.newsWidget_description, context.getString(R.string.decriptionWidget));
        }else{
            views.setTextViewText(R.id.newsWidget_description, getNewsDescription(context));
        }

        if(NewsWidgetProvider.getNewsName(context)==null){
            views.setTextViewText(R.id.newsWidget_Name, context.getString(R.string.nameWidget));
        }else{
            views.setTextViewText(R.id.newsWidget_Name, getNewsName(context));
        }




        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, NewsWidgetProvider.class), views);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {

            views = new RemoteViews(context.getPackageName(),R.layout.news_widget_provider );
            appWidgetManager.updateAppWidget(appWidgetId, views);

            Intent configIntent = new Intent(context, MainActivity.class);
            PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
            views.setOnClickPendingIntent(R.id.linearLayout_Widget, configPendingIntent);



            updateNewsWidgets(context,appWidgetManager,appWidgetIds);
            //updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        views = new RemoteViews(context.getPackageName(), R.layout.news_widget_provider);
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainActivityIntent, 0);
        views.setOnClickPendingIntent(R.id.newsWidgetTitle, pendingIntent);

        if(getNewsTitle(context)==null){
            views.setTextViewText(R.id.newsWidgetTitle, context.getString(R.string.TitleWidget));
        }else{
            views.setTextViewText(R.id.newsWidgetTitle, getNewsTitle(context));
        }
        if(getNewsDescription(context)==null){
            views.setTextViewText(R.id.newsWidget_description,  context.getString(R.string.decriptionWidget));
        }else{
            views.setTextViewText(R.id.newsWidget_description, getNewsDescription(context));
        }

        if(getNewsName(context)==null){
            views.setTextViewText(R.id.newsWidget_Name, context.getString(R.string.nameWidget));
        }else{
            views.setTextViewText(R.id.newsWidget_Name, getNewsName(context));
        }




        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, NewsWidgetProvider.class), views);


    }

    private static String getNewsTitle(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.NEWS, Context.MODE_PRIVATE);

        String title = prefs.getString(context.getString(R.string.keywidgetTitle), "");
        return title;
    }

    private static String getNewsName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.NEWS, Context.MODE_PRIVATE);

        String name = prefs.getString(context.getString(R.string.keywidgetName), "");
        return name;
    }

    private static String getNewsDescription(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.NEWS, Context.MODE_PRIVATE);

        String description = prefs.getString(context.getString(R.string.keywidgetDescription), "");
        return description;
    }


    public static void updateNewsWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Toast.makeText(context, R.string.widgetUpdatedSuccesfully, Toast.LENGTH_SHORT).show();
        }
    }


}

