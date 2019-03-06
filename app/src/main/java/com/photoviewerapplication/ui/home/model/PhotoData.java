package com.photoviewerapplication.ui.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotoData implements Serializable {
    @SerializedName("photo_title")
    @Expose
    private String photoName;

    @SerializedName("thumbnail_image_url")
    @Expose
    private String photoImage;

    public PhotoData(String photoName, String photoImage) {
        this.photoName = photoName;
        this.photoImage = photoImage;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(String photoImage) {
        this.photoImage = photoImage;
    }
}
