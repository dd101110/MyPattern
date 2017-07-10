package com.yibin.mypattern.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1、加载图片
 * 2、图片缓存
 * Created by yibin on 2017/7/10.
 */

public class ImageLoader {

    public static final int MSG_OK = 1;

    private LruCache<String,Bitmap> mCache;

    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private ImageView imageView;
    private Bitmap bitmap;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_OK) {
                imageView.setImageBitmap(bitmap);
            }
        }
    };

    public ImageLoader(ImageView imageView) {
        this.imageView = imageView;
        initCache();
    }

    /**
     * 初始化图片缓存容器
     */
    private void initCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    /**
     * 根据url下载图片
     * @param imgUrl 图片url
     * @return 图片bitmap
     */
    public Bitmap downloadImg(String imgUrl) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(imgUrl);
            connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection) connection.disconnect();
        }
        return bitmap;
    }

    /**
     * 加载图片
     * @param imgUrl 图片url
     */
    public void displayImg(final String imgUrl) {
        imageView.setTag(imgUrl);
        try {
            mExecutorService.submit(new Runnable() {
                @Override
                public void run() {
                    bitmap = downloadImg(imgUrl);
                    if (null == bitmap) return;
                    if (imageView.getTag().equals(imgUrl)) {
                        imageView.setImageBitmap(bitmap);
    //                    handler.sendEmptyMessage(MSG_OK);
                        mCache.put(imgUrl,bitmap);
                    }
                }
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
