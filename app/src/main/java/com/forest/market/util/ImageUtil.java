package com.forest.market.util;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by guoqingwen on 2016/07/28 14:10
 */
public class ImageUtil {

    private static DisplayImageOptions option;
    private static int lastRes;

    public static void loadImage(ImageView imageView, String path) {
        loadImage(imageView, path, 0);
    }

    public static void loadImageFromFile(ImageView imageView, String path) {
        ImageLoader.getInstance().displayImage("file:///"+path,imageView);
    }

    public static void loadImage(ImageView imageView, String path, int defRes) {
        if (defRes == 0) {
            ImageLoader.getInstance().displayImage(path, imageView);
            return;
        }
        if (defRes != lastRes) {
            option = new DisplayImageOptions.Builder()
                    .showImageOnLoading(defRes)
                    .showImageForEmptyUri(defRes)
                    .showImageOnFail(defRes)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
        }
        lastRes = defRes;
        ImageLoader.getInstance().displayImage(path, imageView, option);
    }
}
