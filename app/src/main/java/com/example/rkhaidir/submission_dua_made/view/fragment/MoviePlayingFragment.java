package com.example.rkhaidir.submission_dua_made.view.fragment;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.rkhaidir.submission_dua_made.R;
import com.example.rkhaidir.submission_dua_made.model.Movie;
import com.example.rkhaidir.submission_dua_made.view.adapter.MovieAdapter;
import com.example.rkhaidir.submission_dua_made.viewmodel.MovieViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviePlayingFragment extends Fragment {

    @BindView(R.id.rv_movie_playing)
    RecyclerView rvMovieNowPlaying;

    @BindView(R.id.pb_movie_playing)
    ProgressBar progressBar;

    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;

    public MoviePlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_playing, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvMovieNowPlaying.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMoviesPlaying().observe(this, getMovie);

        movieViewModel.setMoviePlaying();
        showLoading(true);
    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            movieAdapter = new MovieAdapter(getActivity());
            movieAdapter.setMovies(movies);
            rvMovieNowPlaying.setAdapter(movieAdapter);
            showLoading(false);
        }
    };

    private void showLoading(Boolean state) {
        if(state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
