package com.mozeeb.newsapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.newsapi.responseapi.model.BeritaItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    //TODO generate butterknife
    public static final String EXTRA_OBJ = "obj";
    @BindView(R.id.imgDetail)
    ImageView imgDetail;
    @BindView(R.id.tvJudul)
    TextView tvJudul;
    @BindView(R.id.tvTglTerbit)
    TextView tvTglTerbit;
    @BindView(R.id.tvIsiBerita)
    TextView tvIsiBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        //TODO mengambil data dari adapter
        BeritaItem beritaItem = getIntent().getParcelableExtra(EXTRA_OBJ);

        //TODO mengambil data dari berita item
        String judul = beritaItem.getTitle();
        String gambar = beritaItem.getUrlToImage();
        String tglTerbit = beritaItem.getPublishedAt();
        String isiBerita = beritaItem.getContent();

        //TODO menampilkan data
        tvJudul.setText(judul);
        tvTglTerbit.setText(tglTerbit);
        tvIsiBerita.setText(isiBerita);

        Glide.with(this).load(gambar).into(imgDetail);
    }
}
