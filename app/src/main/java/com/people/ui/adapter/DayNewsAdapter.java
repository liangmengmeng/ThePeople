package com.people.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.people.R;
import com.people.api.bean.SectionListBean;

import java.util.ArrayList;

/**
 * autour: 梁萌萌
 * date: 2017/1/12 15:23
 * update: 2017/1/12
 */
public class DayNewsAdapter extends RecyclerView.Adapter<DayNewsAdapter.MyViewHolder> {

    private ArrayList<SectionListBean.DataBean> list;
    private Context context;

    private static OnItemClickListener mOnItemClickListener;
    //设置点击和长按事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position); //设置点击事件

    }
    public static void setOnItemClickListener(OnItemClickListener listener)     {
        mOnItemClickListener = listener;
    }

    public DayNewsAdapter(Context context, ArrayList<SectionListBean.DataBean> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public DayNewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.fm2_recycler_item,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(final DayNewsAdapter.MyViewHolder holder, final int position) {
        holder.tv1.setText(list.get(position).getDescription());
        Glide.with(context)
                .load(list.get(position).getThumbnail())
                .into(holder.iv);
        holder.tv2.setText(list.get(position).getName());


        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                //itemview是holder里的所有控件，可以单独设置比如ImageButton Button等
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

       ImageView iv;
        TextView tv1;
       TextView tv2;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.fm2_recycler_image);
            tv1 = (TextView) itemView.findViewById(R.id.fm2_recycler_desc);
            tv2 = (TextView) itemView.findViewById(R.id.fm2_recycler_title);

        }
    }
}
