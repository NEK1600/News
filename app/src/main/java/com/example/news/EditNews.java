package com.example.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.news.data.model.ResponseN;
import com.example.news.data.remote.ApiService;
import com.example.news.data.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditNews extends AppCompatActivity {
    private  Integer fName;
    private TextView textDisc;
    private TextView textTitle;
    private TextView textSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_news);

        textDisc=findViewById(R.id.newsTx);
        textTitle=findViewById(R.id.titleTx);
        textSource=findViewById(R.id.sourceTx);

        initRetrofit();

        Intent intent = getIntent();
        fName = intent.getIntExtra("id",0);


    }

    private void initRetrofit(){
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<ResponseN> call = apiService.getNews();
        call.enqueue(new Callback<ResponseN>() {
            @Override
            public void onResponse(Call<ResponseN> call, Response<ResponseN> response) {
                textDisc.setText(response.body().getArticles().get(fName).getDescription());
                textTitle.setText(response.body().getArticles().get(fName).getTitle());
                textSource.setText("Источник "+response.body().getArticles().get(fName).getSource().getName());

                Log.d("TAG","Response == "+fName);

            }

            @Override
            public void onFailure(Call<ResponseN> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }


    public void OnClickReaderNews(View view) {
        BlankFragment fragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("number", fName);
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }



}





















