package com.photoviewerapplication.ui.home.model;
import java.util.List;

import com.photoviewerapplication.api.ApiManager;
import io.reactivex.Single;

public class RemoteDataSource {

    private PhotoApi photoApi;

    public RemoteDataSource(ApiManager apiManager) {
        photoApi = apiManager.getRetrofit().create(PhotoApi.class);
    }

    public Single<List<PhotoData>> getPhoto() {
        return photoApi.getPhoto("bird");
    }
}
