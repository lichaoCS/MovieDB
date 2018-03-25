package com.samsung.interview.moviedb.demo.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.samsung.interview.moviedb.demo.Utils;
import com.samsung.interview.moviedb.demo.data.exception.FailedGettingDataException;
import com.samsung.interview.moviedb.demo.data.exception.NetworkConnectionException;
import com.samsung.interview.moviedb.demo.data.repository.base.ICloudMovieRepository;
import com.samsung.interview.moviedb.demo.data.repository.base.MovieRepositoryFactory;
import com.samsung.interview.moviedb.demo.ui.fragment.base.ILoadDataView;
import com.samsung.interview.moviedb.demo.ui.presenter.base.Presenter;

/**
 * Created by licha on 3/25/2018.
 */

public class SplashPresenter extends Presenter<Void> {

    private static final int STATUS_OK = 0;
    private static final int STATUS_ERROR = 1;
    private static final int STATUS_NO_CONNECTION = 2;

    public SplashPresenter(ILoadDataView<Void> view) {
        super(view);
    }

    @Override
    public void execute() {
        new LoadDataTask().execute();
    }

    /**
     * Download data from the cloud if the local repo (content provider) is empty
     */
    private class LoadDataTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected void onPostExecute(Integer status) {
            super.onPostExecute(status);

            switch (status) {
                case STATUS_OK:
                    view.setData(null);
                    break;
                case STATUS_NO_CONNECTION:
                    view.showNoConnection();
                    break;
                case STATUS_ERROR:
                default:
                    view.showError("Ooops! Loading Data Error!");
            }
        }
        @Override
        protected Integer doInBackground(Void... params) {

            try {
                ICloudMovieRepository cloudRepo = MovieRepositoryFactory.getCloudRepository(view.getViewContext());
                Thread.sleep(2000);
                Utils.saveGenresList(cloudRepo.getGenres());

                return STATUS_OK;
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
                return STATUS_ERROR;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return STATUS_ERROR;
            }catch (NetworkConnectionException e) {
                e.printStackTrace();
                return STATUS_NO_CONNECTION;
            }
        }
    }
}
