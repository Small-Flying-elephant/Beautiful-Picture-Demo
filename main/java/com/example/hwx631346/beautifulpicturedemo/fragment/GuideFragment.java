package com.example.hwx631346.beautifulpicturedemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hwx631346.beautifulpicturedemo.R;

public class GuideFragment extends Fragment {
    int imgId ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guide_fragement,null);
        ImageView fragmentView = view.findViewById(R.id.guide_image);
        fragmentView.setBackgroundResource(imgId);
        return view;
    }

    /**
     * 为改fragment设置显示的图片
     */
    public void setImgId(int imgID){
        imgId = imgID;
    }
}
