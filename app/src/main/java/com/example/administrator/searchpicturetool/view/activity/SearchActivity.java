package com.example.administrator.searchpicturetool.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.activitPresenter.SearchActivityPresenter;
import com.example.administrator.searchpicturetool.view.fragment.SearchFragment;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.utils.JUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wenhuaijun on 2015/11/3 0003.
 */
    @RequiresPresenter(SearchActivityPresenter.class)
public class SearchActivity extends BeamBaseActivity<SearchActivityPresenter>{
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.bg_img)
    SimpleDraweeView imageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.search_fab)
    FloatingActionButton fab;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    FragmentManager manager;
    MenuItem item;
    private int maxScrollHeight =0;
    private SearchFragment searchFragment;
    private String imagUrl;
    private String searchWord;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
         ButterKnife.bind(this);
        onSetToolbar(toolbar);
        maxScrollHeight =JUtils.dip2px(200);
        searchWord = getIntent().getBundleExtra("search").getString("search");
        collapsingToolbarLayout.setTitle(searchWord);
        imagUrl =getIntent().getBundleExtra("search").getString("imagUrl");
        if (TextUtils.isEmpty(imagUrl)){
            imageView.setBackgroundResource(getPresenter().getBgImg());
        }else{
            imageView.setImageURI(Uri.parse(imagUrl));
        }
        manager =getSupportFragmentManager();
        searchFragment = new SearchFragment();
        searchFragment.setArguments(getIntent().getBundleExtra("search"));
        manager.beginTransaction().replace(R.id.search_container,searchFragment).commit();
        initAppBarSetting();
        initSearchView();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchactivity_menu, menu);
        item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }*/
        switch (item.getItemId()){
            case R.id.action_search:
                if(!searchView.isShown()){
                    searchView.setVisibility(View.VISIBLE);
                    searchView.onActionViewExpanded();
                    searchView.requestFocus();
                    searchView.setQuery(searchWord, false);
                }else{

                    if(searchView.getQuery().toString().equals("")){
                        searchView.setVisibility(View.GONE);
                        searchView.onActionViewCollapsed();
                       // collapsingToolbarLayout.requestFocus();
                    }else{
                        searchView.setQuery(searchView.getQuery(), true);
                    }
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initAppBarSetting(){
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i >= (-maxScrollHeight+200)&&fab.isShown()) {
                    fab.hide();
                    if (item != null) {
                        item.setVisible(false);
                    }
                } else if (i <= (-maxScrollHeight+200)&&!fab.isShown()) {
                    fab.show();
                    item.setVisible(true);
                }
            }
        });
    }
    private void initSearchView() {
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.onActionViewCollapsed();
                searchWord = query;
                searchPicture(searchWord);
                searchView.setVisibility(View.GONE);
                collapsingToolbarLayout.setTitle(searchWord);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @OnClick(R.id.search_fab)
    public void clickFab(View view){
        getPresenter().gotoUp(0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            if(data!=null){
                getPresenter().gotoUp(data.getIntExtra("position",0));
            }

        }
    }
    private void searchPicture(String query){
        searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("search",query);
        searchFragment.setArguments(bundle);
        manager.beginTransaction().replace(R.id.search_container,searchFragment).commit();
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(searchView.isShown()){
                    searchView.setVisibility(View.GONE);
                    searchView.onActionViewCollapsed();
                    return true;
                }else{
                    return super.onKeyDown(keyCode, event);
                }
            default:
                return super.onKeyDown(keyCode, event);
        }

    }
}
