package lk.meterialdesign.color.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import lk.meterialdesign.R;

/**
 * Created by uiprj on 6/13/16.
 */
public class HeartView extends View {

    Paint mPaint;
    int dx = 0;
    Bitmap mSrcBp;
    Bitmap mDstBp;
    int mItemWaveLength = 0;

    public HeartView(Context context) {
        this(context, null);
    }

    public HeartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mSrcBp = BitmapFactory.decodeResource(getResources(), R.mipmap.hearmap);
        mDstBp = Bitmap.createBitmap(mSrcBp.getWidth(), mSrcBp.getHeight(), Bitmap.Config.ARGB_8888);
        mItemWaveLength = mSrcBp.getWidth();
        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Canvas c = new Canvas(mDstBp);
        c.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        c.drawRect(mDstBp.getWidth() - dx, 0, mDstBp.getWidth(), mDstBp.getHeight(), mPaint);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mSrcBp, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mDstBp, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }


    private void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(6000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

}
