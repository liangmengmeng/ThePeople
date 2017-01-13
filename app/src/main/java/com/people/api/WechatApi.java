package com.people.api;

import com.people.api.bean.CoreDataResponse;
import com.people.api.bean.WXItemBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
/**
 * autour: 梁萌萌
 * date: 2017/1/12 15:32
 * update: 2017/1/12
 */

public interface WechatApi {
    String HOST = "http://api.tianapi.com/";
    /*
    * http://api.tianapi.com/wxnew/?key=78926886d340296c5125405447aed227&num=20&page=1
    * */

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<CoreDataResponse<List<WXItemBean>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<CoreDataResponse<List<WXItemBean>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

}
