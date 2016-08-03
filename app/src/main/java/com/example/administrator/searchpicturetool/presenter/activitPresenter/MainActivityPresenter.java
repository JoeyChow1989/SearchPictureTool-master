package com.example.administrator.searchpicturetool.presenter.activitPresenter;

import android.support.v4.app.FragmentManager;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.adapter.MyTabFragmentAdapter;
import com.example.administrator.searchpicturetool.view.activity.MainActivity;
import com.example.administrator.searchpicturetool.view.fragment.RecommendFragment;
import com.jude.beam.bijection.Presenter;
import com.jude.beam.expansion.list.BeamListFragment;

/**
 * Created by wenhuaijun on 2015/11/2 0002.
 */
public class MainActivityPresenter extends Presenter<MainActivity>{
    MyTabFragmentAdapter adapter;
    FragmentManager fragmentManager;
    public int item =1;
    @Override
    protected void onCreateView(MainActivity view) {
        super.onCreateView(view);
        view.getTabLayout().setSelectedTabIndicatorColor(view.getResources().getColor(R.color.white));
        fragmentManager =view.getSupportFragmentManager();
        replaceFragment(0);
    }
    public void replaceFragment(int position){
        item=position;
        adapter = new MyTabFragmentAdapter(getView(),fragmentManager);
        getView().getViewPager().setAdapter(adapter);
        getView().getTabLayout().setupWithViewPager(getView().getViewPager());

    }

    public void goToUp(int position){

        if(adapter.getFragment(getView().getViewPager().getCurrentItem())!=null){
            if(getView().getViewPager().getCurrentItem()==0){
                ((RecommendFragment)adapter.getFragment(0)).recyclerView.scrollToPosition(position);
            }else{
                ((BeamListFragment)adapter.getFragment((getView().getViewPager().getCurrentItem()))).getListView().scrollToPosition(position);
            }

        }

    }
    public void stopRefresh(int i){
        if(getView().getViewPager().getCurrentItem()!=0&&adapter.getFragment(getView().getViewPager().getCurrentItem())!=null)
            ((BeamListFragment)adapter.getFragment((getView().getViewPager().getCurrentItem()))).getListView().getSwipeToRefresh().setEnabled(i == 0);
    }

}
