package com.samsung.interview.moviedb.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.samsung.interview.moviedb.demo.data.exception.FailedGettingDataException;
import com.samsung.interview.moviedb.demo.data.exception.NetworkConnectionException;
import com.samsung.interview.moviedb.demo.data.repository.base.ICloudMovieRepository;
import com.samsung.interview.moviedb.demo.data.repository.base.MovieRepositoryFactory;

/**
 * Created by licha on 3/25/2018.
 */

public class LocaleChangeReceiver extends BroadcastReceiver {
    public final String TAG = "DEBUG_" + getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.v(TAG, "mReceiver  onReceive  intent.getAction(): " + intent.getAction());
        if (intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED)) {
            Log.e("LocaleChangeReceiver", "Language change");

            try {
                ICloudMovieRepository cloudRepo = MovieRepositoryFactory.getCloudRepository(context);
                Utils.saveGenresList(cloudRepo.getGenres());
            } catch (FailedGettingDataException e) {
                e.printStackTrace();
            }catch (NetworkConnectionException e) {
                e.printStackTrace();
            }
        }
    }
}
