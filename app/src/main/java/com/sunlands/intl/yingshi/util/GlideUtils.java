package com.sunlands.intl.yingshi.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sunlands.intl.yingshi.R;



import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * 文件名: GlideUtils
 * 描    述: [加载各种图片]
 * 创建人: duzzi
 * 创建时间: 2018/6/6
 */
public class GlideUtils {


    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void loadImageBase64(Context context, String base64, ImageView imageView) {
        Glide.with(context)
                .load(stringToBitmap(base64))
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView, BitmapTransformation bitmapTransformation) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(bitmapTransformation))
                .into(imageView);
    }

    public static void loadImage(Context context, Drawable drawable, ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }

    public static void loadImageFix(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
//                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
//                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }

    public static void loadImage(Context context, Drawable drawable, ImageView imageView, BitmapTransformation bitmapTransformation) {
        Glide.with(context)
                .load(drawable)
                .apply(new RequestOptions()
//                        .placeholder(R.drawable.ic_thumbnail_1)
//                        .error(R.drawable.ic_thumbnail_1)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CenterCrop()))
                .into(imageView);
    }

    public static void loadRoundImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_avatar_default)
                        .error(R.drawable.ic_avatar_default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadRoundAvatarImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_avatar_default)
                        .error(R.drawable.ic_avatar_default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .apply(bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadIntoUseFitWidth(Context context, final String imageUrl, final ImageView imageView) {

        Glide.with(context).load(imageUrl).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                int w = resource.getMinimumWidth();
                int h = resource.getMinimumHeight();
                imageView.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(), (ScreenUtils.getScreenWidth() * h) / w));//720*365
                imageView.setBackground(resource);
            }
        });
    }

}
