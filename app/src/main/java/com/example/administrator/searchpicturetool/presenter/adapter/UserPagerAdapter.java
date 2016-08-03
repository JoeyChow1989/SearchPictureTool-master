package com.example.administrator.searchpicturetool.presenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.view.fragment.CollectFragment;
import com.example.administrator.searchpicturetool.view.fragment.DownloadFragment;
import com.example.administrator.searchpicturetool.view.fragment.NetImgFragment;

import java.util.HashMap;

/**
 * Created by wenhuaijun on 2015/11/12 0012.
 */
public class UserPagerAdapter extends FragmentPagerAdapter{
    private DownloadFragment downloadFragment;
    private CollectFragment collectFragment;
    public UserPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            downloadFragment = new DownloadFragment();
            return downloadFragment;
        }else{
            collectFragment = new CollectFragment();
            return collectFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "我的下载";
        }else{
            return "我的收藏";
        }
    }

    public DownloadFragment getDownloadFragment() {
        return downloadFragment;
    }

    public CollectFragment getCollectFragment() {
        return collectFragment;
    }
}
