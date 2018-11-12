package com.fb.sdclibrary.widget.ninegridview;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideImageLoader implements INineGridImageLoader {
    @Override
    public void displayNineGridImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    @Override
    public void displayNineGridImage(Context context, String url, ImageView imageView, int width, int height) {
        Glide.with(context).load(url)
                .override(width, height)
                .into(imageView);
    }
}
