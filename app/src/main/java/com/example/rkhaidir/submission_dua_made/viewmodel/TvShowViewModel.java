package com.example.rkhaidir.submission_dua_made.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rkhaidir.submission_dua_made.BuildConfig;
import com.example.rkhaidir.submission_dua_made.model.TvShow;
import com.example.rkhaidir.submission_dua_made.model.TvShowDetail;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TvShow>> listTvOn = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShow>> listTvPopular = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShowDetail>> tvDetails = new MutableLiveData<>();

    public void setTvOn() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> listItems = new ArrayList<>();
        String url = BuildConfig.BASE_URL+"tv/on_the_air?api_key="+BuildConfig.API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String results = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(results);
                    JSONArray list = jsonObject.getJSONArray("results");
                    for(int i = 0; i < list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);
                        TvShow tvShow = new TvShow(object);
                        listItems.add(tvShow);
                    }
                    listTvOn.postValue(listItems);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<TvShow>> getTvOn() {
        return listTvOn;
    }

    public void setTvPopular() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> listItem = new ArrayList<>();
        String url = BuildConfig.BASE_URL+"tv/popular?api_key="+BuildConfig.API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String results = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(results);
                    JSONArray list = jsonObject.getJSONArray("results");
                    for(int i = 0; i < list.length(); i++) {
                       JSONObject object = list.getJSONObject(i);
                       TvShow tvShow = new TvShow(object);
                       listItem.add(tvShow);
                    }
                    listTvPopular.postValue(listItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<TvShow>> getTvPopular() {
        return listTvPopular;
    }

    public void setTvDetail(final int id) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShowDetail> listItem = new ArrayList();
        String url = BuildConfig.BASE_URL+"tv/"+id+"?api_key="+BuildConfig.API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String results = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(results);
                    TvShowDetail tvShowDetail = new TvShowDetail(jsonObject);
                    listItem.add(tvShowDetail);
                    tvDetails.postValue(listItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<TvShowDetail>> getTvDetail() {
        return tvDetails;
    }
}
