package com.mozeeb.newsapi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mozeeb.newsapi.adapter.AdapterBerita;
import com.mozeeb.newsapi.network.ApiService;
import com.mozeeb.newsapi.network.ConfigRetrofit;
import com.mozeeb.newsapi.responseapi.ArticlesItem;
import com.mozeeb.newsapi.responseapi.ResponseBerita;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //TODO membuat funtion
        tampilBerita();

        //TODO melakukan get data ketika di refresh
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tampilBerita();
            }
        });
    }

    private void tampilBerita() {
        //TODO membuat object configretrofit
        ConfigRetrofit config = new ConfigRetrofit();
        ApiService api = config.service;

        //TODO Melakukan loading keta data belum tampil
        refreshLayout.setRefreshing(true);

        //TODO get data dari API
        api.getBerita().enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()) {

                    String status = response.body().getStatus();

                    if (status.equals(status)) {
                        ResponseBerita dataSemua = response.body();

                        //TODO menghentikan loading ketika data tampil
                        refreshLayout.setRefreshing(false);

                        List<ArticlesItem> data_berita = dataSemua.getArticles();

                        AdapterBerita adapterBerita = new AdapterBerita(MainActivity.this, data_berita);

                        rvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        rvMain.setAdapter(adapterBerita);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                //TODO menghentikan loading ketika data tidak tampil
                refreshLayout.setRefreshing(false);
            }
        });
    }
}
