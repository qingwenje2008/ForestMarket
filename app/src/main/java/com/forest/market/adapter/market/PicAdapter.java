package com.forest.market.adapter.market;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.forest.market.R;
import com.forest.market.util.ImageUtil;
import com.forest.market.util.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nmliz on 2016/11/30.
 */

public class PicAdapter extends BaseAdapter {

    List<String> stringList ;
    Context context;

    public PicAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        if (stringList.size() == 9) {
            return 9;
        }
        return (stringList.size() + 1);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_published_grida, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == stringList.size()) {

//            ImageLoader.getInstance().displayImage("drawable://" + R.mipmap.upload_btn, viewHolder.mItemGridImage);
            Glide.with(context).load(R.mipmap.btn_iimg).into(viewHolder.mItemGridImage);
            if (position == 9) {
                viewHolder.mItemGridImage.setVisibility(View.GONE);
            }
        } else {
            // ImageLoader.getInstance().displayImage("file:///" + stringList.get(position), viewHolder.mItemGridImage);
//            LoadImg.loadImg( viewHolder.mItemGridImage,stringList.get(position));
            LogUtil.i(stringList.get(position)+"+++++++");
            Glide.with(context).load(ImageUtil.loadImage(stringList.get(position))).into(viewHolder.mItemGridImage);
           /* ImageLoader.getInstance().displayImage(Constants.IMG_URL+picEntityList.get(position).getUrl(), new ImageViewAware(grid_image),
                    null, null);*/

        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_grid_image)
        ImageView mItemGridImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
