package com.bipul.newsapi.Service;

import com.bipul.newsapi.Model.About;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Endpoints {



    @GET("/wp-news/wp-json/wp/v2/posts")
    Call<List<About>> getalltext();
}
