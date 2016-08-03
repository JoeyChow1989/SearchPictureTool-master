package com.example.administrator.searchpicturetool.presenter.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.view.fragment.RecommendFragment;
import com.example.administrator.searchpicturetool.view.fragment.NetImgFragment;

import java.util.HashMap;

/**
 * Created by wenhuaijun on 2015/11/2 0002.
 */
public class MyTabFragmentAdapter extends FragmentPagerAdapter {
    private HashMap<String,Fragment> fragments;
    private String[] tabs;
    public MyTabFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        tabs=context.getResources().getStringArray(R.array.tab);
        fragments = new HashMap<String,Fragment>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment = new RecommendFragment();
                break;
            /*case 1:
                fragment = new JoyImgFragment();

                break;*/
            default:
                fragment = new NetImgFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("tab", position);
                fragment.setArguments(bundle);
             //   fragments.put(position + "", fragment);
                break;

        }
        fragments.put(position + "", fragment);
        /*if(position==0){
            fragment = new RecommendFragment();
        }else{
            fragment = new NetImgFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tab", position);
            fragment.setArguments(bundle);
            fragments.put(position+"",(NetImgFragment)fragment);
        }*/
        return fragment;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

       return tabs[position];
    }

    public Fragment getFragment(int position){
        return fragments.get(position+"");
    }
}
