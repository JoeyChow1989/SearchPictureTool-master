package com.example.administrator.searchpicturetool.util;

import com.example.administrator.searchpicturetool.model.bean.NewRecommendContent;
import com.example.administrator.searchpicturetool.model.bean.RecommendTip;

import java.util.Comparator;

/**
 * Created by wenhuaijun on 2016/2/12 0012.
 */
public class RecommendComparator implements Comparator<NewRecommendContent> {

    @Override
    public int compare(NewRecommendContent ob1, NewRecommendContent ob2) {
        if(ob1.getType()<ob2.getType()){
            return -1;
        }else{
            return 1;
        }
       /* RecommendTip tip1 = null;
        RecommendTip tip2 = null;

        //ob1向下转型
        if(ob1 instanceof RecommendTip) {
            tip1 = (RecommendTip) ob1;
        }
        if(ob2 instanceof RecommendTip){
            tip2 =(RecommendTip)ob2;
        }

        if(tip1!=null&&tip1.getType()<tip2.getType()){
            return -1;
        }else if(tip2!=null&&tip1.getType()>tip2.getType()){
            return  1;
        }
        return 0;*/

    }
}
