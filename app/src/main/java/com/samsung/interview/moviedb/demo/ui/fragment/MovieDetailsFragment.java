package com.samsung.interview.moviedb.demo.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsung.interview.moviedb.demo.R;
import com.samsung.interview.moviedb.demo.Utils;
import com.samsung.interview.moviedb.demo.model.MovieDetails;
import com.samsung.interview.moviedb.demo.ui.fragment.base.LoadDataFragment;
import com.samsung.interview.moviedb.demo.ui.presenter.MovieDetailsPresenter;
import com.samsung.interview.moviedb.demo.ui.presenter.base.IPresenter;
import com.squareup.picasso.Picasso;

/**
 * Movie details fragment, shows all the movie details
 * Created by licha on 3/25/2018.
 */

public class MovieDetailsFragment extends LoadDataFragment<MovieDetails> {

    private MovieDetails movie;
    private MovieDetailsPresenter moviePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewContainer = super.onCreateView(inflater, container, savedInstanceState);

        this.moviePresenter = (MovieDetailsPresenter) this.presenter;

        //ask for the movie
        if(!getArguments().isEmpty() && getArguments().getInt("movie_id") != 0) {
            Log.d(TAG, "onCreateView: we have a movie id!");
            this.moviePresenter.setMovieId(getArguments().getInt("movie_id"));
            this.presenter.execute();
        }

        return viewContainer;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_movie;
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void setData(MovieDetails data) {
        this.showResults(false);
        this.movie = data;

        //set toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(data.getTitle());

        ImageView backdropView = (ImageView) this.mainView.findViewById(R.id.movie_back_drop_path);
        ImageView imageView = (ImageView) this.mainView.findViewById(R.id.movie_cover);
        TextView title = (TextView) this.mainView.findViewById(R.id.movie_title_details);
        TextView genre = (TextView) this.mainView.findViewById(R.id.movie_genre);
        TextView rating = (TextView) this.mainView.findViewById(R.id.movie_rating);
        TextView runtime = (TextView) this.mainView.findViewById(R.id.movie_runtime);
        TextView releaseYear = (TextView) this.mainView.findViewById(R.id.movie_release_date);
        TextView voteNum = (TextView) this.mainView.findViewById(R.id.movie_vote_count);
        TextView popularity = (TextView) this.mainView.findViewById(R.id.movie_popularity);
        TextView overview = (TextView) this.mainView.findViewById(R.id.movie_overview);
        TextView homepage = (TextView) this.mainView.findViewById(R.id.movie_homepage);


        Picasso.with(getContext()).load(movie.getBackdrop()).into(backdropView);
        Picasso.with(getContext()).load(movie.getPoster()).into(imageView);
        title.setText(movie.getTitle());
        genre.setText(this.getString(R.string.row_genre, Utils.createGenreText(movie.getGenres())));
        rating.setText(this.getString(R.string.row_rating, movie.getRating()));
        runtime.setText(this.getString(R.string.row_running_time,Utils.createRuntimeText(movie.getRuntime())));
        releaseYear.setText(this.getString(R.string.row_released, movie.getReleaseDate()));
        voteNum.setText(this.getString(R.string.row_vote_count, movie.getVotecount()));
        popularity.setText(this.getString(R.string.row_popularity, movie.getPopularity()));
        overview.setText(movie.getOverview());
        if(movie.getHomepage()!=null)
            homepage.setText(movie.getHomepage());
        else
            homepage.setVisibility(View.GONE);


    }

    @Override
    protected IPresenter createPresenter() {
        return new MovieDetailsPresenter(this);
    }
}

