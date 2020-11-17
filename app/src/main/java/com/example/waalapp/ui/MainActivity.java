package com.example.waalapp.ui;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.waalapp.R;
import com.example.waalapp.Statics;
import com.example.waalapp.adapter.OnItemClickListener;
import com.example.waalapp.adapter.WaalAdapter;
import com.example.waalapp.model.Waal;
import com.example.waalapp.viewmodel.WaalViewModel;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private WaalViewModel waalViewModel;
    private WaalAdapter adapter;
    private RecyclerView recyclerview;
    GridLayoutManager manager;
    PagedList<Waal> waalPagedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Statics.actionbarcentertitle(this, getDrawable(R.drawable.ic_arrow_back), getString(R.string.app_name), Color.WHITE, false, false);

        recyclerview= findViewById(R.id.recyclerView);
        adapter = new WaalAdapter();
        manager  = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(manager);
        recyclerview.setHasFixedSize(true);
        waalViewModel = ViewModelProviders.of(this).get(WaalViewModel.class);


        waalViewModel.userPagedList.observe(this, new Observer<PagedList<Waal>>() {
            @Override
            public void onChanged(PagedList<Waal> waals) {
                waalPagedList = waals;
                adapter.submitList(waals);
            }
        });

        recyclerview.setAdapter(adapter);

        //adapterdeki elemana tıklama için custom click interfacei içine farklı metodları ovveride edebilirim.
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, Waal waal,int pos) {
               // waalPagedList.remove(pos);
               // adapter.notifyDataSetChanged();

                ImageView imageStart = (ImageView) v.findViewById(R.id.imageView);
                Intent intent = new Intent(MainActivity.this,WaalDetail.class);
                String transitionName = "imageTransition";
                ViewCompat.setTransitionName(imageStart, transitionName);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, imageStart, transitionName);
                intent.putExtra("waal" , waal);
                startActivity(intent,options.toBundle());

            }

        });






    }




}
