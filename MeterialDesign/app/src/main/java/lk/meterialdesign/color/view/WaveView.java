package lk.meterialdesign.color.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import lk.meterialdesign.R;

/**
 * Created by uiprj on 6/13/16.
 */
public class WaveView extends View {

    Paint mPaint;
    int dx = 0;
    int mItemWaveLength = 0;
    Bitmap mSrcBp;
    Bitmap mDstBp;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mSrcBp = BitmapFactory.decodeResource(getResources(), R.mipmap.circle_shape);
        mDstBp = BitmapFactory.decodeResource(getResources(), R.mipmap.wave);
        mItemWaveLength = mDstBp.getWidth();
        startAnim();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mSrcBp, 0, 0, mPaint);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mDstBp, new Rect(dx, 0, dx + mSrcBp.getWidth(), mSrcBp.getHeight()),
        new Rect(0, 0, mSrcBp.getWidth(), mSrcBp.getHeight()), mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mSrcBp,0,0,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(4000);
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
