package com.photoviewerapplication.ui.home.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoApi {
    @GET("/rest/search_public?type=photo&limit=10")
    Single<List<PhotoData>> getPhoto(@Query("keyword") String keyword);
}
