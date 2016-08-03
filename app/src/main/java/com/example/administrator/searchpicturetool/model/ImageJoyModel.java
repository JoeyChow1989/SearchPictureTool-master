package com.example.administrator.searchpicturetool.model;

import com.example.administrator.searchpicturetool.model.bean.ImageJoy;
import com.example.administrator.searchpicturetool.model.bean.ImageJoyResult;
import com.example.administrator.searchpicturetool.model.service.ServiceCilent;
import com.jude.utils.JUtils;

import java.util.ArrayList;
import java.util.Arrays;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wenhuaijun on 2016/2/13 0013.
 */
public class ImageJoyModel {
    public static Observable<ArrayList<ImageJoy>> getImageJoys(){
        Observable<ArrayList<ImageJoy>> observable = ServiceCilent.getService().getImageJoy()
                .map(new Func1<ImageJoy[], ArrayList<ImageJoy>>() {
                    @Override
                    public ArrayList<ImageJoy> call(ImageJoy[] imageJoys) {
                        return new ArrayList<ImageJoy>(Arrays.asList(imageJoys));
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}
