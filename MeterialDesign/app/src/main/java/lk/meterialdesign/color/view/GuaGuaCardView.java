package lk.meterialdesign.color.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import lk.meterialdesign.R;

/**
 * Created by uiprj on 6/12/16.
 */
public class GuaGuaCardView extends View {

    Paint mPaint;
    Bitmap mBmpText;
    Bitmap mBmpSrc;
    Bitmap mBmpDesk;
    float mPreX;
    float mPreY;
    Path mPath;

    public GuaGuaCardView(Context context) {
        this(context, null);
    }

    public GuaGuaCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuaGuaCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(45);
        mBmpText = BitmapFactory.decodeResource(getResources(), R.mipmap.guagua_text);
        mBmpSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.guaguaka_pic);
        mBmpDesk = Bitmap.createBitmap(mBmpSrc.getWidth(), mBmpSrc.getHeight(), Bitmap.Config
        .ARGB_8888);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBmpText, 0, 0, mPaint);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        Canvas c = new Canvas(mBmpDesk);
        c.drawPath(mPath, mPaint);

        canvas.drawBitmap(mBmpDesk, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(mBmpSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        postInvalidate();
        return super.onTouchEvent(event);
    }
}
