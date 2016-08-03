package com.example.administrator.searchpicturetool.presenter.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.DownloadImg;
import com.example.administrator.searchpicturetool.widght.PhotoView;
import com.example.administrator.searchpicturetool.widght.PinchImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.utils.JUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by wenhuaijun on 2015/11/13 0013.
 */
public class ShowDownloadLargeImgAdapter extends PagerAdapter implements View.OnClickListener {

    private ArrayList<DownloadImg> downloadImgs;
    private Activity context;
    private LayoutInflater inflater;
    private int screenHeight;
    private int screenWidth;
    private PinchImageView pinchImageView;
    ViewGroup.LayoutParams mLayoutParams;
    public ShowDownloadLargeImgAdapter(ArrayList<DownloadImg> downloadImgs,Activity context) {
        this.downloadImgs = downloadImgs;
        this.context=context;
        screenHeight = JUtils.getScreenHeight();
        screenWidth = JUtils.getScreenWidth();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return downloadImgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //  super.destroyItem(container, position, object);
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
         View view = inflater.inflate(R.layout.item_large_img, null);
        pinchImageView = (PinchImageView) view.findViewById(R.id.photoView);
        pinchImageView.setOnClickListener(this);
        //加载图片
        pinchImageView.setImageBitmap(BitmapFactory.decodeFile(downloadImgs.get(position).getName()));
        container.addView(view);
        return  view;
    }

    @Override
    public void onClick(View v) {
        context.finish();
    }
}
