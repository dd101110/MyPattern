package com.yibin.mypattern.utils;

/**
 * Created by yibin on 2017/7/13.
 */

public class FileUtils {

    /**
     * 用“_”替换原始url里的“/”，并返回
     * @param url 原始url
     * @return
     */
    public static String getReplacedUrl(String url) {
        return url.replace("/","_");
    }

}
