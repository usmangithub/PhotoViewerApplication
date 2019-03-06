package com.photoviewerapplication.ui.home.model;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import com.photoviewerapplication.shared_preference.SharedPreferenceManager;

public class LocalDataSource {
    private static final String PHOTO_SHARED_PREFERENCE = "photo_shared_preference";

    private SharedPreferences sharedPreferences;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public LocalDataSource(SharedPreferenceManager sharedPreferenceManager){
        this.sharedPreferences = sharedPreferenceManager
                .getSharedPreferences(PHOTO_SHARED_PREFERENCE);
    }

    public void savePhoto(List<PhotoData> photo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("photo_id", new Gson().toJson(photo));
        editor.apply();
    }

    public List<PhotoData> getPhoto() {
        String obj = sharedPreferences.getString("photo_id", "");
        if(obj.equals("")) {
            return null;
        } else {
            return new Gson().fromJson(obj, new TypeToken<ArrayList<PhotoData>>(){}.getType());
        }
    }
}
