package com.samsung.interview.moviedb.demo.ui.presenter.base;

import com.samsung.interview.moviedb.demo.ui.fragment.base.ILoadDataView;

/**
 * Class used as base class for presenter layer
 *
 * Created by licha on 3/24/2018.
 */

public abstract class ListablePresenter<T> extends Presenter<T> {

    public ListablePresenter(ILoadDataView<T> view) {
        super(view);
    }
    public abstract void getMoreData(int page);
    public abstract void refresh();
}