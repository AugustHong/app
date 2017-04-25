package com.example.preferencefragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by 偉德 on 2017/4/25.
 */

public class My_PreferenceFragment extends PreferenceFragment {  //以後都這樣創就好了
        public static final String PREFERENCES_NAME = "myPreference"; //偏好設定名稱
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // 取得PreferenceManager並設定偏好設定名稱
            this.getPreferenceManager().setSharedPreferencesName(PREFERENCES_NAME);
            addPreferencesFromResource(R.xml.my_preferences);
    }
}
