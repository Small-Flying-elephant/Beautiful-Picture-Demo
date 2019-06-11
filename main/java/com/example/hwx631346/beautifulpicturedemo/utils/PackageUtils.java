package com.example.hwx631346.beautifulpicturedemo.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.hwx631346.beautifulpicturedemo.BeautifulPictureApplication;

public class PackageUtils {

    public static int packageCode() {
        PackageManager manager = BeautifulPictureApplication.getContext().getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(BeautifulPictureApplication.getContext().getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String packageName() {
        PackageManager manager = BeautifulPictureApplication.getContext().getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(BeautifulPictureApplication.getContext().getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }
}
