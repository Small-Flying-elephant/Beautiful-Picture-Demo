package com.example.hwx631346.beautifulpicturedemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hwx631346.beautifulpicturedemo.PictureDetailActivity;
import com.example.hwx631346.beautifulpicturedemo.R;
import com.example.hwx631346.beautifulpicturedemo.bean.SisterBean;

import java.util.ArrayList;

public class MZAdapter extends RecyclerView.Adapter<MZAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SisterBean> mzs;

    public void addAll(ArrayList<SisterBean> data){
        Log.d("linsheng", "addAll: " +data);
        mzs.clear();
        mzs.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(ArrayList<SisterBean> data){
        mzs.addAll(data);
    }


    public MZAdapter(Context mContext, ArrayList<SisterBean> mzs) {
        this.mContext = mContext;
        this.mzs = mzs;
        Log.d("linsheng", "MZAdapter: ");
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
        Log.d("linsheng", "getItemCount: " + mzs.size());
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
                    Intent intent = new Intent(mContext, PictureDetailActivity.class);
                    intent.putExtra("pic_url", data.getUrl());
                    mContext.startActivity(intent);
                }
            } );
        }
    }
}