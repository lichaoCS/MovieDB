package com.samsung.interview.moviedb.demo;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Singleton class (note that we have one instance per application process) that plays the role
 * of Service Locator, decoupling the remaining code base of the concrete service provider types.
 *
 * Created by licha on 3/24/2018.
 */

public class MovieDBApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }



    public static Context getAppContext(){

        return appContext;
    }

}
