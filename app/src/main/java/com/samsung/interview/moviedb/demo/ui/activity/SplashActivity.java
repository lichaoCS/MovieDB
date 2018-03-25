package com.samsung.interview.moviedb.demo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.samsung.interview.moviedb.demo.R;
import com.samsung.interview.moviedb.demo.ui.activity.base.LoadDataActivity;
import com.samsung.interview.moviedb.demo.ui.presenter.SplashPresenter;
import com.samsung.interview.moviedb.demo.ui.presenter.base.IPresenter;

/**
 * This activity must be the launcher one, is purpose is to download data if the content provider
 * is empty
 * Created by licha on 3/25/2018.
 */

public class SplashActivity extends LoadDataActivity<Void> {
    protected RelativeLayout mScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView tvVersion = (TextView) findViewById(R.id.textView3);
        tvVersion.setText("Version: " + getVersionName());
        mScreen = (RelativeLayout) findViewById(R.id.splash_layout);
        AlphaAnimation mAnimation = new AlphaAnimation(0.0f, 1f);
        mAnimation.setDuration(5000);
        mScreen.startAnimation(mAnimation);

        this.presenter.execute();
    }


    @Override
    public void showNoConnection() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(SplashActivity.this);
        normalDialog.setIcon(R.drawable.icon);
        normalDialog.setMessage("No Network Connection...\nPlease Check Your Network!");
        normalDialog.setPositiveButton("Exit APP",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        normalDialog.setNegativeButton("START APP",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setData(null);
                    }
                });
        normalDialog.show();
    }

    @Override
    protected IPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void setData(Void data) {
        //launch home activity when presenter is done
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


    private String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getPackageName(), 0);//
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }
}
