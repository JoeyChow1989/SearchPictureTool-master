package com.example.administrator.searchpicturetool.config;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.searchpicturetool.R;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * Created by wenhuaijun on 2015/11/10 0010.
 */
public class ShareConfig {
    Context context;
    UMSocialService mController;
    String appID = "wx6dd88e5fb674ccfd";
    String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
    public UMSocialService init(Context context,Activity activity){
        this.context =context;
        UMWXHandler wxHandler = new UMWXHandler(context,appID,appSecret);
        wxHandler.addToSocialSDK();
// 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(context,appID,appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //qq
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, "1104882277",
                "lSwsVt4trqpxuWXB");
        qqSsoHandler.addToSocialSDK();
        //qqZone
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, "100424468",
                "c7394704798a158208a74ab60104f0ba");
        qZoneSsoHandler.addToSocialSDK();
        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
       initSina();;
        initQQ();
        initQQzone();
        initWeixin();
        initWXCircle();
        return mController;
    }
    public void initSina(){
        SinaShareContent sinaShareContent = new SinaShareContent();
        sinaShareContent.setShareContent("装机必备的搜图app，专业提供各类高清壁纸");
        sinaShareContent.setTitle("美图壁纸");
        sinaShareContent.setShareImage(new UMImage(context, R.drawable.ic_launcher));
        sinaShareContent.setTargetUrl("http://meituxiu.bmob.cn");
        mController.setShareMedia(sinaShareContent);
    }
    public void initQQ(){
        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setShareContent("装机必备的搜图app，专业提供各类高清壁纸");
        qqShareContent.setTitle("美图壁纸");
        qqShareContent.setShareImage(new UMImage(context, R.drawable.ic_launcher));
        qqShareContent.setTargetUrl("http://meituxiu.bmob.cn");
        mController.setShareMedia(qqShareContent);
    }
    public void initQQzone(){
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setShareContent("装机必备的搜图app，专业提供各类高清壁纸");
        qzone.setTargetUrl("http://meituxiu.bmob.cn");
        qzone.setTitle("美图壁纸");
        qzone.setShareImage(new UMImage(context, R.drawable.ic_launcher));
        mController.setShareMedia(qzone);
    }
    public void initWeixin(){
        WeiXinShareContent weiXinShareContent = new WeiXinShareContent();
        weiXinShareContent.setShareContent("装机必备的搜图app，专业提供各类高清壁纸");
        weiXinShareContent.setTargetUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.example.administrator.searchpicturetool");
        weiXinShareContent.setTitle("美图壁纸");
        weiXinShareContent.setShareImage(new UMImage(context, R.drawable.ic_launcher));
        mController.setShareMedia(weiXinShareContent);
    }
    public void initWXCircle(){
        CircleShareContent circleShareContent = new CircleShareContent();
        circleShareContent.setShareContent("装机必备的搜图app，专业提供各类高清壁纸");
        circleShareContent.setTargetUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.example.administrator.searchpicturetool");
        circleShareContent.setTitle("搜图app，专业提供各类高清壁纸");
        circleShareContent.setShareImage(new UMImage(context, R.drawable.ic_launcher));
        mController.setShareMedia(circleShareContent);
    }
}
