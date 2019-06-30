package com.hyh.baselib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private static final PluginManager mInstance = new PluginManager();

    private PluginManager() {
    }

    public static PluginManager getInstance(){
        return mInstance;
    }

    private Context mContext;
    private PluginApk mPluginApk;

    public void init(Context context){
        mContext = context.getApplicationContext();
    }

    //加载apk文件
    public void loadApk(String apkPath){
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);

        DexClassLoader dexClassLoader = createDexClassLoader(apkPath);
        AssetManager assetManager = createAssetManager(apkPath);
        Resources resources = createResources(assetManager);

        mPluginApk = new PluginApk(packageInfo,dexClassLoader,resources);
    }

    //加载DexClassLoader，加载dex文件
    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mContext.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,mContext.getClassLoader());
    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(assetManager,apkPath);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //加载Resources，加载资源文件
    private Resources createResources(AssetManager assetManager) {
        Resources resources = mContext.getResources();
        return new Resources(assetManager,resources.getDisplayMetrics(),resources.getConfiguration());
    }

    public PluginApk getPluginApk() {
        return mPluginApk;
    }
}
