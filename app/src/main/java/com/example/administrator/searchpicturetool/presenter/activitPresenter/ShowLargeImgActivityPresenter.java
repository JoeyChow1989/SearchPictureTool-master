package com.example.administrator.searchpicturetool.presenter.activitPresenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.example.administrator.searchpicturetool.app.APP;
import com.example.administrator.searchpicturetool.config.API;
import com.example.administrator.searchpicturetool.library.imageLoader.EasyImageLoader;
import com.example.administrator.searchpicturetool.model.SaveBitmapModel;
import com.example.administrator.searchpicturetool.model.SqlModel;
import com.example.administrator.searchpicturetool.model.WrapperModel;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.util.Utils;
import com.example.administrator.searchpicturetool.view.activity.ShowLargeImgActivity;
import com.example.administrator.searchpicturetool.presenter.adapter.ShowLargeImgAdapter;
import com.example.administrator.searchpicturetool.widght.PinchImageViewPager;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.jude.beam.bijection.Presenter;
import com.jude.utils.JFileManager;
import com.jude.utils.JUtils;

import java.io.File;
import java.util.ArrayList;

import rx.functions.Action1;

/**
 * Created by wenhuaijun on 2015/11/4 0004.
 */
public class ShowLargeImgActivityPresenter extends Presenter<ShowLargeImgActivity> implements PinchImageViewPager.OnPageChangeListener{
    private NetImage netImage;
    int currentPosition=0;
    private ArrayList<NetImage> netImages;
    ShowLargeImgAdapter adapter;
    /**
     * -1 初始化
     * 0 下载图片
     * 1 分享图片
     * 3 设为壁纸
     * 4 设为锁屏
     */
    private int state =-1;

    @Override
    protected void onCreateView(ShowLargeImgActivity view) {
        super.onCreateView(view);
        netImages =(ArrayList<NetImage>)view.getIntent().getSerializableExtra("netImages");
        currentPosition =view.getIntent().getIntExtra("position", 0);
        adapter  = new ShowLargeImgAdapter(netImages,getView());
        getView().getViewPager().setAdapter(adapter);
        getView().getViewPager().setCurrentItem(currentPosition);
        getView().getViewPager().setOnPageChangeListener(this);
        getView().getPg_tv().setText((currentPosition + 1) + "/" + netImages.size());
    }
    public void savePicture(){
        state=0;
        downloadBitmap(getView(), netImages.get(currentPosition).getLargeImg(),netImages.get(currentPosition).getThumbImg());
    }
    public void collectPicture(){
        SqlModel.addCollectImg(getView(), netImages.get(currentPosition));

    }
    public void requestCollectPicture(){
        SqlModel.deleteCollectImgByUrl(getView(),netImages.get(currentPosition).getLargeImg());
        getView().setResult(100);
        getView().finish();

    }

    public void sharePicture(){
        state=1;
        downloadBitmap(getView(), netImages.get(currentPosition).getLargeImg());
    }
    public void setWallWrapper(){
        state=3;
        downloadBitmap(getView(), netImages.get(currentPosition).getLargeImg());
    }
    public void setLockWrapper(){
        state=4;
        downloadBitmap(getView(), netImages.get(currentPosition).getLargeImg());
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getView().getPg_tv().setText((position+1)+"/"+netImages.size());
        currentPosition=position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public int getPosition(){
        return currentPosition;
    }
    public void downloadBitmap(final Context context,String url,String smallUrl){
        EasyImageLoader.getInstance(context).getBitmap(url, bitmap -> {
            if (bitmap!=null){
                if(state==0||state==1){
                    /*//判断是否是Android 6.0以上，需动态获取权限
                    if(Build.VERSION.SDK_INT >=23){
                        //查看是否获取了写权限
                        int hasWritePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if(hasWritePermission != PackageManager.PERMISSION_GRANTED){
                            //未获取到权限，申请权限
                            ActivityCompat.requestPermissions(getView(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},200);
                        }else{
                            //已获取到权限
                            SaveBitmapModel.getSaveBitmapObservable(bitmap).subscribe(saveSubscriber);
                        }
                    }else{*/
                    SaveBitmapModel.getSaveBitmapObservable(bitmap).subscribe(saveSubscriber);
                    //}
                }else if (state==3){
                    //设置桌面壁纸
                    WrapperModel.getSetWallWrapperObservable(bitmap, context).subscribe(callbackSubscriber);

                }else if(state==4){
                    //设置锁屏壁纸
                    WrapperModel.getSetLockWrapperObservable(bitmap,context).subscribe(callbackSubscriber);
                }
            }else{
                JUtils.Log("高清图下载失败，转去下载缩略图");
                downloadBitmap(context,smallUrl);
            }
        });
    }
    public void downloadBitmap( final Context context, String url){
        EasyImageLoader.getInstance(context).getBitmap(url, bitmap -> {
            if (bitmap!=null){
                if(state==0||state==1){

                        SaveBitmapModel.getSaveBitmapObservable(bitmap).subscribe(saveSubscriber);
                }else if (state==3){
                    //设置桌面壁纸
                    WrapperModel.getSetWallWrapperObservable(bitmap, context).subscribe(callbackSubscriber);

                }else if(state==4){
                    //设置锁屏壁纸
                    WrapperModel.getSetLockWrapperObservable(bitmap,context).subscribe(callbackSubscriber);
                }
            }else{
                JUtils.Toast("下载图片失败");
            }
        });

    }
    //保存图片后的观察者
    Action1<String> saveSubscriber = new Action1<String>() {
        @Override
        public void call(String path) {

            if(!path.equals(API.status.error+"")){
                if(state==0) {
                    JUtils.Log("path: "+path);
                    JUtils.ToastLong("图片已保存至："+path);
                    //保存到数据库
                    SqlModel.addDownloadImg(getView(),netImages.get(currentPosition),path);
                }
                if(state==1){
                   // startShareImg(path);
                    Utils.startShareImg(path,getView());
                }
            }else{
                JUtils.Toast("未获取到读写sd卡权限！无法保存图片");
            }
        }
    };
    //设置事件发生后的消费该事件的观察者
    Action1<Integer> callbackSubscriber = new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
            if(integer.intValue()== API.status.success){
                JUtils.Toast("设置成功！");
            }else{
                JUtils.Toast("设置失败...");
            }
        }
    };
  /*  private void startShareImg(String path){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image*//*");
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        getView().startActivity(Intent.createChooser(shareIntent, "请选择"));
    }*/

}
