package com.people.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.people.R;
import com.people.api.WechatApi;
import com.people.api.bean.CoreDataResponse;
import com.people.api.bean.WXItemBean;
import com.people.ui.adapter.WeChatAdapter;

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
 * date: 2017/1/4 20:36
 * update: 2017/1/4
 */
public class FragmentThree extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<WXItemBean> wechat_al = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_three, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fm3_recycler);
        //设置数据
        initData();
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void initData() {
        Retrofit mRetrofit=new Retrofit.Builder().baseUrl("http://api.tianapi.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WechatApi wechatApi = mRetrofit.create(WechatApi.class);
        wechatApi.getWXHot("78926886d340296c5125405447aed227",20,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CoreDataResponse<List<WXItemBean>>>() {
                    @Override
                    public void onCompleted() {


                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(CoreDataResponse<List<WXItemBean>> listCoreDataResponse) {
                        wechat_al.addAll(listCoreDataResponse.getNewslist());
                        //设置适配器
                        WeChatAdapter adapter = new WeChatAdapter(getActivity(),wechat_al);
                        mRecyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener(new WeChatAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(getActivity(), "您点击了第" + (position+1 )+ "条目", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


    }
}
