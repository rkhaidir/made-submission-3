package com.example.rkhaidir.submission_dua_made.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rkhaidir.submission_dua_made.BuildConfig;
import com.example.rkhaidir.submission_dua_made.R;
import com.example.rkhaidir.submission_dua_made.model.Movie;
import com.example.rkhaidir.submission_dua_made.model.MovieDetail;
import com.example.rkhaidir.submission_dua_made.viewmodel.MovieViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.img_backdrop) ImageView imgBackdrop;
    @BindView(R.id.img_poster) ImageView imgPoster;
    @BindView(R.id.txt_title) TextView tvTitle;
    @BindView(R.id.txt_years) TextView tvYears;
    @BindView(R.id.rb_movie) RatingBar rbMovie;
    @BindView(R.id.txt_genre) TextView tvGenre;
    @BindView(R.id.txt_overview) TextView tvOverview;
    @BindView(R.id.pb_movie_detail) ProgressBar progressBar;

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Movie movie = getIntent().getParcelableExtra("movies");

        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovieDetail().observe(this, getMovie);
        movieViewModel.setMovieDetail(movie.getId());
        showLoading(true);
    }

    private Observer<ArrayList<MovieDetail>> getMovie = new Observer<ArrayList<MovieDetail>>() {
        @Override
        public void onChanged(ArrayList<MovieDetail> movieDetails) {
            collapsingToolbarLayout.setTitle(movieDetails.get(0).getTitle());
            tvTitle.setText(movieDetails.get(0).getTitle());
            tvYears.setText(movieDetails.get(0).getRelease());
            rbMovie.setRating((float)(movieDetails.get(0).getRating()/2));
            tvOverview.setText(movieDetails.get(0).getOverview());
            tvGenre.setText(movieDetails.get(0).getGenres());

            Glide.with(DetailActivity.this)
                    .load(BuildConfig.POSTER_URL+movieDetails.get(0).getPoster())
                    .into(imgPoster);

            Glide.with(DetailActivity.this)
                    .load(BuildConfig.POSTER_URL+movieDetails.get(0).getBackdrop())
                    .into(imgBackdrop);

            showLoading(false);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLoading(Boolean state) {
        if(state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
