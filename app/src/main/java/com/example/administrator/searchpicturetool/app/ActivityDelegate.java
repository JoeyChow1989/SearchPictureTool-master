package com.example.administrator.searchpicturetool.app;

import android.app.Activity;
import android.os.Bundle;

import com.jude.beam.bijection.ActivityLifeCycleDelegate;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * Created by wenhuaijun on 2015/11/10 0010.
 */
public class ActivityDelegate extends ActivityLifeCycleDelegate{
    public ActivityDelegate(Activity act) {
        super(act);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(getActivity()).onAppStart();
      //  APP.getInstance().mRefWatcher.watch(getActivity());
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

}
