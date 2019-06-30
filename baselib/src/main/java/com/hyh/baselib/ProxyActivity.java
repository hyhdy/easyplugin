package com.hyh.baselib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import static com.hyh.baselib.IActivityPlugin.FROM_EXTERNAL;
import static com.hyh.baselib.Key.KEY_CLASS_NAME;
import static com.hyh.baselib.PluginActivity.KEY_FROM;

/**
 * activity代理类，通过代理访问插件apk的activity
 */
public class ProxyActivity extends Activity {
    public static final String TAG = "ProxyActivity";
    private String mClassName;
    private PluginApk mPluginApk;
    //插件apk的activity
    private IActivityPlugin mIActivityPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate: ");
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra(KEY_CLASS_NAME);
        mPluginApk = PluginManager.getInstance().getPluginApk();
        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if(mPluginApk == null){
            return;
        }
        try {
            Class<?> clazz = mPluginApk.getClassLoader().loadClass(mClassName);
            Object object = clazz.newInstance();
            if(object instanceof IActivityPlugin){
                mIActivityPlugin = (IActivityPlugin) object;
                mIActivityPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_FROM,FROM_EXTERNAL);
                mIActivityPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mIActivityPlugin == null){
            return;
        }
        mIActivityPlugin.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mIActivityPlugin == null){
            return;
        }
        mIActivityPlugin.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mIActivityPlugin == null){
            return;
        }
        mIActivityPlugin.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mIActivityPlugin == null){
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mIActivityPlugin == null){
            return;
        }
        mIActivityPlugin.onDestroy();
    }

    @Override
    public Resources getResources() {
        return mPluginApk == null ? super.getResources():mPluginApk.getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk == null ? super.getClassLoader():mPluginApk.getClassLoader();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk == null ? super.getAssets():mPluginApk.getAssetMgr();
    }
}
