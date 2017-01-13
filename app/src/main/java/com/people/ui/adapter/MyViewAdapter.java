package com.people.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 图片轮播适配器
 * autour: 梁萌萌
 * date: 2017/1/5 20:24
 * update: 2017/1/5
 */
public class MyViewAdapter extends PagerAdapter {
    private Handler handler;
    private Integer[] boys;
    private Context context;

    public MyViewAdapter(Context context, Integer[] boys, Handler handler) {
        this.context=context;
        this.boys=boys;
        this.handler=handler;
    }




    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv=new ImageView(context);
        //对imageView设置触摸监听
        iv.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //停止轮播任务--移除所有信息和任务
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                    case MotionEvent.ACTION_CANCEL:
                        //重新开始轮播任务
                        handler.sendEmptyMessageDelayed(0, 2000);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
        iv.setImageResource(boys[position%boys.length]);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
