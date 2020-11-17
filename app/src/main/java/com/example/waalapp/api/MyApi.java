package com.example.waalapp.api;

import com.example.waalapp.model.Waal;
import com.example.waalapp.model.WaalResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {

    @GET("images/{page}")
    Call<WaalResponse> getWallpapers(@Path("page") String page);

}
