package com.photoviewerapplication.shared_preference;

import android.content.SharedPreferences;

public interface SharedPreferenceManager {
    SharedPreferences getSharedPreferences();

    SharedPreferences getSharedPreferences(String id);
}
