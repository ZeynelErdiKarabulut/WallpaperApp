package com.example.waalapp.adapter;

import android.view.View;

import androidx.paging.PagedList;

import com.example.waalapp.model.Waal;

public interface OnItemClickListener {
    void onItemClick(View v, Waal waal,int pos);
}
