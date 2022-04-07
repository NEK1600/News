package com.example.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.EditNews;
import com.example.news.R;
import com.example.news.data.model.ArticlesItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterNew extends  RecyclerView.Adapter<AdapterNew.Adapter2> {

    private Context context;
    private List<ArticlesItem> listAdapter;


    public AdapterNew(Context context){
        this.context = context;
        listAdapter = new ArrayList<>();
    }

    @NonNull
    @Override
    public Adapter2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_element, parent, false);
        return new Adapter2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2 holder, int position) {
        holder.set(listAdapter.get(position).getTitle());
        if(listAdapter.get(position).getUrlToImage()==null){

        }else{
            Picasso.get()
                    .load(listAdapter.get(position).getUrlToImage())
                    .resize(200, 200)
                    .centerCrop()
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditNews.class);
                intent.putExtra("id", holder.getAdapterPosition());


                view.getContext().startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return listAdapter.size();
    }

    public static class Adapter2 extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;


        public Adapter2(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tx);
            imageView = itemView.findViewById(R.id.im);

        }
        public void set(String news){
            textView.setText(news);
        }



    }


    public void update(List<ArticlesItem> list2){
        listAdapter.clear();
        listAdapter.addAll(list2);
        notifyDataSetChanged();
    }



}
