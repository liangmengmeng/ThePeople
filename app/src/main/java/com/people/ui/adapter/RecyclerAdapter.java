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
import com.people.api.bean.DailyListBean;

import java.util.List;

/**
 * autour: 梁萌萌
 * date: 2017/1/5 21:45
 * update: 2017/1/5
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<DailyListBean.StoriesBean> recycler_list;
    private Context context;

    public RecyclerAdapter(Context context, List<DailyListBean.StoriesBean> recycler_list) {
        this.context = context;
        this.recycler_list=recycler_list;
    }


    /**
     *    点击事件
     */
    private static OnItemClickListener mOnItemClickListener;
    //设置点击和长按事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position); //设置点击事件

    }
    public static void setOnItemClickListener(OnItemClickListener listener)     {
        mOnItemClickListener = listener;
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.fm1_recyclerview_item,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(recycler_list.get(position).getImages().get(0))
                .into(holder.iv);
        holder.tv.setText(recycler_list.get(position).getTitle());


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
        return recycler_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.name);
            iv = (ImageView) itemView.findViewById(R.id.picture);
        }
    }
}
