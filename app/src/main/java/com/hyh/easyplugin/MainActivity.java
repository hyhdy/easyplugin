package com.hyh.easyplugin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hyh.baselib.PluginManager;
import com.hyh.baselib.ProxyActivity;
import com.hyh.baselib.Utils;

import static com.hyh.baselib.Key.KEY_CLASS_NAME;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private Button mBtnLoadPlugin;
    private Button mBtnSkipPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
    }

    private void initviews(){
        mBtnLoadPlugin = findViewById(R.id.btn_load_plugin);
        mBtnSkipPlugin = findViewById(R.id.btn_skip_plugin);
        PluginManager.getInstance().init(this);
    }

    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.btn_load_plugin:{
                //加载插件apk
                String apkPath = Utils.getAssetsCacheFile(MainActivity.this,"plugina-debug.apk");
                PluginManager.getInstance().loadApk(apkPath);
            }
            break;
            case R.id.btn_skip_plugin:{
                //访问插件apk的某个activity
                Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
                intent.putExtra(KEY_CLASS_NAME,"com.hyh.plugina.MainActivity");
                startActivity(intent);
            }
            break;
        }
    }
}
