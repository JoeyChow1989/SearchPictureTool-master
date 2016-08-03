package com.example.administrator.searchpicturetool.presenter.activitPresenter;

import com.example.administrator.searchpicturetool.config.API;
import com.example.administrator.searchpicturetool.model.CollectImgSelected;
import com.example.administrator.searchpicturetool.model.DownloadImgSelected;
import com.example.administrator.searchpicturetool.model.SqlModel;
import com.example.administrator.searchpicturetool.presenter.adapter.UserPagerAdapter;
import com.example.administrator.searchpicturetool.view.activity.UserActivity;
import com.jude.beam.bijection.Presenter;
import com.jude.utils.JUtils;

import rx.functions.Action1;

/**
 * Created by wenhuaijun on 2015/11/12 0012.
 */
public class UserActivityPresenter extends Presenter<UserActivity> {
    public UserPagerAdapter userPagerAdapter;
    public static boolean isTransactioning =false;
    /**
     * 0:批量删除图片
     * 1：批量设置每日替换壁纸
     * 2：批量分享下载图片
     */
    public int transaction=-1;
    /**
     * 0: downloadPage
     * 1:collectPage
     */
    public int page=0;

    @Override
    protected void onCreateView(UserActivity view) {
        super.onCreateView(view);
        userPagerAdapter = new UserPagerAdapter(getView().getSupportFragmentManager());
        getView().getViewPager().setAdapter(userPagerAdapter);
        getView().getTabLayout().setupWithViewPager(getView().getViewPager());
    }

    @Override
    protected void onDestroyView() {
        super.onDestroyView();
        isTransactioning =false;
        DownloadImgSelected.getSelectedImgs().clear();
        CollectImgSelected.getSelectedImgs().clear();
    }
    public void beginSelectImgs(){
        isTransactioning =true;
        if(page==0)  userPagerAdapter.getDownloadFragment().getListView().getAdapter().notifyDataSetChanged();
        if(page==1) userPagerAdapter.getCollectFragment().getListView().getAdapter().notifyDataSetChanged();
       // userPagerAdapter.getDownloadFragment().getListView().getSwipeToRefresh().setEnabled(false);

    }
    public void beginTransaction(){
        switch (transaction){
            case 0:
                //批量删除图片
                isTransactioning =false;
                if(DownloadImgSelected.size()!=0) deleteDownloadImgs();
                if(CollectImgSelected.size()!=0)  deleteCollectImgs();
                break;
            case 1:
                //设置每日壁纸
                transactionEnd();
                break;
            case 2:
                //批量分享下载图片
                transactionEnd();
                break;
            default:
                transactionEnd();
                break;
        }

    }
    public void deleteDownloadImgs(){
        SqlModel.deleteDownloadImgs(getView(), DownloadImgSelected.getSelectedImgs()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                if (s.equals(API.status.success + "")) {
                    DownloadImgSelected.getSelectedImgs().clear();
                    userPagerAdapter.getDownloadFragment().getPresenter().onRefresh();
                }
            }
        });
    }
    public void deleteCollectImgs(){
        SqlModel.deleteCollectImgs(getView(), CollectImgSelected.getSelectedImgs()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                if (s.equals(API.status.success + "")) {
                    CollectImgSelected.getSelectedImgs().clear();
                    userPagerAdapter.getCollectFragment().getPresenter().onRefresh();

                }
            }
        });
    }
    public void transactionEnd(){
        if(transaction==1){
            JUtils.Toast("每日壁纸正在赶工中...");
        }
        if(transaction==2){
            JUtils.Toast("批量分享正在赶工中...");
        }
        isTransactioning=false;
        DownloadImgSelected.getSelectedImgs().clear();
        CollectImgSelected.getSelectedImgs().clear();
        userPagerAdapter.getDownloadFragment().getPresenter().onRefresh();
        userPagerAdapter.getCollectFragment().getPresenter().onRefresh();
    }
    public void stopRefresh(int i){
        switch (getView().getViewPager().getCurrentItem()){
            case 0:
                userPagerAdapter.getDownloadFragment().getListView().getSwipeToRefresh().setEnabled(i==0);
            break;
            case 1:
                userPagerAdapter.getCollectFragment().getListView().getSwipeToRefresh().setEnabled(i==0);
            break;
        }
    }
}
