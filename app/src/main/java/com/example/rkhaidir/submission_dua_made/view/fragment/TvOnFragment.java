package com.example.rkhaidir.submission_dua_made.view.fragment;


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
import com.example.rkhaidir.submission_dua_made.model.TvShow;
import com.example.rkhaidir.submission_dua_made.view.adapter.TvShowAdapter;
import com.example.rkhaidir.submission_dua_made.viewmodel.TvShowViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvOnFragment extends Fragment {

    @BindView(R.id.rv_tv_on)
    RecyclerView rvTvOn;

    @BindView(R.id.pb_tv_on)
    ProgressBar progressBar;

    private TvShowAdapter tvShowAdapter;
    private TvShowViewModel tvShowViewModel;

    public TvOnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_on, container, false);
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
        rvTvOn.setLayoutManager(new LinearLayoutManager(getActivity()));

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvOn().observe(this, getTv);

        tvShowViewModel.setTvOn();
        showLoading(true);
    }

    private Observer<ArrayList<TvShow>> getTv = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShows) {
            tvShowAdapter = new TvShowAdapter(getActivity());
            tvShowAdapter.setTvShows(tvShows);
            rvTvOn.setAdapter(tvShowAdapter);
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
