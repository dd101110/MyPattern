package com.yibin.mypattern.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 磁盘缓存
 * Created by yibin on 2017/7/12.
 */

public class DiskCache implements ImageCacheInterface {

    public static final String CACHE_DIR = "sdcard/cache/";

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream outputStream = null;
        try {
            File folder = new File(CACHE_DIR);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File file = new File(folder.getAbsolutePath()  + FileUtils.getReplacedUrl(url));
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(CACHE_DIR + FileUtils.getReplacedUrl(url));
    }
}
