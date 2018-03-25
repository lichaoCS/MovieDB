package com.samsung.interview.moviedb.demo.ui.fragment;

import com.samsung.interview.moviedb.demo.ui.fragment.base.MovieListableFragment;
import com.samsung.interview.moviedb.demo.ui.presenter.NowPlayingListPresenter;
import com.samsung.interview.moviedb.demo.ui.presenter.base.IPresenter;

/**
 * Fragment representing the movies list in theaters
 *
 * Created by licha on 3/24/2018.
 */

public class NowPlayingListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new NowPlayingListPresenter(this);
    }
}
