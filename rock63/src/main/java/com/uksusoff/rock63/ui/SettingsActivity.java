package com.uksusoff.rock63.ui;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.uksusoff.rock63.R;
import com.uksusoff.rock63.data.UserPrefs;
import com.uksusoff.rock63.data.UserPrefs_;

@EActivity(R.layout.a_settings)
public class SettingsActivity extends BaseMenuActivity {

    @ViewById(R.id.info_body_text)
    TextView bodyText;

    @ViewById(R.id.dailyReminder)
    Switch dailyReminder;

    @ViewById(R.id.weeklyReminder)
    Switch weeklyReminder;

    @Pref
    UserPrefs_ userPrefs;

    @Override
    protected void init() {
        super.init();

        bodyText.setMovementMethod(LinkMovementMethod.getInstance());
        bodyText.setText(Html.fromHtml(getString(R.string.about_body)));

        dailyReminder.setChecked(userPrefs.remindDayBefore().get());
        weeklyReminder.setChecked(userPrefs.remindWeekBefore().get());

        dailyReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userPrefs.edit().remindDayBefore().put(isChecked).apply();
            }

        });

        weeklyReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userPrefs.edit().remindWeekBefore().put(isChecked).apply();
            }

        });
    }

}