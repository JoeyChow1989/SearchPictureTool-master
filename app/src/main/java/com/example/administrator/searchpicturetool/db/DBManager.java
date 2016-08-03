package com.example.administrator.searchpicturetool.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.searchpicturetool.config.MySql;
import com.example.administrator.searchpicturetool.model.bean.DownloadImg;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.model.bean.NetImageImpl;
import com.example.administrator.searchpicturetool.model.bean.NewRecommendContent;
import com.jude.utils.JUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenhuaijun on 2016/1/31 0031.
 */
public class DBManager {

    private  static DBManager instance;
    private DBHelper helper;
    private SQLiteDatabase db;
    private DBManager(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }
    //单例模式
    public static synchronized  DBManager getInstance(Context context){
        if(instance!=null){
            return instance;
        }else{
            instance = new DBManager(context);
            return instance;
        }
    }


    /**
     * 添加已下载图片信息到数据库
     */
    public  void addHasDownload(DownloadImg img){
        db.beginTransaction();
        db.execSQL("insert into "+ MySql.DownloadTable+" values(null,?,?,?,?)",
                new Object[]{img.getName(),img.getLargUrl(),img.getHeight(),img.getWidth()});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 将已删除的下载图片信息从数据库里删除
     */
    public void deleteHasDownload(String fileName){
        db.beginTransaction();
        db.execSQL("delete from "+MySql.DownloadTable+" where fileName =?",new Object[]{fileName});
      //  db.execSQL("delete from " + MySql.DownloadTable + " where fileName = " + "'" + fileName + "'");
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    /**
     * 批量删除已选中下载的图片
     */
    public void deleteDownloadPictures(ArrayList<DownloadImg> imgs){
        db.beginTransaction();
        for(DownloadImg img :imgs){
            new File(img.getName()).delete();
            db.execSQL("delete from "+MySql.DownloadTable+" where fileName =?",new Object[]{img.getName()});
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 查询所有下载的图片信息
     * @return
     */
    public ArrayList<DownloadImg> queryHasDownloadImgs(){
        ArrayList<DownloadImg> imgs = new ArrayList<DownloadImg>();

        Cursor cursor = db.rawQuery("select * from "+MySql.DownloadTable+" order by id desc",null);

        if (cursor!=null&&cursor.moveToFirst()){
            do{
                DownloadImg img = new DownloadImg();
                img.setName(cursor.getString(cursor.getColumnIndex("fileName")));
                img.setLargUrl(cursor.getString(cursor.getColumnIndex("largeImgUrl")));
                img.setHeight(cursor.getInt(cursor.getColumnIndex("height")));
                img.setWidth(cursor.getInt(cursor.getColumnIndex("width")));
                imgs.add(img);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return imgs;

    }
    //从数据库随机抽取需要展示的推荐列表
    public List<NewRecommendContent> getRandomRecomendFromDB(){
        List<NewRecommendContent> lists = new ArrayList<>();
        //获取tip
        Cursor cursor = db.rawQuery("select * from "+MySql.RecommendTable+" where justType =1",null);
        int tipNums =cursor.getCount();
        if (cursor!=null&&cursor.moveToFirst()){
            do{
                NewRecommendContent img = new NewRecommendContent();
                img.setType(cursor.getFloat(cursor.getColumnIndex("type")));
                img.setTip(cursor.getString(cursor.getColumnIndex("tip")));
               // img.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
                //img.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                //img.setContent(cursor.getString(cursor.getColumnIndex("content")));
                if(cursor.getInt(cursor.getColumnIndex("justType"))==1){
                    img.setJustType(true);
                }else{
                    img.setJustType(false);
                }

                lists.add(img);
            }while(cursor.moveToNext());
        }
        cursor.close();
        if(tipNums!=0){
            for(int i=0; i<tipNums;i++){
                Cursor contentCursor;
                if(lists.get(i).getTip().equals("热门搜索")){
                   contentCursor = db.rawQuery("select * from "+MySql.RecommendTable+" where justType = 0 and tip =? ORDER BY RANDOM() limit 2",new String[]{lists.get(i).getTip()});
                }else{
                 contentCursor = db.rawQuery("select * from "+MySql.RecommendTable+" where justType = 0 and tip =? ORDER BY RANDOM() limit 4",new String[]{lists.get(i).getTip()});
                }
                if (contentCursor!=null&&contentCursor.moveToFirst()){
                    do{
                        NewRecommendContent img = new NewRecommendContent();
                        img.setType(contentCursor.getFloat(contentCursor.getColumnIndex("type")));
                        img.setTip(contentCursor.getString(contentCursor.getColumnIndex("tip")));
                        img.setImageUrl(contentCursor.getString(contentCursor.getColumnIndex("imageUrl")));
                        img.setTitle(contentCursor.getString(contentCursor.getColumnIndex("title")));
                        img.setContent(contentCursor.getString(contentCursor.getColumnIndex("content")));
                        if(contentCursor.getInt(contentCursor.getColumnIndex("justType"))==1){
                            img.setJustType(true);
                        }else{
                            img.setJustType(false);
                        }
                        lists.add(img);
                    }while(contentCursor.moveToNext());
                }
            }

        }
       /* for(NewRecommendContent recommendContent :lists){
            JUtils.Log(recommendContent.toString());
        }*/
        return lists;
    }
    //从数据库中获取所有推荐列表
    public List<NewRecommendContent> getRecomendContentfromDB(){
        List<NewRecommendContent> lists = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+MySql.RecommendTable+"",null);
        if (cursor!=null&&cursor.moveToFirst()){
            do{
                NewRecommendContent img = new NewRecommendContent();
                img.setType(cursor.getFloat(cursor.getColumnIndex("type")));
                img.setTip(cursor.getString(cursor.getColumnIndex("tip")));
                img.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
                img.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                img.setContent(cursor.getString(cursor.getColumnIndex("content")));
                if(cursor.getInt(cursor.getColumnIndex("justType"))==1){
                    img.setJustType(true);
                }else{
                    img.setJustType(false);
                }
                lists.add(img);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return lists;
    }




    /**
     * 添加已收藏图片信息到数据库
     */
    public void addHasCollect(NetImage img){
        db.beginTransaction();
        db.execSQL("insert into "+MySql.CollectTable+" values(null,?,?,?,?)",
                new Object[]{img.getThumbImg(),img.getLargeImg(),img.getHeight(),img.getWidth()});
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    //添加一个推荐列表到数据库
    private void addRecommendContent(NewRecommendContent content){
      //  db.beginTransaction();
        db.execSQL("insert into " + MySql.RecommendTable + " values(?,?,?,?,?,?)",
                new Object[]{content.getType(), content.getTip(),content.getImageUrl(), content.getTitle(), content.getContent(), content.isJustType()});
      //  db.setTransactionSuccessful();
      //  db.endTransaction();
    }
    //删除推荐列表库里面所有的数据
    public void deleteAllRecommendContents(){
        db.beginTransaction();
        db.execSQL("delete from " + MySql.RecommendTable + "");
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    //批量添加一系列的推荐到数据库
    public void addAllRecomendContents(List<NewRecommendContent> lists){
        db.beginTransaction();
        for(NewRecommendContent content:lists){
            addRecommendContent(content);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 将已删除的收藏图片信息从数据库里删除
     */
    public void deleteHasCollect(String largeImgUrl){
        db.beginTransaction();
        db.execSQL("delete from "+MySql.CollectTable+" where largeImgUrl = ?",new Object[]{largeImgUrl});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 批量删除已选中下载的图片
     */
    public void deleteCollectPictures(ArrayList<NetImage> imgs){
        db.beginTransaction();
        for(NetImage img :imgs){
            db.execSQL("delete from "+MySql.CollectTable+" where largeImgUrl =?",new Object[]{img.getLargeImg()});
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 查询所有收藏的图片信息
     * @return
     */
    public ArrayList<NetImage> queryHasCollectImgs(){
        ArrayList<NetImage> imgs = new ArrayList<NetImage>();
        Cursor cursor = db.rawQuery("select * from "+MySql.CollectTable+" order by id desc",null);

        if (cursor!=null&&cursor.moveToFirst()){
            do{
                NetImageImpl netImage= new NetImageImpl();
                netImage.setThumbUrl(cursor.getString(cursor.getColumnIndex("smallImgUrl")));
                netImage.setLargeUrl(cursor.getString(cursor.getColumnIndex("largeImgUrl")));
                netImage.setThumb_height(cursor.getInt(cursor.getColumnIndex("height")));
                netImage.setThumb_width(cursor.getInt(cursor.getColumnIndex("width")));
                imgs.add(netImage);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return imgs;

    }

}
