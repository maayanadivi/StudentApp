package com.example.maayanalice;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {

    public static ExecutorService executorService = Executors.newFixedThreadPool(4);
    final public static Handler mainHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    private static Context context;
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }
    public static Context getAppContext() {
        return MyApplication.context;
    }
}