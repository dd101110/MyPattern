package com.yibin.mypattern.utils;

import android.graphics.Bitmap;

/**
 * 双重缓存策略：内存缓存+磁盘缓存
 * Created by yibin on 2017/7/12.
 */

public class DoubleCache implements ImageCacheInterface {

    private MemoryCache mMemoryCache;
    private DiskCache mDiskCache;

    public DoubleCache() {
        initCache();
    }

    private void initCache() {
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache();
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (null == bitmap) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }
}
