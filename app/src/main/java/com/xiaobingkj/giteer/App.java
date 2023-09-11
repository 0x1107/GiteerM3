package com.xiaobingkj.giteer;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import com.tencent.mmkv.MMKV;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import java.util.HashMap;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        String rootDir = MMKV.initialize(this);
        Log.d("App", "MMKV 根目录: " + rootDir);


        // 在调用TBS初始化、创建WebView之前进行如下配置
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.setTbsListener(new TbsListener() {

            /**
             * @param stateCode 110: 表示当前服务器认为该环境下不需要下载
             */
            @Override
            public void onDownloadFinish(int stateCode) {
                Log.i(TAG, "onDownloadFinish: " + stateCode);
            }

            /**
             * @param stateCode 200、232安装成功
             */
            @Override
            public void onInstallFinish(int stateCode) {
                Log.i(TAG, "X5 onInstallFinished: " + stateCode);
            }

            /**
             * 首次安装应用，会触发内核下载，此时会有内核下载的进度回调。
             * @param progress 0 - 100
             */
            @Override
            public void onDownloadProgress(int progress) {
                Log.i(TAG, "X5 onDownloadProgress: " + progress);
            }
        });

        /* 此过程包括X5内核的下载、预初始化，接入方不需要接管处理x5的初始化流程，希望无感接入 */
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                // 内核初始化完成，可能为系统内核，也可能为系统内核
                Log.i(TAG, "X5 onCoreInitFinished");
            }

            /**
             * 预初始化结束
             * 由于X5内核体积较大，需要依赖wifi网络下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
             * 内核下发请求发起有24小时间隔，卸载重装、调整系统时间24小时后都可重置
             * @param isX5 是否使用X5内核
             */
            @Override
            public void onViewInitFinished(boolean isX5) {
                Log.i(TAG, "X5 onViewInitFinished: " + isX5);
            }
        });
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
