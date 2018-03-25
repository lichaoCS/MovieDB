package com.samsung.interview.moviedb.demo.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.samsung.interview.moviedb.demo.R;
import com.samsung.interview.moviedb.demo.ui.activity.base.ToolbarActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.samsung.interview.moviedb.demo.ui.fragment.NowPlayingListFragment;
import com.samsung.interview.moviedb.demo.ui.fragment.UpcomingMovieListFragment;

/**
 * Launcher activity that displays the tabs and fragments containing the movies lists
 */
public class MainActivity extends ToolbarActivity implements ViewPager.OnPageChangeListener {

    protected ViewPager viewPager;
    protected int tabPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setPager();
    }

    @Override
    protected int getToolbarLayout() {
        return R.layout.activity_main;
    }

    /**
     * Setup the ViewPager with tabs using SmartTabs Lib
     */
    private void setPager() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add(R.string.page_nowplaying,
                                NowPlayingListFragment.class)
                        .add(R.string.page_upcoming,
                                UpcomingMovieListFragment.class)
                        .create());

        //Set the fragments pager
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        //Set the tabs names pager
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.tabs);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected: " + position);
        tabPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    /*
   |--------------------------------------------------------------------------
   | Save state
   |--------------------------------------------------------------------------
   */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int pos = savedInstanceState.getInt("tab_pos");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("tab_pos", tabPosition);

        super.onSaveInstanceState(outState);
    }
}

