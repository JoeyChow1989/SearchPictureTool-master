package com.example.administrator.searchpicturetool.view.activity;

import android.os.Bundle;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.activitPresenter.SettingPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.utils.JUtils;
import com.umeng.fb.FeedbackAgent;
import com.umeng.message.PushAgent;
import com.umeng.update.UmengUpdateAgent;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wenhuaijun on 2015/11/14 0014.
 */
@RequiresPresenter(SettingPresenter.class)
public class SettingActivity extends BeamBaseActivity<SettingPresenter>{
    android.support.v7.widget.SwitchCompat compat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        compat = (android.support.v7.widget.SwitchCompat)findViewById(R.id.setting_switch);
        if(JUtils.getSharedPreference().getBoolean("shouldPush",true)){
            compat.setChecked(true);
        }else{
            compat.setChecked(false);
        }
    }
    @OnClick(R.id.setting_response)
    public void response(){
        FeedbackAgent agent = new FeedbackAgent(this);
        agent.startFeedbackActivity();
    }
    @OnClick(R.id.setting_update)
    public void update(){
        UmengUpdateAgent.forceUpdate(this);
    }
    @OnClick(R.id.setting_switch)
    public void openSwitch(){
        PushAgent mPushAgent = PushAgent.getInstance(this);
        if(!compat.isChecked()){
            mPushAgent.disable();
            JUtils.Toast("已关闭接收消息推送功能");
            JUtils.getSharedPreference().edit().putBoolean("shouldPush",false).commit();
        }else{
            mPushAgent.enable();
            JUtils.Toast("已开启接收消息推送功能");
            JUtils.getSharedPreference().edit().putBoolean("shouldPush", true).commit();
        }
    }
}
