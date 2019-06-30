package com.hyh.baselib;

import android.app.Activity;
import android.os.Bundle;

public interface IActivityPlugin {
    int FROM_INTERNAL = 0;//作为独立apk
    int FROM_EXTERNAL = 1;//作为插件apk

    void attach(Activity proxyActivity);
    void onCreate(Bundle saveInstanceState);
    void onStart();
    void onRestart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
