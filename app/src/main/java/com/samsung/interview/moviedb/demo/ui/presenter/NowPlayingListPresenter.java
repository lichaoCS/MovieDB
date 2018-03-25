package com.samsung.interview.moviedb.demo.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.samsung.interview.moviedb.demo.data.exception.FailedGettingDataException;
import com.samsung.interview.moviedb.demo.data.exception.NetworkConnectionException;
import com.samsung.interview.moviedb.demo.data.repository.base.ICloudMovieRepository;
import com.samsung.interview.moviedb.demo.data.repository.base.MovieRepositoryFactory;
import com.samsung.interview.moviedb.demo.model.Movie;
import com.samsung.interview.moviedb.demo.ui.fragment.base.ILoadDataView;
import com.samsung.interview.moviedb.demo.ui.fragment.base.MovieListableFragment;
import com.samsung.interview.moviedb.demo.ui.presenter.base.ListablePresenter;

import java.util.List;

/**
 * Presenter class for the NowPlayingFragment
 *
 * Created by licha on 3/24/2018.
 */

public class NowPlayingListPresenter extends ListablePresenter<List<Movie>> {

    public NowPlayingListPresenter(ILoadDataView<List<Movie>> view) {
        super(view);
    }

    @Override
    public void execute() {
        new LoadDataTask().execute();
    }

    @Override
    public void getMoreData(int page) {
        new LoadMorePagesTask().execute(page);
    }

    @Override
    public void refresh() {
        new LoadDataTask().execute();
    }

    /**
     * Load movie list in a worker thread using an AsyncTask from Content Provider
     */
    private class LoadDataFromDatabaseTask extends AsyncTask<Void, Void, List<Movie>> {


        @Override
        protected List<Movie> doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: Getting movies from content provider");
            // TODO
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);
            view.setData(list);
        }
    }

    /**
     * Load movie list in a worker thread using an AsyncTask from Web
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<Movie>> {

        private Exception exception = null;

        @Override
        protected List<Movie> doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: Getting movies from content provider");
            ICloudMovieRepository repo = null;
            try {
                //Thread.sleep(1000);
                repo = MovieRepositoryFactory.getCloudRepository(view.getViewContext());
                return repo.getNowPlayingMovies(1);
            } catch (NetworkConnectionException e) {
                exception = e;
                e.printStackTrace();
            }catch (FailedGettingDataException e) {
                exception = e;
            }
            return null;

        }

        @Override
        protected void onPostExecute(List<Movie> list) {

            super.onPostExecute(list);
            if(exception == null && list!=null)
                view.setData(list);
            else if(exception instanceof NetworkConnectionException)
                view.showNoConnection();
            else if(exception instanceof FailedGettingDataException)
                view.showError("Ooops! Loading Data Error!");
            else if(exception instanceof InterruptedException)
                view.showError("Ooops! Thread Sleeping Error !");
        }
    }

    /**
     * Load specific page movie list and adds to the list in a worker thread using an AsyncTask
     */
    private class LoadMorePagesTask extends AsyncTask<Integer, Void, List<Movie>> {
        private Exception exception = null;
        @Override
        protected List<Movie> doInBackground(Integer... params) {
            Log.d(TAG, "doInBackground: Getting movies from web");


            try {
                ICloudMovieRepository repo = MovieRepositoryFactory.getCloudRepository(view.getViewContext());
                return repo.getNowPlayingMovies(params[0]);
            }catch (NetworkConnectionException e) {
                exception = e;
                e.printStackTrace();
                return  null;
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "doInBackground: failed getting data from web");
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> list) {
            super.onPostExecute(list);
            if(exception == null && list!=null)
                ((MovieListableFragment)view).addMoreData(list);
            else if(exception instanceof NetworkConnectionException)
                view.showNoConnection();
            else if(exception instanceof FailedGettingDataException)
                view.showError("Ooops! Loading Data Error!");
            else if(exception instanceof InterruptedException)
                view.showError("Ooops! Thread Sleeping Error !");

        }
    }
}
