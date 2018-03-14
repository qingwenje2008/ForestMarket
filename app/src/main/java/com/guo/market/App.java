package com.guo.market;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by guoqingwen on 2016/12/15 10:02
 */
public class App extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initImageLoader();
    }

    private void initImageLoader() {
        DisplayImageOptions defOption = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.default_cover)
//                .showImageForEmptyUri(R.drawable.default_cover)
//                .showImageOnFail(R.drawable.default_cover)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        File cacheDir = StorageUtils.getOwnCacheDirectory(this, "imageLoader/cache");
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.memoryCacheExtraOptions(480, 800);
        config.threadPoolSize(3);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.memoryCacheSize(5 * 1024 * 1024);
        config.diskCacheSize(50 * 1024 * 1024);
        config.diskCacheFileCount(100);
        config.diskCache(new UnlimitedDiskCache(cacheDir));
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.defaultDisplayImageOptions(defOption);
        ImageLoader.getInstance().init(config.build());
        L.writeLogs(false);
    }
//
//    private void initThird() {
//        UMShareAPI.get(this);
//        PlatformConfig.setWeixin(ThirdConfig.WX_AppID, ThirdConfig.WX_AppSecret);
//        // PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
//        PlatformConfig.setQQZone(ThirdConfig.QQ_APPID, ThirdConfig.QQ_APPKEY);
//    }

}
