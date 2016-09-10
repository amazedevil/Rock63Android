package com.uksusoff.rock63.ui;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.uksusoff.rock63.data.DBHelper;
import com.uksusoff.rock63.data.InternalPrefs_;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.StringRes;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {

    public static final String ACTION_CHECK_ALARM = "com.uksusoff.rock63.ui.ACTION_CHECK_ALARM";

    private DBHelper dbHelper = null;

    @Pref
    InternalPrefs_ internalPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendBroadcast(new Intent(ACTION_CHECK_ALARM));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

    protected DBHelper getHelper() {
        if (dbHelper == null) {
            dbHelper = (DBHelper)OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return dbHelper;
    }

    @UiThread
    public void showWarning(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showWarning(@StringRes int resId) {
        showWarning(getString(resId));
    }
    
}