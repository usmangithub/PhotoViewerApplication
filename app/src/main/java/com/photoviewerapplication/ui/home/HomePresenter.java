package com.photoviewerapplication.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.photoviewerapplication.BasePresenter;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.SingleSubject;

public class HomePresenter
        extends BasePresenter<HomeContract.View, HomeContract.Navigator>
        implements HomeContract.Presenter {

    private Disposable photoDisposable;

    public HomePresenter(HomeContract.View view,
                         HomeContract.Navigator navigator) {
        super(view, navigator);
    }

    private Single<List<String>> getPhotoData(String keyword) {
        SingleSubject<List<String>> singleSubject = SingleSubject.create();
        Schedulers.io().scheduleDirect(() -> singleSubject.onSuccess(
                getFileDataList(keyword)));
        return singleSubject;
    }

    @Override
    public void init() {
        super.init();
    }

    private List<String> getFileDataList(String keyWord) {
        String urlString = "http://api.photozou.jp/rest/search_public&type=photo&limit=50&keyword="
                + keyWord;
        List<String> list = new ArrayList<String>();
        int type;
        String s;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");
            urlConnection.connect();
            final XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new InputStreamReader(urlConnection.getInputStream()));

            while ((type = parser.next()) != XmlPullParser.END_DOCUMENT) {
                if (type == XmlPullParser.START_TAG && "image_url".equals(parser.getName())) {
                    parser.next();
                    if ((s = parser.getText()) != null && !"".equals(s)) {
                        list.add(s);
                    }
                }
            }
            urlConnection.disconnect();
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
        } catch (XmlPullParserException e) {
            Log.e("TAG", e.getMessage());
        }
        return list;
    }

    private Single<List<Bitmap>> getBitmapData(List<String> urlList) {
        SingleSubject<List<Bitmap>> singleSubject = SingleSubject.create();
        Schedulers.io().scheduleDirect(() -> singleSubject.onSuccess(
                getBitmapList(urlList)));
        return singleSubject;
    }

    private List<Bitmap> getBitmapList(List<String> urlList) {
        List<Bitmap> photos = new ArrayList<Bitmap>();
        for (int i = 0; i < urlList.size(); i++) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(urlList.get(i)).openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                photos.add(BitmapFactory.decodeStream(input));
            } catch (IOException e) {
                Log.e("TAG", e.getMessage());
            }
        }
        return photos;
    }

    @Override
    public void doResearch(String keyword) {
        view.showLoadingAnimation().subscribe(() -> getData(keyword));
    }

    private void getData(String keyword) {
        Schedulers.io().scheduleDirect(() -> photoDisposable = getPhotoData(keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photoList ->
                        getBitmapData(photoList)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(bitmapList ->
                                        view.hideLoadingAnimation()
                                                .subscribe(() -> view.setBitmapList(bitmapList))))
        );
    }

    @Override
    public void destroy() {
        super.destroy();
        if (photoDisposable != null && !photoDisposable.isDisposed()) {
            photoDisposable.dispose();
        }
    }
}
