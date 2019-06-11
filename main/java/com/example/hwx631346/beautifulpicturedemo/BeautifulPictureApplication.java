package com.example.hwx631346.beautifulpicturedemo;

import android.app.Application;

public class BeautifulPictureApplication extends Application {
    private static BeautifulPictureApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static BeautifulPictureApplication getContext() {
        return context;
    }
}
