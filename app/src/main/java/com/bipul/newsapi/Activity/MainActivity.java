package com.bipul.newsapi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.bipul.newsapi.Adapter.CustomAdapter;
import com.bipul.newsapi.Model.About;
import com.bipul.newsapi.R;
import com.bipul.newsapi.Service.BaseUrl;
import com.bipul.newsapi.Service.Endpoints;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        Endpoints endpoints = BaseUrl.getRetrofitInstance().create(Endpoints.class);
        Call<List<About>> call = endpoints.getalltext();
        call.enqueue(new Callback<List<About>>() {
            @Override
            public void onResponse(Call<List<About>> call, Response<List<About>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<About>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<About> abouts) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this,abouts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}