package com.example.administrator.searchpicturetool.view.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.activitPresenter.ShowDownlargeImgPresenter;
import com.example.administrator.searchpicturetool.widght.PinchImageViewPager;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.utils.JUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wenhuaijun on 2015/11/13 0013.
 */
@RequiresPresenter(ShowDownlargeImgPresenter.class)
public class ShowDownloadImgActivity extends BeamBaseActivity<ShowDownlargeImgPresenter> implements View.OnClickListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.large_page)
    TextView pg_tv;
    @BindView(R.id.large_viewPager)
    PinchImageViewPager viewPager;
    FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_large_img);
        ButterKnife.bind(this);
        //toolbar.setTitle("");
        fragmentManager = getSupportFragmentManager();
      //  initMenuFragment();
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
              /*  if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }*/
                return true;
            case R.id.action_wrapper:
                getPresenter().setWallWrapper();

                return true;
        }
        return super.onOptionsItemSelected(item);
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
    @OnClick(R.id.large_delete)
    public void download(){

        JUtils.Toast("已删除");
        getPresenter().deletePicture();
    }
    @OnClick(R.id.large_wrapper)
      public void setingWraper(){
        JUtils.Toast("设置桌面壁纸...");
        getPresenter().setWallWrapper();
    }
    public PinchImageViewPager getViewPager() {
        return viewPager;
    }
    public TextView getPg_tv(){
        return pg_tv;
    }

}
