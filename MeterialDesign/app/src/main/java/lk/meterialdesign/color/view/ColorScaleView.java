package lk.meterialdesign.color.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import lk.meterialdesign.R;

/**
 * Created by uiprj on 6/8/16.
 */
public class ColorScaleView extends View {

    Paint mPaint;
    Bitmap mTempBp;

    public ColorScaleView(Context context) {
        this(context, null);
    }

    public ColorScaleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTempBp = BitmapFactory.decodeResource(getResources(), R.mipmap.dog);
        mPaint.setAntiAlias(true);

//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//        -1, 0, 0, 0, 255,
//        0, -1, 0, 0, 255,
//        0, 0, -1, 0, 255,
//        0, 0, 0, 1, 0,});

//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//        0.213f, 0.715f, 0.072f, 0, 0,
//        0.213f, 0.715f, 0.072f, 0, 0,
//        0.213f, 0.715f, 0.072f, 0, 0,
//        0,       0,    0, 1, 0,
//        });

//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//        1/2f,1/2f,1/2f,0,0,
//        1/3f,1/3f,1/3f,0,0,
//        1/4f,1/4f,1/4f,0,0,
//        0,0,0,1,0
//        });

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
        0,1,0,0,0,
        1,0,0,0,0,
        0,0,1,0,0,
        0,0,0,1,0
        });

        //        ColorMatrix colorMatrix = new ColorMatrix();
//        colorMatrix.setScale(1, 1.3f, 1, 1);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mTempBp, null, new Rect(0, 0, mTempBp.getWidth(), mTempBp.getHeight()), mPaint);
    }
}
