package com.example.administrator.searchpicturetool.view.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.activitPresenter.ShowLargeImgActivityPresenter;
import com.example.administrator.searchpicturetool.widght.PinchImageViewPager;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.utils.JUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by wenhuaijun on 2015/11/4 0004.
 */
@RequiresPresenter(ShowLargeImgActivityPresenter.class)
public class ShowLargeImgActivity extends BeamBaseActivity<ShowLargeImgActivityPresenter> implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.large_page)
    TextView pg_tv;
    @BindView(R.id.large_viewPager)
    PinchImageViewPager viewPager;
    @BindView(R.id.large_star)
    ImageView star;
    FragmentManager fragmentManager;
    private boolean hasCollected =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_large_img);
        ButterKnife.bind(this);
        onSetToolbar(toolbar);
        hasCollected = getIntent().getBooleanExtra("hasCollected", false);
        if(hasCollected){
            star.setImageResource(R.drawable.ic_large_delete_selector);
        }
        fragmentManager = getSupportFragmentManager();
        viewPager.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.large_img_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cut:
                JUtils.Toast("该功能在下一个版本中开发，敬请期待");
                return true;
            case R.id.action_wrapper:
                getPresenter().setWallWrapper();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
            setResult(200,new Intent().putExtra("position",getPresenter().getPosition()));
            super.onBackPressed();
    }

    public PinchImageViewPager getViewPager() {
        return viewPager;
    }
    public TextView getPg_tv(){
        return pg_tv;
    }
    public void setHasCollected(boolean hasCollected) {
        this.hasCollected = hasCollected;
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
    @OnClick(R.id.large_share)
    public void share(){
        JUtils.Toast("正在分享...");
        getPresenter().sharePicture();
    }
    @OnClick(R.id.large_download)
    public void download(){
        JUtils.Log("download");
        JUtils.Toast("正在下载...");
        getPresenter().savePicture();
    }
    @OnClick(R.id.large_star)
    public void collect(){
        if(hasCollected){
            getPresenter().requestCollectPicture();
            JUtils.Toast("已取消收藏");
        }else{
            getPresenter().collectPicture();
            JUtils.Toast("已收藏");
        }

    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode){
            case 200:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    getPresenter().savePicture();
                } else {
                    // Permission Denied
                    JUtils.Toast("未获取到读写sd卡权限，无法保存图片");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }*/
}
