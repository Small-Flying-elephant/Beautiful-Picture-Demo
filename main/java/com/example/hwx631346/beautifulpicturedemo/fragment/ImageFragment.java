package com.example.hwx631346.beautifulpicturedemo.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import com.example.hwx631346.beautifulpicturedemo.R;
import com.example.hwx631346.beautifulpicturedemo.adapter.MZAdapter;
import com.example.hwx631346.beautifulpicturedemo.bean.SisterBean;
import com.example.hwx631346.beautifulpicturedemo.network.SisterApi;
import com.example.hwx631346.beautifulpicturedemo.utils.ResUtils;
import com.example.hwx631346.beautifulpicturedemo.utils.ToastUtils;

import java.util.ArrayList;

public class ImageFragment extends Fragment {

    private SwipeRefreshLayout srl_refresh;
    private FloatingActionButton fab_top;
    private RecyclerView rec_mz;
    private int mCurPage = 1;
    private ArrayList<SisterBean> mData;
    private ArrayList<SisterBean> mDatas;
    private static final int SPANCOUNT = 2;
    private final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private MZAdapter mAdapter;
    private Handler uiHandler;


    public static ImageFragment newInstance() {
        return new ImageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragement, null);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mData = new ArrayList<>();
        Log.d("linsheng", "onViewCreated: " + mData);
        mAdapter = new MZAdapter(getActivity(), mData);
        rec_mz.setAdapter(mAdapter);
        srl_refresh.setRefreshing(true);
        fetchMZ(true);
        Log.d("linsheng", "onViewCreated: ");
    }

    private void initView(View view) {
        srl_refresh = view.findViewById(R.id.srl_refresh);
        rec_mz = view.findViewById(R.id.rec_mz);
        fab_top = view.findViewById(R.id.fab_top);
        Log.d("linsheng", "initView: ");
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("linsheng", "onRefresh: ");
                mCurPage = 1;
                fetchMZ(true);
                Log.d("linsheng", "onRefresh: ");
            }
        });
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), SPANCOUNT);
        rec_mz.setLayoutManager(layoutManager);
        rec_mz.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager1 = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//加载更多
                    if (layoutManager.getItemCount() - recyclerView.getChildCount() <= layoutManager.findFirstVisibleItemPosition()) {
                        ++mCurPage;
                        fetchMZ(false);
                        Log.d("linsheng", "onScrollStateChanged: ");
                    }
                }

                if (layoutManager.findFirstVisibleItemPosition() != 0) {
                    fabInAnim();
                } else {
                    fabOutAnim();
                }
            }
        });

        fab_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager manager = (LinearLayoutManager) rec_mz.getLayoutManager();
                //如果超过50项直接跳到开头，不然要滚好久
                if (manager.findFirstVisibleItemPosition() < 50) {
                    rec_mz.smoothScrollToPosition(0);
                } else {
                    rec_mz.scrollToPosition(0);
                    fabOutAnim();
                }
            }
        });
    }

    private void fabOutAnim() {
        if (fab_top.getVisibility() == View.VISIBLE) {
            ViewCompat.animate(fab_top).scaleX(0.0F).scaleY(0.0F).alpha(0.0F)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {

                }

                @SuppressLint("RestrictedApi")
                @Override
                public void onAnimationEnd(View view) {
                    fab_top.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(View view) {

                }
            }).start();
        }
    }

    @SuppressLint("RestrictedApi")
    private void fabInAnim() {
        if (fab_top.getVisibility() == View.GONE) {
            fab_top.setVisibility(View.VISIBLE);
            ViewCompat.animate(fab_top).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null).start();
        }
    }

    private void fetchMZ(boolean isRefresh) {
        new SisterTask(mCurPage).execute();
        mDatas = new ArrayList<>();
        uiHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        Log.d("linsheng", "handleMessage:asdafaf " + mData.size());
                        if (mDatas != null && mDatas.size() > 0) {
                            if (isRefresh) {
                                Log.d("linsheng", "handleMessage: " + mData.size());
                                mAdapter.addAll(mDatas);
                                ToastUtils.shortToast(ResUtils.getString(R.string.refresh_success));
                            } else {
                                Log.d("linsheng", "handleMessage:1234544 ");
                                mAdapter.loadMore(mDatas);
                                String mssg = String.format(ResUtils.getString(R.string.load_more_num), mData.size(), "妹子");
                                ToastUtils.shortToast(mssg);
                            }
                            srl_refresh.setRefreshing(false);
                            break;
                        }
                }
            }
        };
    }

        private class SisterTask extends AsyncTask<Void, Void, ArrayList<SisterBean>> {

            private int page;

            public SisterTask(int page) {
                this.page = page;
            }

            @Override
            protected ArrayList<SisterBean> doInBackground(Void... params) {
                Log.d("linsheng", "doInBackground: ");
                return SisterApi.fetchSister(20, page);
            }

            @Override
            protected void onPostExecute(ArrayList<SisterBean> sisters) {
                super.onPostExecute(sisters);
                mData.clear();
                mData.addAll(sisters);
                mDatas.addAll(sisters);
                Log.d("linsheng", "onPostExecute: " + mData);
                try{
                    // 子线程执行完毕的地方，利用主线程的handler发送消息
                    Message msg = new Message();
                    msg.what = 1;
                    uiHandler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
