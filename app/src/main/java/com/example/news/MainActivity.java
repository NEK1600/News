package com.example.news;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.adapters.AdapterNew;
import com.example.news.data.model.ResponseN;
import com.example.news.data.remote.ApiService;
import com.example.news.data.remote.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Thread thread;
    private Runnable runnable;

    //заголовки
    private ArrayList<String> titleList = new ArrayList<>();

    private RecyclerView rc;
    private AdapterNew adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc = findViewById(R.id.rc);
        adapter = new AdapterNew(this);
        rc.setLayoutManager(new LinearLayoutManager(this));

        init();


    }

    private void initRetrofit(){
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<ResponseN> call = apiService.getNews();
        call.enqueue(new Callback<ResponseN>() {
            @Override
            public void onResponse(Call<ResponseN> call, Response<ResponseN> response) {
                /*for (int i=0; i<response.body().getArticles().size(); i++){
                    titleList.add(response.body().getArticles().get(i).getTitle());

                }*/
                adapter.update(response.body().getArticles());
                Log.d("TAG","Response == "+response.body().getArticles().get(4).getUrl());

            }

            @Override
            public void onFailure(Call<ResponseN> call, Throwable t) {
               Log.d("TAG","Response = "+t.toString());
            }
        });
    }




    private void init(){
        runnable = new Runnable() {

            @Override
            public void run() {
                initRetrofit();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rc.setAdapter(adapter);
                    }
                });

            }

        };

        thread = new Thread(runnable);
        thread.start();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        //onBackPressed();
    }

}








































































