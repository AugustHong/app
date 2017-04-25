package com.example.preferencefragment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //加入這一行才能讓PreferenceFragment出現
        getFragmentManager().beginTransaction().replace(android.R.id.content, new My_PreferenceFragment()).commit();
    }
}


