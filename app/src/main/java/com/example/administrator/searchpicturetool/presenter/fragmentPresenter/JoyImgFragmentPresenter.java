package com.example.administrator.searchpicturetool.presenter.fragmentPresenter;

import com.example.administrator.searchpicturetool.model.ImageJoyModel;
import com.example.administrator.searchpicturetool.model.bean.ImageJoy;
import com.example.administrator.searchpicturetool.view.fragment.JoyImgFragment;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by wenhuaijun on 2016/2/13 0013.
 */
public class JoyImgFragmentPresenter extends BeamListFragmentPresenter<JoyImgFragment,ImageJoy>{
    @Override
    protected void onCreateView(JoyImgFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        ImageJoyModel.getImageJoys().subscribe(new Subscriber<ArrayList<ImageJoy>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().stopRefresh();
                getView().showError(e);
            }

            @Override
            public void onNext(ArrayList<ImageJoy> imageJoys) {
                    getAdapter().clear();
                    getAdapter().addAll(imageJoys);
            }
        });
    }
}
