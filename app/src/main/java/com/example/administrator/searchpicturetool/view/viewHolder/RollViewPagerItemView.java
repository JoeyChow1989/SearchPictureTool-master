package com.example.administrator.searchpicturetool.view.viewHolder;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.app.APP;
import com.example.administrator.searchpicturetool.model.BannerModel;
import com.example.administrator.searchpicturetool.model.bean.Banner;
import com.example.administrator.searchpicturetool.model.bean.NewBanner;
import com.example.administrator.searchpicturetool.presenter.adapter.ImageLoopAdapter;
import com.example.administrator.searchpicturetool.widght.BannerTextHintView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;
import com.jude.rollviewpager.RollPagerView;
import com.jude.utils.JFileManager;
import com.jude.utils.JUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by wenhuaijun on 2016/2/9 0009.
 */
public class RollViewPagerItemView implements RecyclerArrayAdapter.ItemView{
    RollPagerView rollPagerView;
    ImageLoopAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context;
    List<NewBanner> banners;
    JFileManager.Folder folder;
    public RollViewPagerItemView(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout =swipeRefreshLayout;
    }

    @Override
    public View onCreateView(ViewGroup parent) {
      //  adapter = new ImageLoopAdapter();
        context =parent.getContext();
        folder = JFileManager.getInstance().getFolder(APP.Dir.Object);
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_viewpager,parent,false);
        rollPagerView =(RollPagerView)view.findViewById(R.id.roll_view_pager);
        //解决viewPager和swipeRefreshLayout的冲突
        rollPagerView.getViewPager().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_MOVE:
                        swipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        swipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });

//        rollPagerView.setAdapter(adapter);
      //  rollPagerView.getViewPager().setOnClickListener(this);
        setData();
        return view;
    }
    public void setData(){
        //加载缓存数据
        getBannerFromCache();
        //延迟加载，和缓存加载间隔一段时间，避免卡顿
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BannerModel.getBanners(context, new Subscriber<List<NewBanner>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final List<NewBanner> banners) {
                        folder.writeObjectToFile(banners, "banner");
                        RollViewPagerItemView.this.banners = banners;
                        adapter = new ImageLoopAdapter();
                        adapter.setBanners(banners);
                        rollPagerView.setHintView(new BannerTextHintView(context, banners));
                        rollPagerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        //rollPagerView.setHintView(new BannerTextHintView(context, banners));


                    }

                });
            }
        },1000);

    }
    @Override
    public void onBindView(View headerView) {

    }
    public void getBannerFromCache(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                banners =(ArrayList<NewBanner>)folder.readObjectFromFile("banner");
                if(banners!=null&&banners.size()!=0){
                    rollPagerView.post(new Runnable() {
                        @Override
                        public void run() {

                            adapter = new ImageLoopAdapter();
                            adapter.setBanners(banners);
                            rollPagerView.setHintView(new BannerTextHintView(context, banners));
                            rollPagerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();

    }

}