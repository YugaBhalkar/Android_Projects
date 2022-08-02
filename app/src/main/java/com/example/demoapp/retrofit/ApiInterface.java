package com.example.demoapp.retrofit;

import com.example.demoapp.RoomDatabase.Artical;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("posts")
    Call<List<Artical>> getPosts();
}
