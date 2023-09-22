package com.coronavirussefety.covidtracer;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView titleTv;
    private ImageButton refreshBtn;
    private BottomNavigationView navigationView;


    private Fragment homeFragment, statsFragment;
    InfoFragment infoFragment = new InfoFragment();
    private Fragment activeFragment;
    OverviewFragment overviewFragment = new OverviewFragment();
    SymptomsFragment symptomsFragment = new SymptomsFragment();
    PreventionFragment preventionFragment = new PreventionFragment();
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTv = findViewById(R.id.titleTv);
        refreshBtn = findViewById(R.id.refreshBtn);
        navigationView = findViewById(R.id.navigationView);

        initFragments();


        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment.onResume();
                statsFragment.onResume();
//                overviewFragment.onResume();
//                symptomsFragment.onResume();
//                preventionFragment.onResume();
            }
        });

        navigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        statsFragment = new StatsFragment();
        infoFragment = new InfoFragment();
        overviewFragment = new OverviewFragment();
        symptomsFragment = new SymptomsFragment();
        preventionFragment = new PreventionFragment();
        fragmentManager = getSupportFragmentManager();
        activeFragment = homeFragment;

        fragmentManager.beginTransaction()
                .add(R.id.frame, homeFragment, "homeFragment")
                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.frame, statsFragment, "statsFragment")
                .hide(statsFragment)
                .commit();
//        fragmentManager.beginTransaction()
//                .add(R.id.frame, infoFragment, "InfoFragment")
//                .hide(infoFragment)
//                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.frame, overviewFragment, "Overview")
                .hide(overviewFragment)
                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.frame, symptomsFragment, "Symptoms")
                .hide(symptomsFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(R.id.frame, preventionFragment, "Prevention")
                .hide(preventionFragment)
                .commit();
    }

    private void loadHomeFragment() {
        titleTv.setText("Home");
        fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
        activeFragment = homeFragment;
    }

    private void loadStatsFragment() {
        titleTv.setText("COVID19 STATUS");
        fragmentManager.beginTransaction().hide(activeFragment).show(statsFragment).commit();
        activeFragment = statsFragment;
    }
//
//    private void loadinfoFragment() {
//        titleTv.setText("Information");
//        fragmentManager.beginTransaction().show(infoFragment).commit();
//        activeFragment = infoFragment;
//    }

    private void loadOverViewFragment() {
        titleTv.setText("Overview");
        fragmentManager.beginTransaction().show(overviewFragment).commit();
        activeFragment = overviewFragment;
    }

    private void loadSymptomsFragment() {
        titleTv.setText("Symptoms");
        fragmentManager.beginTransaction().show(symptomsFragment).commit();
        activeFragment = symptomsFragment;
    }

    private void loadPreventionFragment() {
        titleTv.setText("Prevention");
        fragmentManager.beginTransaction().show(preventionFragment).commit();
        activeFragment = preventionFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                loadHomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment).commit();
                return true;
            case R.id.nav_stats:
                loadStatsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, statsFragment).commit();
                return true;

            case R.id.overview:
                loadOverViewFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, overviewFragment).commit();
                return true;

            case R.id.synptomps:
                loadSymptomsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, symptomsFragment).commit();
                return true;
            case R.id.prevention:
                loadPreventionFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, preventionFragment).commit();
                return true;

//            case R.id.navinfo:
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame, infoFragment).commit();
//                loadinfoFragment();
//                return true;
        }
        return false;
    }
}