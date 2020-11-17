package com.example.waalapp.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.example.waalapp.api.data.WaalDataSource;
import com.example.waalapp.api.data.WaalDataSourceFactory;
import com.example.waalapp.model.Waal;

public class WaalViewModel extends ViewModel {

    public LiveData<PagedList<Waal>> userPagedList;
    public LiveData<WaalDataSource> liveDataSource;

    public WaalViewModel() {
        init();
    }
    private void init() {
        WaalDataSourceFactory itemDataSourceFactory = new WaalDataSourceFactory();



        liveDataSource = itemDataSourceFactory.waalLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(WaalDataSource.PAGE_SIZE)
                .build();
        userPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();



    }






}