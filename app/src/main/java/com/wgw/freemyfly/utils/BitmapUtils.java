/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by wgw on 2018/6/6.
 */

public class BitmapUtils {

    /**
     * 创建缩略图方法
     * @param url 图片路径
     * @param i 压缩比例，最终为原图的1/（i^2）
     * @return 压缩后的图片
     */
    public static Bitmap onCreateThumbnail(String url,int i){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置不读取内存
        BitmapFactory.decodeFile(url,options);//通过编辑得到边界值，并存入options中
        options.inSampleSize = i;//赋值缩放比例
        options.inPreferredConfig = Bitmap.Config.RGB_565;//设置图片显示格式
        options.inJustDecodeBounds=false;//设置为读取内存
        return BitmapFactory.decodeFile(url,options);//得到压缩图片


    }
}
