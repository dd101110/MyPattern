package com.yibin.mypattern.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 内存缓存
 * Created by yibin on 2017/7/12.
 */

public class MemoryCache implements ImageCacheInterface {

    private LruCache<String,Bitmap> mCache;

    public MemoryCache() {
        initCache();
    }

    /**
     * 初始化cache
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

    @Override
    public void put(String url, Bitmap bitmap) {
        mCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return mCache.get(url);
    }
}
