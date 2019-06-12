package com.example.hwx631346.beautifulpicturedemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hwx631346.beautifulpicturedemo.PictureDetailActivity;
import com.example.hwx631346.beautifulpicturedemo.R;
import com.example.hwx631346.beautifulpicturedemo.bean.SisterBean;
import com.example.hwx631346.beautifulpicturedemo.network.SisterApi;

import java.util.ArrayList;

public class MZAdapter extends RecyclerView.Adapter<MZAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SisterBean> mzs;

    public void addAll(ArrayList<SisterBean> data){
        mzs = data;
        notifyDataSetChanged();
    }

    public void loadMore(ArrayList<SisterBean> data){
        mzs = data;
        notifyDataSetChanged();
    }


    public MZAdapter(Context mContext, ArrayList<SisterBean> mzs) {
        this.mContext = mContext;
        this.mzs = mzs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mz, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mzs.get(position));
    }

    @Override
    public int getItemCount() {
        return mzs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_content;

        ViewHolder(View itemView) {
            super(itemView);
            img_content = itemView.findViewById(R.id.img_content);
        }

        void bind(final SisterBean data) {
            Glide.with(mContext)
                    .load(data.getUrl())
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(img_content);
            img_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        SisterApi.run(data.getUrl());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    Intent intent = new Intent(mContext, PictureDetailActivity.class);
//                    intent.putExtra("pic_url", data.getUrl());
//                    mContext.startActivity(intent);
                }
            } );
        }
    }
}