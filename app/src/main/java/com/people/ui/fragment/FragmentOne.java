package com.people.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.people.R;
import com.people.api.bean.DailyListBean;
import com.people.api.ZhiHuApi;
import com.people.ui.adapter.RecyclerAdapter;
import com.people.ui.zhihu.activity.XQActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * autour: 梁萌萌
 * date: 2017/1/4 20:38
 * update: 2017/1/4
 */
public class FragmentOne extends Fragment implements OnItemClickListener {

    private List<DailyListBean.StoriesBean> recycler_list = new ArrayList<>();
    private RecyclerView mRecyclerView;

    private List<DailyListBean.TopStoriesBean> img = new ArrayList<>();
    private ConvenientBanner convenientBanner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_one, container, false);
        //初始化控件
        initView(view);
        //初始化数据
        initData();
        return view;
    }

    //请求数据
    private void initData() {
               Retrofit mRetrofit=new Retrofit.Builder()
                       .baseUrl("http://news-at.zhihu.com/api/4/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                       .build();
        ZhiHuApi dayNews=mRetrofit.create(ZhiHuApi.class);
        //用Rxjava结合Retrofit请求日报的图片实现图片轮播
        dayNews.getDailyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyListBean>() {
                    @Override
                    public void onCompleted() {
                        //设置图片轮播
                       initImage();
                        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(),recycler_list);
                        mRecyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                               startActivity(new Intent(getActivity(), XQActivity.class));
                            }
                        });
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        img.addAll(dailyListBean.getTop_stories());
                        recycler_list.addAll(dailyListBean.getStories());


                    }
                });
        //只用Retrofit请求
        /*Call<DailyListBean> dailyList = dayNews.getDailyList();
        dailyList.enqueue(new Callback<DailyListBean>() {
            //请求成功
            @Override
            public void onResponse(Call<DailyListBean> call, Response<DailyListBean> response) {
                if (response.isSuccessful()){
                    //把拿到的结果集 添加进集合中
                    img.addAll(response.body().getTop_stories());
                    //设置图片轮播
                    initImage();
                }else {
                    Toast.makeText(getActivity(), "请求图片失败", Toast.LENGTH_SHORT).show();
                }
            }
            //请求失败
            @Override
            public void onFailure(Call<DailyListBean> call, Throwable t) {
            }
        });*/
    }

    //convenientBanner实现图片轮播
    private void initImage() {
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, img)
                .setPageTransformer(ConvenientBanner.Transformer.ZoomInTransformer)
                .startTurning(2000)
                .setOnItemClickListener(this);
    }


    //初始化控件
    private void initView(View view) {
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);

        //设置RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fm1_recycler);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * 轮播图点击事件
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {

        switch (position) {
            case 0:
                Toast.makeText(getActivity(), "点击了第1张图片", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getActivity(), "点击了第2张图片", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getActivity(), "点击了第3张图片", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getActivity(), "点击了第4张图片", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getActivity(), "点击了第5张图片", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //设置图片轮播的布局
    public class NetworkImageHolderView implements CBPageAdapter.Holder<DailyListBean.TopStoriesBean> {
        private ImageView iv;
        private TextView tv;
        //绑定布局
        @Override
        public View createView(Context context) {
            View view = View.inflate(context, R.layout.banner_img_item,null);
            iv = (ImageView) view.findViewById(R.id.banner_image);
            tv = (TextView) view.findViewById(R.id.banner_tv_title);

            return view;
        }
        //添加数据
        @Override
        public void UpdateUI(Context context, int position, DailyListBean.TopStoriesBean data) {
            Glide.with(context)
                    .load(data.getImage())
                    .into(iv);
            tv.setText(img.get(position).getTitle());
        }
    }

    @Override
    public void onPause() {
        img.clear();
        super.onPause();
    }
}
