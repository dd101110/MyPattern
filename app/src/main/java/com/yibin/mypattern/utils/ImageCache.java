package com.yibin.mypattern.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by yibin on 2017/7/12.
 */

public class ImageCache {

    private LruCache<String,Bitmap> mCache;


    public ImageCache() {
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

    public void put(String url,Bitmap bitmap) {
        mCache.put(url,bitmap);
    }

    public Bitmap get(String url) {
        return mCache.get(url);
    }
}
