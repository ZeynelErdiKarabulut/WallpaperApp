package com.example.waalapp.api.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.waalapp.model.Waal;

public class WaalDataSourceFactory extends DataSource.Factory<Long, Waal> {

    public MutableLiveData<WaalDataSource> waalLiveDataSource =new MutableLiveData<>();

    @Override
    public DataSource<Long, Waal> create() {
        WaalDataSource waalDataSource = new WaalDataSource();
        waalLiveDataSource.postValue(waalDataSource);
        return waalDataSource;
    }



}
