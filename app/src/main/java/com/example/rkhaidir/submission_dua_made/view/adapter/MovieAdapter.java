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
import com.example.rkhaidir.submission_dua_made.model.Movie;
import com.example.rkhaidir.submission_dua_made.view.activity.DetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Movie movie = getMovies().get(i);
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvYears.setText(movie.getRelease());
        viewHolder.rbMovie.setRating((float)(movie.getRate()/2));

        Glide.with(context)
                .load(BuildConfig.POSTER_URL+movie.getBackdrop())
                .into(viewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_poster)
        ImageView imgPoster;

        @BindView(R.id.txt_title)
        TextView tvTitle;

        @BindView(R.id.txt_years)
        TextView tvYears;

        @BindView(R.id.rb_rate)
        RatingBar rbMovie;

        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("movies", movies.get(i));
                    context.startActivity(intent);
                }
            });
        }
    }
}
