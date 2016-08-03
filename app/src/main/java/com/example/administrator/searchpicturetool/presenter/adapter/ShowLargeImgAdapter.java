package com.example.administrator.searchpicturetool.presenter.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.library.imageLoader.EasyImageLoader;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.widght.PinchImageView;
import com.jude.utils.JUtils;

import java.util.ArrayList;

/**
 * Created by wenhuaijun on 2015/11/8 0008.
 */
public  class ShowLargeImgAdapter extends PagerAdapter implements View.OnClickListener {
    private PinchImageView pinchImageView;
    private ArrayList<NetImage> netImages;
    private Activity context;
    private LayoutInflater inflater;
    private int screenHeight;
    private int screenWidth;
    private View view;
    ViewGroup.LayoutParams mLayoutParams;

    public ShowLargeImgAdapter(ArrayList<NetImage> netImages, Activity context) {
        this.netImages = netImages;
        this.context = context;
        screenHeight = JUtils.getScreenHeight();
        screenWidth = JUtils.getScreenWidth();
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return netImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //  super.destroyItem(container, position, object);
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        view = inflater.inflate(R.layout.item_large_img, null);

        pinchImageView = (PinchImageView) view.findViewById(R.id.photoView);
        pinchImageView.setOnClickListener(this);

     //   mLayoutParams = pinchImageView.getLayoutParams();
        /*if (netImages.get(position).getWidth() != 0 && netImages.get(position).getHeight() != 0) {
           *//* float mHeight = ((float) (netImages.get(position).getHeight()) / ((float) (netImages.get(position).getWidth()))) * screenWidth;
                mLayoutParams.width = screenWidth;
                mLayoutParams.height = (int) mHeight;*//*
            if(netImages.get(position).getHeight()<=netImages.get(position).getWidth()*2){
                float mHeight = ((float) (netImages.get(position).getHeight()) / ((float) (netImages.get(position).getWidth()))) * screenWidth;
                mLayoutParams.width = screenWidth;
                mLayoutParams.height = (int)mHeight;
            }else{
                float mWidth =((float) (netImages.get(position).getWidth()) / ((float) (netImages.get(position).getHeight()))) * screenHeight;
                mLayoutParams.height = screenHeight;
                mLayoutParams.width = (int)mWidth;
            }

                pinchImageView.setLayoutParams(mLayoutParams);

        }else{
            mLayoutParams.width = screenWidth;
            mLayoutParams.height = screenHeight;
            pinchImageView.setLayoutParams(mLayoutParams);
        }*/
           // pinchImageView.setImageURI(Uri.parse(netImages.get(position).getLargeImg()));
            //加载图片
            EasyImageLoader.getInstance(context).bindBitmap(netImages.get(position).getLargeImg(), pinchImageView, new EasyImageLoader.BindBitmapErrorCallBack() {
                @Override
                public void onError(ImageView imgView) {
                    JUtils.Log("bindBitmap onError");
                    EasyImageLoader.getInstance(context).bindBitmap(netImages.get(position).getThumbImg(), imgView);
                }
            });
            container.addView(view);
            return view;
    }

    @Override
    public void onClick(View v) {
        context.finish();
    }
}
