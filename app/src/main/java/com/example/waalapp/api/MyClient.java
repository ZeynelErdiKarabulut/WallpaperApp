package com.example.waalapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClient {

    private static final String BASE_URL="https://raw.githubusercontent.com/FiratGURGUR/waal/master/";
    private static MyClient myClient;
    private static Retrofit retrofit;

    private MyClient(){
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized MyClient getInstance(){
        if (myClient == null){
            myClient = new MyClient();
        }
        return myClient;
    }

    public MyApi getMyApi(){
        return retrofit.create(MyApi.class);
    }


}
