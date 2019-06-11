package com.example.hwx631346.beautifulpicturedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hwx631346.beautifulpicturedemo.adapter.PagerAdapter;

import java.util.ArrayList;

public class GuideActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private Button button;
    private ArrayList<Integer> mViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_layout);
        initView();
        initDate();
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), mViewList));
        viewPager.setOnPageChangeListener(new pageChangeListener());
        button.setOnClickListener(this);
    }

    private void initDate() {
        mViewList = new ArrayList<>();
        mViewList.add(R.mipmap.ic_launcher);
        mViewList.add(R.mipmap.ic_launcher);
        mViewList.add(R.mipmap.ic_launcher);
    }

    private void initView() {
        viewPager = findViewById(R.id.in_viewpager);
        linearLayout = findViewById(R.id.ll);
        button = findViewById(R.id.bt_next);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.bt_next:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                Intent intentDefault = new Intent(this, MainActivity.class);
                startActivity(intentDefault);
                break;
        }
    }

    private class pageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int count = linearLayout.getChildCount();
            if (position == count - 1) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.GONE);
            }
            for (int i = 0; i < count; i++) {
                ImageView iv = (ImageView) linearLayout.getChildAt(i);
                if (i == position) {
                    iv.setBackgroundResource(R.drawable.light_dot);
                } else {
                    iv.setBackgroundResource(R.drawable.gray_dot);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
