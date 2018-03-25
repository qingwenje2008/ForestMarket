package com.forest.market.adapter.market;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.forest.market.R;
import com.forest.market.adapter.base.ListBaseAdapter;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by qingwenguo on 2018/3/25.
 */

public class GridImageAdapter extends ListBaseAdapter {
    public GridImageAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        ImageView iv = null;
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_img, null);
            AutoUtils.auto(view);
//           iv=new ImageView(context);

//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            ViewGroup.LayoutParams lp = iv.getLayoutParams();
//            lp.height = 170;
//            iv.setLayoutParams(lp);

            vh = new ViewHolder(view);
            vh.iv_image.setImageResource(R.mipmap.img_zhanwei);
            view.setTag(vh);
        } else {
            vh= (ViewHolder) view.getTag();
            vh.iv_image.setImageResource(R.mipmap.img_zhanwei);
        }
//        vh.iv_image.setImageResource(R.mipmap.img_zhanwei);
//        vh.content.setText(comment.getContent());
        return view;
    }
    protected class ViewHolder {
        private ImageView iv_image;

        public ViewHolder(View v){
            iv_image = (ImageView) v.findViewById(R.id.iv_image);

        }

    }
}
