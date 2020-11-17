package com.example.waalapp.api.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.waalapp.api.MyApi;
import com.example.waalapp.api.MyClient;
import com.example.waalapp.model.Waal;
import com.example.waalapp.model.WaalResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaalDataSource extends PageKeyedDataSource<Long, Waal> {
    public static int PAGE_SIZE = 20;
    public static long FIRST_PAGE = 1;
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Waal> callback) {
        MyApi api= MyClient.getInstance().getMyApi();
        Call<WaalResponse> call=api.getWallpapers(String.valueOf(1));
        call.enqueue(new Callback<WaalResponse>() {
            @Override
            public void onResponse(Call<WaalResponse> call, Response<WaalResponse> response) {
                WaalResponse waalResponse = response.body();
                if (waalResponse != null && waalResponse.getWaalList() != null) {
                    List<Waal> responseItems = waalResponse.getWaalList();
                    callback.onResult(responseItems, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<WaalResponse> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Waal> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Waal> callback) {
        MyApi api= MyClient.getInstance().getMyApi();
        Call<WaalResponse> call=api.getWallpapers(String.valueOf(params.key));
        call.enqueue(new Callback<WaalResponse>() {
            @Override
            public void onResponse(Call<WaalResponse> call, Response<WaalResponse> response) {
                WaalResponse waalResponse = response.body();
                if (waalResponse != null && waalResponse.getWaalList() != null) {
                    List<Waal> responseItems = waalResponse.getWaalList();
                    callback.onResult(responseItems, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<WaalResponse> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });

    }
}
