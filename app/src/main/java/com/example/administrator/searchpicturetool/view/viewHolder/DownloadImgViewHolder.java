package com.example.administrator.searchpicturetool.view.viewHolder;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.DownloadImgSelected;
import com.example.administrator.searchpicturetool.model.bean.DownloadImg;
import com.example.administrator.searchpicturetool.presenter.activitPresenter.UserActivityPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JUtils;

import java.io.File;

/**
 * Created by wenhuaijun on 2015/11/12 0012.
 */
public class DownloadImgViewHolder extends BaseViewHolder<DownloadImg>{
    SimpleDraweeView image;
    View view_bg;
    ImageView img_selected;
    boolean isSelected =false;
    float width;
    float height;
    float sccrenWidth;


    ViewGroup.LayoutParams layoutParams;
    public DownloadImgViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_netimage);
        image =(SimpleDraweeView)itemView.findViewById(R.id.net_img);
        view_bg =itemView.findViewById(R.id.bg_layout);
        img_selected =(ImageView)itemView.findViewById(R.id.img_selected);
        sccrenWidth = JUtils.getScreenWidth()/2;
    }

    @Override
    public void setData(final DownloadImg data) {
        super.setData(data);
        height =data.getHeight();
        width = data.getWidth();
        layoutParams= image.getLayoutParams();
        layoutParams.height= (int)((height/width)*sccrenWidth);
        image.setLayoutParams(layoutParams);
        image.setImageURI(Uri.fromFile(new File(data.getName())));
        if(UserActivityPresenter.isTransactioning){
            view_bg.setLayoutParams(layoutParams);
            view_bg.setVisibility(View.VISIBLE);
            img_selected.setVisibility(View.VISIBLE);
            img_selected.setImageResource(R.drawable.ic_not_selected);
            img_selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isSelected){
                        img_selected.setImageResource(R.drawable.ic_selected);
                      //  DownloadImgSelected.getSelectedImgs().add(data);
                        DownloadImgSelected.add(data);
                        isSelected=true;
                    }else{
                        img_selected.setImageResource(R.drawable.ic_not_selected);
                       // DownloadImgSelected.getSelectedImgs().remove(data);
                        DownloadImgSelected.remove(data);
                        isSelected=false;
                    }

                }
            });
        }else{
            view_bg.setVisibility(View.GONE);
            img_selected.setVisibility(View.GONE);
        }
    }

}
