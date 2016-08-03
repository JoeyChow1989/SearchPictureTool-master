package com.example.administrator.searchpicturetool.view.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.activitPresenter.UserActivityPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wenhuaijun on 2015/11/12 0012.
 */
@RequiresPresenter(UserActivityPresenter.class)
public class UserActivity extends BeamBaseActivity<UserActivityPresenter> implements ViewPager.OnPageChangeListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Menu menu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        initAppBarSetting();
     //   fab.setVisibility(View.GONE);
        fab.hide();
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.my_pictures_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            finish();
            return true;
        }
       // fab.setVisibility(View.VISIBLE);
        fab.show();
        switch (id){
            case R.id.action_delete:
                getPresenter().transaction=0;
                break;
            /*case R.id.action_setting:
                getPresenter().transaction=1;
                break;
            case R.id.action_share:
                getPresenter().transaction=2;
                break;*/
        }
        getPresenter().beginSelectImgs();
        showSnackbar();
        return super.onOptionsItemSelected(item);
    }

    public void initAppBarSetting(){
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                UserActivity.this.getPresenter().stopRefresh(i);
            }
        });
    }
    @OnClick(R.id.fab)
    public void beginTransaction(){
       // fab.setVisibility(View.GONE);
        fab.hide();
        getPresenter().beginTransaction();
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==0){
            menu.clear();
            getPresenter().page=0;
            getMenuInflater().inflate(R.menu.my_pictures_menu, menu);
        }else{
            menu.clear();
            getPresenter().page=1;
            getMenuInflater().inflate(R.menu.my_collect_menu, menu);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public void showSnackbar(){
        String text="";
        switch (getPresenter().transaction){
            case 0:
                text="请选择要删除的图片";
                break;
            case 1:
                text="请选择要设置每日壁纸的图片";
                break;
            case 2:
                text="请选择要分享的图片";
                break;
        }
        Snackbar.make(fab, text, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&getPresenter().isTransactioning){
                getPresenter().transactionEnd();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
