package com.hyh.baselib;

import android.app.Activity;
import android.os.Bundle;

/**
 * 插件apk的activity需要继承该activity
 */
public class PluginActivity extends Activity implements IActivityPlugin {
    public static final String KEY_FROM = "from";
    int mFrom = FROM_INTERNAL;

    //代理activity，如果是独立apk则是自己，如果作为插件apk则是宿主apk的某个activity
    private Activity mProxyActivity;

    @Override
    public void attach(Activity proxyActivity) {
        mProxyActivity = proxyActivity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if(saveInstanceState != null){
            mFrom = saveInstanceState.getInt(KEY_FROM);
        }
        if(mFrom == FROM_INTERNAL){
            super.onCreate(saveInstanceState);
            mProxyActivity = this;
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if(mFrom == FROM_INTERNAL){
            super.setContentView(layoutResID);
        }else{
            mProxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {
        if(mFrom == FROM_INTERNAL){
            super.onStart();
        }
    }

    @Override
    public void onRestart() {
        if(mFrom == FROM_INTERNAL){
            super.onRestart();
        }
    }

    @Override
    public void onResume() {
        if(mFrom == FROM_INTERNAL){
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if(mFrom == FROM_INTERNAL){
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if(mFrom == FROM_INTERNAL){
            super.onStop();
        }

    }

    @Override
    public void onDestroy() {
        if(mFrom == FROM_INTERNAL){
            super.onDestroy();
        }
    }
}
