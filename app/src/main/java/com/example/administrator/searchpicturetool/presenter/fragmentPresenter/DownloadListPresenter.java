package com.example.administrator.searchpicturetool.presenter.fragmentPresenter;

import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.searchpicturetool.model.DownloadImgModel;
import com.example.administrator.searchpicturetool.model.SqlModel;
import com.example.administrator.searchpicturetool.model.bean.DownloadImg;
import com.example.administrator.searchpicturetool.view.activity.ShowDownloadImgActivity;
import com.example.administrator.searchpicturetool.view.fragment.DownloadFragment;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.utils.JUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by wenhuaijun on 2015/11/12 0012.
 */

public class DownloadListPresenter extends BeamListFragmentPresenter<DownloadFragment,DownloadImg> implements RecyclerArrayAdapter.OnItemClickListener{
    List<DownloadImg> downloadImgs;
    @Override
    protected void onCreateView(DownloadFragment view) {
        super.onCreateView(view);
       // view.getListView().getRecyclerView().setHasFixedSize(false);
        view.getListView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
      // getImagesList();
        /*DownloadImgModel.getImageListFromFile2().subscribe(new Action1<List<File>>() {
            @Override
            public void call(List<File> files) {
                downloadImgs =files;
                if(files.size()==0||files==null){
                    getView().getListView().showEmpty();
                }
                getRefreshSubscriber().onNext(files);
                getAdapter().setOnItemClickListener(DownloadListPresenter.this);
            }
        });*/
        SqlModel.getDownloadImgs(getView().getContext()).subscribe(new Action1<ArrayList<DownloadImg>>() {
            @Override
            public void call(ArrayList<DownloadImg> imgs) {
                downloadImgs = imgs;
                if (downloadImgs.size() == 0||downloadImgs==null){
                    getView().getListView().showEmpty();
                }
                getRefreshSubscriber().onNext(downloadImgs);
                getAdapter().setOnItemClickListener(DownloadListPresenter.this);
            }
        });
    }
  /*  public void getImagesList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                files =DownloadImgModel.getImageListFromFile();
                getView().getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(files.size()==0||files==null){
                            getView().getListView().showEmpty();
                        }
                        getRefreshSubscriber().onNext(files);
                        getAdapter().setOnItemClickListener(DownloadListPresenter.this);
                    }
                });
            }
        }).start();
    }*/

    @Override
    public void onItemClick(int position) {
      //  ((UserActivity)(getView().getActivity())).getExpansion().showProgressDialog("请选择");
        Intent intent = new Intent();
        intent.putExtra("largeImgs", new ArrayList<DownloadImg>(downloadImgs));
        intent.putExtra("position", position);
        intent.setClass(getView().getContext(), ShowDownloadImgActivity.class);
   //     getView().startActivity(intent);
        getView().startActivityForResult(intent,100);
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==100){
            onRefresh();
        }
    }
}
