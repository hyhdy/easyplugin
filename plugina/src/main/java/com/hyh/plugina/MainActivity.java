package com.hyh.plugina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hyh.baselib.PluginActivity;

public class MainActivity extends PluginActivity {
    public static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        Log.d(TAG,"onStart: ");
        super.onStart();
    }

    @Override
    public void onRestart() {
        Log.d(TAG,"onRestart: ");
        super.onRestart();
    }

    @Override
    public void onResume() {
        Log.d(TAG,"onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG,"onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG,"onStop: ");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy: ");
        super.onDestroy();
    }
}
