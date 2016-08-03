package com.example.administrator.searchpicturetool.model;

import android.content.Context;

import com.example.administrator.searchpicturetool.model.bean.Banner;
import com.example.administrator.searchpicturetool.model.bean.NewBanner;
import com.jude.utils.JUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import rx.Subscriber;

/**
 * Created by wenhuaijun on 2016/2/15 0015.
 */
public class BannerModel {
    public static  void getBanners(Context context, final Subscriber<List<NewBanner>> subscriber){
        BmobQuery<NewBanner> query  = new BmobQuery<>();
        query.findObjects(context, new FindListener<NewBanner>() {
            @Override
            public void onSuccess(List<NewBanner> list) {
                if(list.size()<=4){
                    subscriber.onNext(list);
                }else{
                    List<NewBanner> newLists = new ArrayList<>();
                    int[] range =randomCommon(0, list.size()-1, 4);
                    for(int i:range){
                        newLists.add(list.get(i));
                    }
                    subscriber.onNext(newLists);
                }
            }

            @Override
            public void onError(int i, String s) {
                subscriber.onError(new Throwable("on error"));
            }
        });
    }
    //[min,max]
    public static int[] randomCommon(int min, int max, int n) {
        int len = max - min + 1;

        if (max < min || n > len) {
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
        }
    }
