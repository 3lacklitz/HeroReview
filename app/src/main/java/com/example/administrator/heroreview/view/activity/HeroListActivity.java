package com.example.administrator.heroreview.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.heroreview.R;
import com.example.administrator.heroreview.view.fragment.HeroListFragment;

public class HeroListActivity extends AppCompatActivity {
    HeroListFragment heroListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);


        heroListFragment = HeroListFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frg_hero_list, heroListFragment)
                .commit();
    }
}
