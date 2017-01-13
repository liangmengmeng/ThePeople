package com.people.api;

import com.people.api.bean.DailyListBean;
import com.people.api.bean.SectionChildListBean;
import com.people.api.bean.SectionListBean;
import com.people.api.bean.ZhihuDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * autour: 梁萌萌
 * date: 2017/1/12 15:32
 * update: 2017/1/12
 */
public interface ZhiHuApi {
//    /**
//     * 启动界面图片
//     */
//    @GET("start-image/{res}")
//    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * http://news-at.zhihu.com/api/4/news/latest
     * 最新日报
     */
    @GET("news/latest")
    //Call<DailyListBean> getDailyList();
    Observable<DailyListBean> getDailyList();


//    /**
//     * 往期日报
//     */
//    @GET("news/before/{date}")
//    Observable<DailyBeforeListBean> getDailyBeforeList(@Path("date") String date);

//    /**
//     * 主题日报
//     */
//    @GET("themes")
//    Observable<ThemeListBean> getThemeList();

//    /**
//     * 主题日报详情
//     */
//    @GET("theme/{id}")
//    Observable<ThemeChildListBean> getThemeChildList(@Path("id") int id);

    /**
     * 专栏日报
     */
    @GET("sections")
    Observable<SectionListBean> getSectionList();

    /**
     * 专栏日报详情
     */
    @GET("section/{id}")
    Observable<SectionChildListBean> getSectionChildList(@Path("id") int id);

//    /**
//     * 热门日报
//     */
//    @GET("news/hot")
//    Observable<HotListBean> getHotList();

    /**
     * 日报详情
     */
    @GET("news/{id}")
    Observable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);

//    /**
//     * 日报的额外信息
//     */
//    @GET("story-extra/{id}")
//    Observable<DetailExtraBean> getDetailExtraInfo(@Path("id") int id);

//    /**
//     * 日报的长评论
//     */
//    @GET("story/{id}/long-comments")
//    Observable<CommentBean> getLongCommentInfo(@Path("id") int id);

//    /**
//     * 日报的短评论
//     */
//    @GET("story/{id}/short-comments")
//    Observable<CommentBean> getShortCommentInfo(@Path("id") int id);
}
