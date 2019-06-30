package com.hyh.baselib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginApk {
    private PackageInfo mPackageInfo;
    private AssetManager mAssetMgr;
    //dex通过PathClassLoader加载
    private DexClassLoader mClassLoader;
    //图片以及资源文件靠Resources加载
    private Resources mResources;

    public PluginApk(PackageInfo packageInfo, DexClassLoader classLoader, Resources resources) {
        mPackageInfo = packageInfo;
        mClassLoader = classLoader;
        mResources = resources;
        mAssetMgr = resources.getAssets();
    }

    public PackageInfo getPackageInfo() {
        return mPackageInfo;
    }

    public AssetManager getAssetMgr() {
        return mAssetMgr;
    }

    public DexClassLoader getClassLoader() {
        return mClassLoader;
    }

    public Resources getResources() {
        return mResources;
    }
}
