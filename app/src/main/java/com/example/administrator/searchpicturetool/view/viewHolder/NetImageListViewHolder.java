package com.example.administrator.searchpicturetool.view.viewHolder;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JUtils;

/**
 * Created by wenhuaijun on 2015/11/3 0003.
 */
public class NetImageListViewHolder extends BaseViewHolder<NetImage>{
    SimpleDraweeView image;
    float width;
    float height;
    float sccrenWidth;
    ViewGroup.LayoutParams layoutParams;
    public NetImageListViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_netimage);
        image =(SimpleDraweeView)itemView.findViewById(R.id.net_img);
        sccrenWidth = JUtils.getScreenWidth()/2;
    }

    @Override
    public void setData(NetImage data) {
        super.setData(data);
        height =data.getHeight();
        width = data.getWidth();
        if(!TextUtils.isEmpty(data.getThumbImg())){
            layoutParams= image.getLayoutParams();
            layoutParams.height= (int)((height/width)*sccrenWidth);
            image.setLayoutParams(layoutParams);
        }
        image.setImageURI(Uri.parse(data.getThumbImg()));

    }

}
