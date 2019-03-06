package com.photoviewerapplication.ui.home;

import com.photoviewerapplication.BaseNavigator;
import com.photoviewerapplication.BaseView;
import android.graphics.Bitmap;
import java.util.List;

import io.reactivex.Completable;

public interface HomeContract {

    interface View extends BaseView {
        void setBitmapList(List<Bitmap> bitmapList);
        Completable showLoadingAnimation();
        Completable hideLoadingAnimation();
    }

    interface Presenter {
        void doResearch(String keyword);
    }

    interface Navigator extends BaseNavigator {
    }
}
