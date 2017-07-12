package com.yibin.mypattern.utils;

import android.graphics.Bitmap;

/**
 * Created by yibin on 2017/7/12.
 */

public interface ImageCacheInterface {

    void put(String url, Bitmap bitmap);
    Bitmap get(String url);

}
