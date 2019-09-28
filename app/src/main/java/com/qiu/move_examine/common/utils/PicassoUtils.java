package com.qiu.move_examine.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.widget.ImageView;

import com.qiu.move_examine.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.widget.ImageView;

/**
 * picasso 图片处理工具类<BR/>
 * 优缺点：对图片处理强大，取消已经不在视野范围的ImageView图片资源的加载（否则会导致图片错位），
 * 使用4.0+系统上的HTTP缓存来代替磁盘缓存；只能得到结果，不能监听图片下载过程
 * <BR/> Picasso 可以与okhttp搭配
 * <p>
 * 如果使用Picasso同时也使用了okhttp库，那么项目运行的时候可能会报出一下异常：
 * Picasso detected an unsupported OkHttp on the classpath
 * 针对该情况，网上说需要引入：compile 'com.squareup.okhttp:okhttp-urlconnection:2.2.0'
 * 即同时引入一下三个包：
 * compile 'com.squareup.okhttp:okhttp:2.4.0'
 * compile 'com.squareup.okhttp:okhttp-urlconnection:2.2.0'
 * compile 'com.squareup.picasso:picasso:2.4.0'
 *
 * @author Mr.Qiu
 */
public class PicassoUtils {

    private static PicassoUtils instance;

    public static PicassoUtils getinstance() {
        if (instance == null) {
            synchronized (PicassoUtils.class) {
                if (instance == null) {
                    instance = new PicassoUtils();
                }
            }
        }
        return instance;
    }

    public void loadImage(Context context, String path, ImageView imageView, int placeholderimage) {
        if (TextUtils.isEmpty(path)) {
            path = null;
        }
        Picasso.with(context).load(path).placeholder(placeholderimage).into(imageView);
    }

    public void loadCircleImage(Context context, String path, ImageView imageView, int placeholderimage) {
        Picasso.with(context).load(path).placeholder(placeholderimage).transform(new CircleTransform()).into(imageView);
    }

    public void loadRadiusImage(Context context, String path, ImageView imageView, int placeholderimage, float roundRadius) {
        Picasso.with(context).load(path).placeholder(placeholderimage).transform(new RoundTransform(roundRadius)).into(imageView);
    }

    /**
     * 设置圆形头像
     */
    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
    //------------------------------------------------------

    /**
     * 绘制圆角
     */
    public class RoundTransform implements Transformation {
        private float radius;

        public RoundTransform(float radius) {
            this.radius = radius;
        }

        @Override
        public String key() {
            return "round";
        }

        @Override
        public Bitmap transform(Bitmap bitmap) {
            int size = Math.min(bitmap.getWidth(), bitmap.getHeight());

            int x = (bitmap.getWidth() - size) / 2;
            int y = (bitmap.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(bitmap, x, y, size, size);
            if (squaredBitmap != bitmap) {
                bitmap.recycle();
            }
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, radius, radius, paint);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            squaredBitmap.recycle();
            return output;
        }

    }
}
