package com.example.administrator.heroreview.service;

import com.example.administrator.heroreview.model.HeroList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface HeroListCallService {
    @GET("test-mobile/iOS/json/test2.json")
    Call<HeroList> getHeroList();
}
