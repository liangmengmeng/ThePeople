package com.people.ui.zhihu.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lmm.bean.CoreBaseActivity;
import com.people.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecationXQActivity extends CoreBaseActivity {
    @BindView(R.id.secation_xq_recycler)
    RecyclerView mRecyclerView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_secation_xq;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
