package com.example.fragmentjava;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFileSelectedListener {

    private FrameLayout listContainer, detailContainer;
    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (isLandscape) {
            LinearLayout rootLayout = new LinearLayout(this);
            rootLayout.setOrientation(LinearLayout.HORIZONTAL);
            rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));

            listContainer = new FrameLayout(this);
            listContainer.setId(ViewID.LIST_CONTAINER);
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            rootLayout.addView(listContainer, lp1);

            detailContainer = new FrameLayout(this);
            detailContainer.setId(ViewID.DETAIL_CONTAINER);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 2f);
            rootLayout.addView(detailContainer, lp2);

            setContentView(rootLayout);

            getSupportFragmentManager().beginTransaction()
                    .replace(ViewID.LIST_CONTAINER, new ListFragment())
                    .replace(ViewID.DETAIL_CONTAINER, DetailFragment.newInstance(R.raw.file1))
                    .commit();
        } else {
            listContainer = new FrameLayout(this);
            listContainer.setId(ViewID.LIST_CONTAINER);
            setContentView(listContainer);

            getSupportFragmentManager().beginTransaction()
                    .replace(ViewID.LIST_CONTAINER, new ListFragment())
                    .commit();
        }
    }

    @Override
    public void onFileSelected(int fileResId) {
        if (isLandscape) {
            DetailFragment detailFragment = DetailFragment.newInstance(fileResId);
            getSupportFragmentManager().beginTransaction()
                    .replace(ViewID.DETAIL_CONTAINER, detailFragment)
                    .commit();
        } else {
            DetailFragment detailFragment = DetailFragment.newInstance(fileResId);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(ViewID.LIST_CONTAINER, detailFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}

class ViewID {
    static final int LIST_CONTAINER = 1000;
    static final int DETAIL_CONTAINER = 1001;
}

