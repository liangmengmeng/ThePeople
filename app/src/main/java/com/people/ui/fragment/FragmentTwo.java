package com.people.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.people.R;
import com.people.api.bean.SectionListBean;
import com.people.api.ZhiHuApi;
import com.people.ui.adapter.DayNewsAdapter;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * autour: 梁萌萌
 * date: 2017/1/10 9:31 
 * update: 2017/1/10
 */
public class FragmentTwo extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<SectionListBean.DataBean> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_two, container, false);
        //初始化RecyclerView 设置布局管理器
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fm2_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //初始化数据
        initData();
        return view;
    }
    //请求数据
    private void initData() {
        Retrofit mRetrofit = new Retrofit.Builder().baseUrl("http://news-at.zhihu.com/api/4/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ZhiHuApi section = mRetrofit.create(ZhiHuApi.class);
        section.getSectionList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SectionListBean>() {
                    @Override
                    public void onCompleted() {
                        //设置适配器

                        DayNewsAdapter adapter = new DayNewsAdapter(getActivity(),list);
                        mRecyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener(new DayNewsAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(getActivity(), "您点击了第" + (position+1) + "条目", Toast.LENGTH_SHORT).show();
                            }
                        });
                        
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(SectionListBean sectionListBean) {
                        list.addAll(sectionListBean.getData());

                    }
                });



    }

}
