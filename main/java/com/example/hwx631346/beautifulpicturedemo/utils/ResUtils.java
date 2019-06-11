package com.example.hwx631346.beautifulpicturedemo.utils;

import com.example.hwx631346.beautifulpicturedemo.BeautifulPictureApplication;

/**
 * 描述：獲取文件資源工具類
 */
public class ResUtils {

    /* 获取文件资源 */
    public static String getString(int strId) {
        return BeautifulPictureApplication.getContext().getResources().getString(strId);
    }
}
