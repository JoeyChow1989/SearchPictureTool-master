package com.example.administrator.searchpicturetool.model;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.example.administrator.searchpicturetool.config.API;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jude.utils.JUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * Created by wenhuaijun on 2015/11/7 0007.
 * 这就是不用Rxjava的痛点，IO线程和UI线程进行切换需要层层嵌套
 */
@Deprecated
public class TheOldSaveBitmapModel {
    public static void saveBitmap(final Activity activity,Context context,String url, final SaveCallBack callBack){
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);

        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            protected void onNewResultImpl(final Bitmap bitmap) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String name = "/" + System.currentTimeMillis() + ".png";
                        File file = new File(API.imgPath);
                        if (!file.exists()) {
                            JUtils.Log("!file.exists()");
                            file.mkdir();
                        }
                        try {
                            file = new File(API.imgPath + name);
                            if (file.createNewFile()) {
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                               // BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                                //BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                                //  Bitmap mBitmap = Bitmap.createScaledBitmap(bitmap,width,height,true);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                                fileOutputStream.flush();
                                fileOutputStream.close();

                            } else {
                                final String path1 = file.toString();
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        callBack.onSuccess(path1);
                                    }
                                });

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onError(0);
                                }
                            });
                        }
                        final String path =file.toString();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(path);
                            }
                        });


                    }
                }).start();


            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                callBack.onError(1);
            }
        }, CallerThreadExecutor.getInstance());



        }

        public static void setWrapper(final Activity activity,final Context context, final String url, final SaveCallBack callBack){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ImageRequest imageRequest = ImageRequestBuilder
                            .newBuilderWithSource(Uri.parse(url))
                            .setProgressiveRenderingEnabled(true)
                            .build();
                    ImagePipeline imagePipeline = Fresco.getImagePipeline();
                    DataSource<CloseableReference<CloseableImage>>
                            dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
                    dataSource.subscribe(new BaseBitmapDataSubscriber() {
                        @Override
                        protected void onNewResultImpl(Bitmap bitmap) {

                            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                            try {
                                wallpaperManager.setBitmap(bitmap);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        callBack.onSuccess("success");
                                    }
                                });


                            } catch (IOException e) {
                                e.printStackTrace();
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        callBack.onError(0);
                                    }
                                });

                            }
                        }

                        @Override
                        protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onError(0);
                                }
                            });
                        }
                    },CallerThreadExecutor.getInstance());
                }
            }).start();

        }

    public static void setLockWrapper(final Activity activity,final Context context, final String url, final SaveCallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageRequest imageRequest = ImageRequestBuilder
                        .newBuilderWithSource(Uri.parse(url))
                        .setProgressiveRenderingEnabled(true)
                        .build();
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                DataSource<CloseableReference<CloseableImage>>
                        dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
                dataSource.subscribe(new BaseBitmapDataSubscriber() {
                    @Override
                    protected void onNewResultImpl(Bitmap bitmap) {
                        try {
                            WallpaperManager mWallManager =WallpaperManager.getInstance(context);
                            Class class1 =mWallManager.getClass();
                            Method setWallPaperMethod =class1.getMethod("setBitmapToLockWallpaper",Bitmap.class);
                           setWallPaperMethod.invoke(mWallManager,bitmap);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onSuccess("success");
                                }
                            });


                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onError(0);
                                }
                            });

                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onError(0);
                                }
                            });
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onError(0);
                                }
                            });
                        }
                    }

                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onError(0);
                            }
                        });
                    }
                },CallerThreadExecutor.getInstance());
            }
        }).start();
    }
        public interface SaveCallBack{
        public void onSuccess(String path);
        public void onError(int status);

    }
}
