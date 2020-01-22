package com.example.rkhaidir.submission_dua_made.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rkhaidir.submission_dua_made.BuildConfig;
import com.example.rkhaidir.submission_dua_made.model.Movie;
import com.example.rkhaidir.submission_dua_made.model.MovieDetail;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> listMoviePlaying = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> listMoviePopular = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MovieDetail>> moviesDetail = new MutableLiveData<>();

    public void setMoviePlaying() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();
        String url = BuildConfig.BASE_URL+"movie/now_playing?api_key="+BuildConfig.API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String results = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(results);
                    JSONArray list = jsonObject.getJSONArray("results");
                    for(int i = 0; i < list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);
                        Movie movie = new Movie(object);
                        listItems.add(movie);
                    }
                    listMoviePlaying.postValue(listItems);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<Movie>> getMoviesPlaying() {
        return listMoviePlaying;
    }

    public void setMoviePopular() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();
        String url = BuildConfig.BASE_URL+"movie/popular?api_key="+BuildConfig.API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String results = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(results);
                    JSONArray list = jsonObject.getJSONArray("results");
                    for(int i = 0; i < list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);
                        Movie movie = new Movie(object);
                        listItems.add(movie);
                    }
                    listMoviePopular.postValue(listItems);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<Movie>> getMoviesPopular() {
        return listMoviePopular;
    }

    public void setMovieDetail(final int id) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieDetail> listMovieDetail = new ArrayList<>();
        String url = BuildConfig.BASE_URL+"movie/"+id+"?api_key="+BuildConfig.API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String results = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(results);
                    MovieDetail movieDetail = new MovieDetail(jsonObject);
                    listMovieDetail.add(movieDetail);
                    moviesDetail.postValue(listMovieDetail);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<MovieDetail>> getMovieDetail() {
        return moviesDetail;
    }
}
