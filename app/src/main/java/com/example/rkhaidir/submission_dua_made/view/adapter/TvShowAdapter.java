package com.example.rkhaidir.submission_dua_made.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rkhaidir.submission_dua_made.BuildConfig;
import com.example.rkhaidir.submission_dua_made.R;
import com.example.rkhaidir.submission_dua_made.model.TvShow;
import com.example.rkhaidir.submission_dua_made.view.activity.TvDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TvShow> tvShows;

    public TvShowAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<TvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TvShow tvShow = getTvShows().get(i);
        viewHolder.tvTitle.setText(tvShow.getTitle());
        viewHolder.tvYears.setText(tvShow.getRelease());
        viewHolder.rbTv.setRating((float)(tvShow.getRate()/2));

        Glide.with(context)
                .load(BuildConfig.POSTER_URL+tvShow.getBackdrop())
                .into(viewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getTvShows().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_poster)
        ImageView imgPoster;

        @BindView(R.id.txt_title)
        TextView tvTitle;

        @BindView(R.id.txt_years)
        TextView tvYears;

        @BindView(R.id.rb_rate)
        RatingBar rbTv;

        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    Intent intent = new Intent(context, TvDetailActivity.class);
                    intent.putExtra("tv", tvShows.get(i));
                    context.startActivity(intent);
                }
            });
        }
    }
}
