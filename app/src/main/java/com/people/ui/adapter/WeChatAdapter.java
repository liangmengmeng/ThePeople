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
import com.people.api.bean.WXItemBean;

import java.util.ArrayList;

/**
 * 1.
 * 2.梁萌萌
 * 3.2017/1/11
 */
public class WeChatAdapter extends RecyclerView.Adapter<WeChatAdapter.MyViewHolder> {
    private ArrayList<WXItemBean> wechat_list;
    private Context context;

    public WeChatAdapter(Context context, ArrayList<WXItemBean> wechat_list) {
        this.context=context;
        this.wechat_list=wechat_list;
    }


    private static OnItemClickListener mOnItemClickListener;
    //设置点击和长按事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position); //设置点击事件

    }
    public static void setOnItemClickListener(OnItemClickListener listener)     {
        mOnItemClickListener = listener;
    }

    @Override
    public WeChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.fm3_recycler_item,parent,false));
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final WeChatAdapter.MyViewHolder holder, final int position) {
        holder.tv_desc.setText(wechat_list.get(position).getTitle());
        Glide.with(context)
                .load(wechat_list.get(position).getPicUrl())
                .into(holder.iv);
        holder.tv_name.setText(wechat_list.get(position).getDescription());
        holder.tv_date.setText(wechat_list.get(position).getCtime());


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
        return wechat_list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv_desc;
        TextView tv_name;
        TextView tv_date;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.fm3_recycler_image);
            tv_desc = (TextView) itemView.findViewById(R.id.fm3_tv_desc);
            tv_name = (TextView) itemView.findViewById(R.id.fm3_tv_name);
            tv_date = (TextView) itemView.findViewById(R.id.fm3_tv_date);

        }
    }
}
