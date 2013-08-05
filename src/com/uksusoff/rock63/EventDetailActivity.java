package com.uksusoff.rock63;

import java.text.SimpleDateFormat;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.uksusoff.rock63.data.entities.Event;
import com.uksusoff.rock63.data.entities.NewsItem;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

@EActivity(R.layout.activity_event_detail)
public class EventDetailActivity extends Activity {
    
    @Extra("event")
    Event event;
    
    @Pref
    ISharedPrefs_ sharedPrefs;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        String theme = sharedPrefs.theme().get(); //getSharedPreferences(SettingsActivity.ROCK63_PREFS, 0).getString(SettingsActivity.ROCK63_PREFS_THEME, SettingsActivity.ROCK63_PREFS_THEME_OPT_DARK);

        if (theme.equalsIgnoreCase(Settings.ROCK63_PREFS_THEME_OPT_DARK)) {
            setTheme(R.style.AppDarkTheme);
        } else if (theme.equalsIgnoreCase(Settings.ROCK63_PREFS_THEME_OPT_LIGHT)) {
            setTheme(R.style.AppLightTheme);
        }
        
        super.onCreate(savedInstanceState);
        
    }

    @AfterViews
    void init() {
                
        ((TextView)findViewById(R.id.event_detail_title)).setText(event.getTitle());
        SimpleDateFormat fDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat fTime = new SimpleDateFormat("HH:mm");
        if (event.getEnd()!=null) {
            ((TextView)findViewById(R.id.event_detail_datentime)).setText(
                    String.format("%s %s - %s %s", fDate.format(event.getStart()), fTime.format(event.getStart()), fDate.format(event.getEnd()), fTime.format(event.getEnd()))
            );
        } else {
            ((TextView)findViewById(R.id.event_detail_datentime)).setText(
                    String.format("%s %s", fDate.format(event.getStart()), fTime.format(event.getStart()))
            );
        }
        
        if (event.getPlace()!=null) {
            ((TextView)findViewById(R.id.event_detail_placename)).setText(event.getPlace().getName());
            ((TextView)findViewById(R.id.event_detail_placeaddr)).setText(event.getPlace().getAddress());
            ((TextView)findViewById(R.id.event_detail_placephone)).setText(event.getPlace().getPhone());
            ((TextView)findViewById(R.id.event_detail_placelink)).setText(event.getPlace().getUrl());
            ((TextView)findViewById(R.id.event_detail_placevklink)).setText(event.getPlace().getVkUrl());
        
            ((Button)findViewById(R.id.event_detail_infdetailbtn)).setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    View inf = EventDetailActivity.this.findViewById(R.id.event_detail_placeinf);
                    if (inf.getVisibility()==View.VISIBLE) {
                        inf.setVisibility(View.GONE);
                        ((Button)findViewById(R.id.event_detail_infdetailbtn)).setText(R.string.event_show_info_button_title);
                    } else {
                        inf.setVisibility(View.VISIBLE);
                        ((Button)findViewById(R.id.event_detail_infdetailbtn)).setText(R.string.event_hide_info_button_title);
                    }
                }
                
            });
            
            ((Button)findViewById(R.id.event_detail_infdetailbtn)).setText(R.string.event_show_info_button_title);
        
        } else {
            ((TextView)findViewById(R.id.event_detail_placename)).setVisibility(View.GONE);
            ((Button)findViewById(R.id.event_detail_infdetailbtn)).setVisibility(View.GONE);
        }
        
        ((TextView)findViewById(R.id.event_detail_description)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView)findViewById(R.id.event_detail_description)).setText(Html.fromHtml(event.getBody()));
        
        if (event.getMediumThumbUrl()!=null) {
            UrlImageViewHelper.setUrlDrawable((ImageView)findViewById(R.id.event_detail_image), event.getMediumThumbUrl(), R.drawable.news_medium_placeholder);
        } else {
            ((ImageView)findViewById(R.id.event_detail_image)).setVisibility(View.GONE);
        }
    }
    
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_event_detail, menu);
        return true;
    }*/

}