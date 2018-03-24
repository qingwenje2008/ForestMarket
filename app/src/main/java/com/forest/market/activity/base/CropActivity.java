package com.forest.market.activity.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;


import com.edmodo.cropper.CropImageView;
import com.forest.market.R;
import com.forest.market.util.LogUtil;
import com.forest.market.util.TimeUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CropActivity extends BaseActivity implements View.OnClickListener {

    private TextView cancel;
    private TextView confirm;
    private CropImageView imageView;
    private File directory;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_crop);
//
//        initView();
//    }

    @Override
    protected void initData() {
        directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_crop;
    }

    protected void initView() {
        cancel = (TextView) findViewById(R.id.cancel);
        confirm = (TextView) findViewById(R.id.confirm);
        imageView = (CropImageView) findViewById(R.id.imageView);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        int x = getIntent().getIntExtra("x", 1);
        int y = getIntent().getIntExtra("y", 1);
        String path = getIntent().getStringExtra("path");
        imageView.setAspectRatio(x, y);
        imageView.setFixedAspectRatio(true);
        imageView.setImageBitmap(getBitmap(path));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.confirm:
                File cropFile = new File(directory, "temp" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
                saveBitmap(imageView.getCroppedImage(), cropFile);
                break;
        }
    }


    private void saveBitmap(Bitmap bitmap, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            String path = file.getAbsolutePath();
            Intent intent = new Intent();
            intent.putExtra("cropPath", path);
            LogUtil.i("cropPath==="+path);
            setResult(RESULT_OK, intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bitmap!=null){
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

    private Bitmap getBitmap(String path){
        Bitmap bitmap= BitmapFactory.decodeFile(path);
        int degree=getBitmapDegree(path);
        LogUtil.i("bitmap=="+bitmap+"=="+degree);
        return rotateBitmapByDegree(bitmap,degree);
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

    private Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }
}
