package com.example.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.news.data.model.ResponseN;
import com.example.news.data.remote.ApiService;
import com.example.news.data.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlankFragment extends Fragment {
    private String textUrl;
    EditNews editNews;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_blank, container, false);
        WebView webView = (WebView)v.findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Integer anInt = getArguments().getInt("number");


        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ResponseN> call = apiService.getNews();
        call.enqueue(new Callback<ResponseN>() {
            @Override
            public void onResponse(Call<ResponseN> call, Response<ResponseN> response) {
                textUrl = response.body().getArticles().get(anInt).getUrl();
                webView.loadUrl(textUrl);
                Log.d("TAG","Response = "+anInt);
            }

            @Override
            public void onFailure(Call<ResponseN> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        return v;

    }

}