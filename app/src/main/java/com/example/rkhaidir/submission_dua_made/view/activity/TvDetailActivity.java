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
import com.example.rkhaidir.submission_dua_made.model.TvShow;
import com.example.rkhaidir.submission_dua_made.model.TvShowDetail;
import com.example.rkhaidir.submission_dua_made.viewmodel.TvShowViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_poster)
    ImageView imgPoster;

    @BindView(R.id.img_backdrop)
    ImageView imgBackdrop;

    @BindView(R.id.txt_title)
    TextView tvTitle;

    @BindView(R.id.txt_release)
    TextView tvRelease;

    @BindView(R.id.txt_genre)
    TextView tvGenres;

    @BindView(R.id.txt_overview)
    TextView tvOverview;

    @BindView(R.id.rb_tv)
    RatingBar rbTvDetail;

    @BindView(R.id.pb_tv_detail)
    ProgressBar progressBar;

    private TvShowViewModel tvShowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        TvShow tvShow = getIntent().getParcelableExtra("tv");

        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvDetail().observe(this, getTv);
        tvShowViewModel.setTvDetail(tvShow.getId());
        showLoading(true);
    }

    private Observer<ArrayList<TvShowDetail>> getTv = new Observer<ArrayList<TvShowDetail>>() {
        @Override
        public void onChanged(ArrayList<TvShowDetail> tvShowDetails) {
            collapsingToolbarLayout.setTitle(tvShowDetails.get(0).getTitle());
            tvTitle.setText(tvShowDetails.get(0).getTitle());
            tvRelease.setText(tvShowDetails.get(0).getRelease());
            tvGenres.setText(tvShowDetails.get(0).getGenres());
            tvOverview.setText(tvShowDetails.get(0).getOverview());
            rbTvDetail.setRating((float)(tvShowDetails.get(0).getRating()/2));

            Glide.with(TvDetailActivity.this)
                    .load(BuildConfig.POSTER_URL+tvShowDetails.get(0).getPoster())
                    .into(imgPoster);

            Glide.with(TvDetailActivity.this)
                    .load(BuildConfig.POSTER_URL+tvShowDetails.get(0).getBackdrop())
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
