package com.example.administrator.heroreview.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.heroreview.R;
import com.example.administrator.heroreview.model.Hero;
import com.example.administrator.heroreview.model.HeroList;
import com.example.administrator.heroreview.service.HeroListCallService;
import com.example.administrator.heroreview.view.adapter.HeroListAdapter;
import com.example.administrator.heroreview.view.adapter.ViewPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroListFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView heroList;
    @BindView(R.id.view_pager_img)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;

    public static HeroListFragment newInstance() {
        return new HeroListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero_list, container, false);
        ButterKnife.bind(this, view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://coemygroup.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroListCallService callService = retrofit.create(HeroListCallService.class);
        Call<HeroList> call = callService.getHeroList();

        call.enqueue(new Callback<HeroList>() {
            @Override
            public void onResponse(Call<HeroList> call, Response<HeroList> response) {
                List<Hero> heroes = response.body().getElements();

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                HeroListAdapter heroListAdapter = new HeroListAdapter(getContext(), heroes);
                heroList.setLayoutManager(layoutManager);
                heroList.setAdapter(heroListAdapter);
                viewPager.setAdapter(new ViewPagerAdapter(getContext(), heroes));
                indicator.setViewPager(viewPager);
            }

            @Override
            public void onFailure(Call<HeroList> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
