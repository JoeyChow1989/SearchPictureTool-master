package com.example.administrator.searchpicturetool.view.viewHolder;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.config.API;
import com.example.administrator.searchpicturetool.model.SaveBitmapModel;
import com.example.administrator.searchpicturetool.model.SqlModel;
import com.example.administrator.searchpicturetool.model.WrapperModel;
import com.example.administrator.searchpicturetool.model.bean.ImageJoy;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.model.bean.NetImageImpl;
import com.example.administrator.searchpicturetool.util.Utils;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JUtils;

import rx.functions.Action1;

/**
 * Created by wenhuaijun on 2016/2/13 0013.
 */
public class JoyImageViewHolder extends BaseViewHolder<ImageJoy> implements View.OnClickListener{
    private SimpleDraweeView simpleDraweeView;
    private TextView title;
    float width;
    float height;
    float sccrenWidth;
    private ImageView share;
    private ImageView download;
    private ImageView collect;
    private ImageJoy data;
    private int state =-1;
    /**
     * -1 初始化
     * 0 下载图片
     * 1 分享图片
     */
    ViewGroup.LayoutParams layoutParams;
    public JoyImageViewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_joyimage);
        simpleDraweeView =$(R.id.joyImg);
        title = $(R.id.joy_title);
        share =$(R.id.joy_share);
        download =$(R.id.joy_download);
        collect =$(R.id.joy_collect);
        sccrenWidth = JUtils.getScreenWidth();
        share.setOnClickListener(this);
        download.setOnClickListener(this);
        collect.setOnClickListener(this);
    }

    @Override
    public void setData(ImageJoy data) {
        super.setData(data);
        this.data =data;
        height =Float.valueOf(data.getHeight());
        width = Float.valueOf(data.getWidth());
        layoutParams= simpleDraweeView.getLayoutParams();
        layoutParams.height= (int)((height/width)*sccrenWidth);
        simpleDraweeView.setLayoutParams(layoutParams);
        title.setText(data.getTitle());
        simpleDraweeView.setImageURI(Uri.parse(data.getThumburl()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.joy_download:
                //download
                state=0;
                JUtils.Toast("正在下载...");
                downloadBitmap(getContext(),data.getThumburl());
                break;
            case R.id.joy_collect:
                //collect
                SqlModel.addCollectImg(getContext(),new NetImageImpl(data.getSourceurl(),data.getThumburl(),Integer.parseInt(data.getWidth()),Integer.parseInt(data.getHeight())));
                JUtils.Toast("已收藏");
                break;
            case R.id.joy_share:
                //share
                state=1;
                JUtils.Toast("正在准备分享");
                downloadBitmap(getContext(),data.getThumburl());
                break;
            default:
                state =-1;
                break;
        }
    }

    public void downloadBitmap( final Context context, String url){
        SaveBitmapModel.getFrescoDownloadBitmap(context, url).subscribe(new BaseBitmapDataSubscriber() {
            @Override
            protected void onNewResultImpl(final Bitmap bitmap) {
                    SaveBitmapModel.getSaveBitmapObservable(bitmap).subscribe(saveSubscriber);
            }
            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                JUtils.Toast("操作失败！");
            }
        }, CallerThreadExecutor.getInstance());

    }
    //保存图片后的观察者
    Action1<String> saveSubscriber = new Action1<String>() {
        @Override
        public void call(String path) {
            if(!path.equals(API.status.error)){
                if(state==0) {
                    JUtils.Toast("下载图片成功，已下载到SdCard的MyPictures目录里");
                    //保存到数据库
                    SqlModel.addDownloadImg(getContext(),new NetImageImpl(data.getSourceurl(),data.getThumburl(),Integer.parseInt(data.getWidth()),Integer.parseInt(data.getHeight())), path);
                }
                if(state==1){
                    //分享图片
                    Utils.startShareImg(path,getContext());
                }
            }else{
                JUtils.Toast("未知错误");
            }
            state =-1;
        }
    };
}
