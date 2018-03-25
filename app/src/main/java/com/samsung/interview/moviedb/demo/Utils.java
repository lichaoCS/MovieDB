package com.samsung.interview.moviedb.demo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.samsung.interview.moviedb.demo.model.Genre;

import java.util.List;
import java.util.Locale;

/**
 * Utility class that provides some useful methods for the app
 * Created by licha on 3/24/2018.
 */

public class Utils {

    /**
     * Checks if there is network connectivity
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ( info != null && info.isAvailable())
            return true;
        else
            return false;


    }

    /**
     * Shows a {@link Toast} message.
     *
     * @param activity Activity to show
     * @param message An string representing a message to be shown.
     */
    public static void showToastMessage(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Create a nice time string from a runtime
     * @param runtime
     * @return
     */
    public static String createRuntimeText(int runtime) {
        if(runtime == 0) return "";

        int hours = runtime / 60;
        int minutes = runtime % 60;
        return hours + "h " + minutes + "m";
    }

    /**R
     * Create a genres string from an array
     * @param genres
     * @return
     */
    public static String createGenreText(Genre[] genres) {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < genres.length; i++) {
            stringbuilder.append(genres[i].getName());
            if (i < genres.length - 1) {
                stringbuilder.append(", ");
            }
        }
        return stringbuilder.toString();
    }

    public static String createGenreTextFromId(int[] genresIds) {
        StringBuilder stringbuilder = new StringBuilder();
        SharedPreferences sp = MovieDBApplication.getAppContext().getSharedPreferences("GenresMap", Context.MODE_PRIVATE);
        for (int i = 0; i < genresIds.length; ++i) {
            String genresString = sp.getString(genresIds[i]+"", null);
            stringbuilder.append(genresString);
            if (i < genresIds.length - 1) {
                stringbuilder.append(", ");
            }
        }
        return stringbuilder.toString();
    }

    public static void saveGenresList(List<Genre> genresList){
        SharedPreferences sharedPreferences = MovieDBApplication.getAppContext().getSharedPreferences("GenresMap", Context.MODE_PRIVATE);
        String onRecordLanguage = sharedPreferences.getString("language",null);
        String currentLanguage = Locale.getDefault().getLanguage();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(onRecordLanguage == null || !onRecordLanguage.equals(currentLanguage)) {
            editor.putString("language", currentLanguage);
            for (Genre element : genresList)
                editor.putString(element.getId() + "", element.getName());
            editor.commit();
        }
    }

    /**
     * Set a dynamic list height, useful for containing two lists in one activity
     * @param mListView
     */
    public static void setDynamicHeight(ListView mListView) {
        ListAdapter mListAdapter = mListView.getAdapter();
        if (mListAdapter == null) {
            // when adapter is null
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdapter.getCount(); i++) {
            View listItem = mListAdapter.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
        mListView.setLayoutParams(params);
        mListView.requestLayout();
    }
}
