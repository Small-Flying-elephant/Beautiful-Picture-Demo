package com.example.hwx631346.beautifulpicturedemo.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.example.hwx631346.beautifulpicturedemo.BeautifulPictureApplication;

/**
 * 描述：toast 工具類
 */
public class ToastUtils {

    public static void shortToast(String msg) {
        Toast toast = Toast.makeText(BeautifulPictureApplication.getContext(),msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 40);
        toast.show();
    }

    public static void longToast(String msg) {
        Toast toast = Toast.makeText(BeautifulPictureApplication.getContext(),msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 40);
        toast.show();
    }
}
