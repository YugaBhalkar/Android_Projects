package com.example.demoapp.tabs;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.example.demoapp.RoomDatabase.Artical;
import com.example.demoapp.RoomDatabase.ArticalDataBase;
import com.example.demoapp.databinding.ActivityTabBinding;
import com.example.demoapp.retrofit.ApiInterface;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Tab_Activity extends AppCompatActivity {

    private ActivityTabBinding binding;
    ViewPagerAdapter viewPagerAdapter;
    ArticalDataBase articalDataBase;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final ViewPager viewPager = binding.viewPager;
        final TabLayout tabLayout = binding.tabLayout;
        progressBar = binding.loading;
        articalDataBase = ArticalDataBase.getInstance(this);



        articalDataBase.articalDao().getArticals().observe(this, new Observer<List<Artical>>() {
            @Override
            public void onChanged(List<Artical> articals) {
                if (articals == null || articals.size() == 0){
                    progressBar.setVisibility(View.VISIBLE);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://jsonplaceholder.typicode.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                    Call<List<Artical>> call = apiInterface.getPosts();
                    call.enqueue(new Callback<List<Artical>>() {
                        @Override
                        public void onResponse(Call<List<Artical>> call, Response<List<Artical>> response) {
                            List<Artical> postList = response.body();
                            for(int i=0 ; i<postList.size();i++){
                                articalDataBase.articalDao().insertArticals(new Artical(postList.get(i).getUserId(),
                                        postList.get(i).getId(),postList.get(i).getTitle(),postList.get(i).getBody(),"1"));
                            }

                            progressBar.setVisibility(View.GONE);
                            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
                            viewPagerAdapter.addFragment(new PostFragment(postList),"Post");
                            viewPagerAdapter.addFragment(new FavFragment(),"Favourite");
                            viewPager.setAdapter(viewPagerAdapter);
                            tabLayout.setupWithViewPager(viewPager);
                        }

                        @Override
                        public void onFailure(Call<List<Artical>> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Tab_Activity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
                    viewPagerAdapter.addFragment(new PostFragment(articals),"Post");
                    viewPagerAdapter.addFragment(new FavFragment(),"Favourite");
                    viewPager.setAdapter(viewPagerAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
            }
        });

    }
}
