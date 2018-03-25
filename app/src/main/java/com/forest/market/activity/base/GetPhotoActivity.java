package com.forest.market.activity.base;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;


import com.forest.market.dialog.BottomMenuDialog;
import com.forest.market.dialog.OnItemClickListener;
import com.forest.market.util.LogUtil;
import com.forest.market.util.TimeUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：任少东
 * 日期：2016/8/4 9:27
 */
public class GetPhotoActivity extends BaseActivity {

    private final int REQUEST_CAMERA = 1121;
    private final int REQUEST_PHOTO = 1122;
    private final int REQUEST_CROP = 1123;
    private File directory;
    private Uri uri;
    private boolean isCrop;
    private List<File> temps;
    protected BottomMenuDialog dialog;
    private int w = 1, h = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        temps = new ArrayList<>();
        dialog = new BottomMenuDialog(this);
        dialog.setMenus(new String[]{"拍照", "从手机相册选择"});
        dialog.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(Dialog dialog, int position) {
                requestPermission(position, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA});
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onRequestPermissionsResult(int requestCode, boolean isSuccess) {
        if (isSuccess) {
            if (requestCode == 0) {
                useCamera();
            } else if (requestCode == 1) {
                useThumb();
            }
        }
    }

    protected void selectPhoto(int w, int h) {
        this.w = w;
        this.h = h;
        this.isCrop = true;
        dialog.show();
    }

    protected void selectPhoto() {
        this.isCrop = false;
        dialog.show();
    }


    protected void useCamera() {
        File file = new File(directory, "IMG_" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
        uri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    protected void useCamera(boolean isCrop) {
        this.isCrop = isCrop;
        File file = new File(directory, "IMG_" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
        uri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    protected void useThumb() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PHOTO);
    }

    protected void useThumb(boolean isCrop) {
        this.isCrop = isCrop;
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PHOTO);
    }

    private void crop(String path) {
        Bundle bundle = new Bundle();
        bundle.putInt("x", w);
        bundle.putInt("y", h);
        bundle.putString("path", path);
        openActivityForResult(CropActivity.class, REQUEST_CROP, bundle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CAMERA:
                    String path = getUriPath(uri);
                    rotateBitmap(path);
                    if (isCrop) {
                        crop(path);
                    } else {
                        onSelectPic(uri, path);
                    }
                    break;
                case REQUEST_PHOTO:
                    uri = data.getData();
                    if (isCrop) {
                        crop(getUriPath(uri));
                    } else {
                        onSelectPic(uri, getUriPath(uri));
                    }
                    break;
                case REQUEST_CROP:
                    String cropPath = data.getStringExtra("cropPath");
                    LogUtil.i("crop==" + cropPath);
                    onCrop(cropPath);
                    temps.add(new File(cropPath));
                    break;
            }
        }
    }

    protected void onSelectPic(Uri uri, String filePath) {

    }

    protected void onCrop(String path) {

    }


    protected String getUriPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndex("_data"));

            cursor.close();
            return path;
        }
        return uri.getPath();
    }

    public void showPic(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (File file : temps) {
            file.delete();
        }
    }


    public void rotateBitmap(String path) {
        int rotate = getBitmapDegree(path);

//        Bitmap bitmap= rotateBitmapByDegree(BitmapFactory.decodeFile(path),rotate);
        Bitmap bitmap = rotateBitmapByDegree(createImageThumbnail(path, 4), rotate);
        saveBitmap(bitmap, new File(path));
    }

    private void saveBitmap(Bitmap bitmap, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * 二次采样缩小图片所占大小
     *
     * @param filepath  图片路径
     * @param newWidth  目标图片的宽度
     * @param newheight 目标图片的高度
     * @return
     */
    private Bitmap createImageThumbnail(String filepath, int newWidth, int newheight) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //此时并不会为  BitmapFactory.decodeFile(filepath, opts)分配空间
        //因为已经设置了inJustDecodeBounds为fales;
        opts.inJustDecodeBounds = true;
        //此时opts已经包含了未裁剪的宽高
        BitmapFactory.decodeFile(filepath, opts);

        //得到原图片的宽高
        int oldHeight = opts.outHeight;
        int oldWidth = opts.outWidth;

        //获取新旧图片的比例
        int rationHeight = oldHeight / newheight;
        int rationWidth = oldWidth / newWidth;
        opts.inSampleSize = rationHeight > rationWidth ? rationWidth : rationHeight;

        //确定bitmap的单位字节像素
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        opts.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(filepath, opts);
        return bm;
    }

    /**
     * 设置裁剪比例
     *
     * @param filepath     路径
     * @param inSampleSize 裁剪比例
     * @return
     */
    private Bitmap createImageThumbnail(String filepath, int inSampleSize) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //此时并不会为  BitmapFactory.decodeFile(filepath, opts)分配空间
        //因为已经设置了inJustDecodeBounds为fales;
        opts.inJustDecodeBounds = true;
        //此时opts已经包含了未裁剪的宽高
        BitmapFactory.decodeFile(filepath, opts);

        //得到原图片的宽高
        int oldHeight = opts.outHeight;
        int oldWidth = opts.outWidth;

        //获取新旧图片的比例
//        int rationHeight=oldHeight/newheight;
//        int rationWidth=oldWidth/newWidth;
//        opts.inSampleSize=rationHeight>rationWidth?rationWidth:rationHeight;
        opts.inSampleSize = inSampleSize;
        //确定bitmap的单位字节像素
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        opts.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(filepath, opts);
        return bm;
    }

    public void setCrop(boolean crop) {
        isCrop = crop;
    }
}