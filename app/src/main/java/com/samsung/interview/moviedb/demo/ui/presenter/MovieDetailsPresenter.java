package com.samsung.interview.moviedb.demo.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.samsung.interview.moviedb.demo.data.exception.FailedGettingDataException;
import com.samsung.interview.moviedb.demo.data.exception.NetworkConnectionException;
import com.samsung.interview.moviedb.demo.data.repository.base.ICloudMovieRepository;
import com.samsung.interview.moviedb.demo.data.repository.base.MovieRepositoryFactory;
import com.samsung.interview.moviedb.demo.model.MovieDetails;
import com.samsung.interview.moviedb.demo.ui.fragment.base.ILoadDataView;
import com.samsung.interview.moviedb.demo.ui.presenter.base.Presenter;

/**
 * Movie view details presenter
 *
 * Created by licha on 3/25/2018.
 */

public class MovieDetailsPresenter extends Presenter<MovieDetails> {

    private int id;

    public MovieDetailsPresenter(final ILoadDataView<MovieDetails> view) {
        super(view);
    }


    public void setMovieId(int id) {
        this.id = id;
    }


    @Override
    public void execute() {
        new LoadDataTask().execute(id);
    }

    /**
     * Load movie details in a worker thread using an AsyncTask
     */
    private class LoadDataTask extends AsyncTask<Integer, Void, MovieDetails> {

        @Override
        protected MovieDetails doInBackground(Integer... params) {
            Log.d(TAG, "doInBackground: Getting movie from the api");
            ICloudMovieRepository cloudRepo = null;
            try {
                cloudRepo = MovieRepositoryFactory.getCloudRepository(view.getViewContext());
                MovieDetails movie = cloudRepo.getMovieById(params[0]);
                // movie.setBeingFollowed(localRepo.isBeingFollowed(movie.getId()));
                return movie;
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
            }catch (NetworkConnectionException e) {
                Log.d(TAG, "No connection! Error: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(MovieDetails movie) {
            super.onPostExecute(movie);

            if(movie != null)
                view.setData(movie);
            else
                view.showNoConnection();
        }
    }

}
