package com.mozeeb.newsapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.newsapi.DetailActivity;
import com.mozeeb.newsapi.R;
import com.mozeeb.newsapi.responseapi.ArticlesItem;
import com.mozeeb.newsapi.responseapi.model.BeritaItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder> {

    //TODO membuat variable yang dibutuhkan
    private Context context;
    private List<ArticlesItem> beritaList;

    //TODO generate constructor
    public AdapterBerita(Context context, List<ArticlesItem> beritaList) {
        this.context = context;
        this.beritaList = beritaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //TODO inflate layout dari xml untuk tampilannya
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_berita, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        //TODO menamoilkan data pada list
        viewHolder.tvPenulis.setText(beritaList.get(i).getAuthor());
        viewHolder.tvJudul.setText(beritaList.get(i).getTitle());
        viewHolder.tvTglterbit.setText(beritaList.get(i).getPublishedAt());

        Glide.with(context).load(beritaList.get(i).getUrlToImage()).into(viewHolder.imgBerita);

        //TODO membuat respon click pada list untuk berpindah halaman ke detailActivity
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO membuat variable dari berita item agar bisa di gunakan
                BeritaItem beritaItem = new BeritaItem();

                //TODO mengambil data dari berita item
                beritaItem.setTitle(beritaList.get(i).getTitle());
                beritaItem.setUrlToImage(beritaList.get(i).getUrlToImage());
                beritaItem.setContent(beritaList.get(i).getContent());
                beritaItem.setPublishedAt(beritaList.get(i).getPublishedAt());

                //TODO melakukan perpindahan halaman
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_OBJ,beritaItem);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beritaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //TODO hasil gerate butterknife
        @BindView(R.id.imgBerita)
        ImageView imgBerita;
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_penulis)
        TextView tvPenulis;
        @BindView(R.id.tv_tglterbit)
        TextView tvTglterbit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
