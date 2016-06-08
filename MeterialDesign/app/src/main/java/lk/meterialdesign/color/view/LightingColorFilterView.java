package lk.meterialdesign.color.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import lk.meterialdesign.R;

/**
 * Created by uiprj on 6/8/16.
 */
public class LightingColorFilterView extends View {

    Bitmap mTempBp;
    Paint mPaint;
    public LightingColorFilterView(Context context) {
        this(context,null);
    }

    public LightingColorFilterView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LightingColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mTempBp = BitmapFactory.decodeResource(getResources(), R.mipmap.bluebutton);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width  = 500;
        int height = width * mTempBp.getHeight()/mTempBp.getWidth();
        mPaint.reset();
        canvas.drawBitmap(mTempBp,null,new Rect(0,0,width,height),mPaint);
        canvas.translate(0,550);
        mPaint.setColorFilter(new LightingColorFilter(0x00ff00,0x000000));
        canvas.drawBitmap(mTempBp,null,new Rect(0,0,width,height),mPaint);
        canvas.translate(0,550);
        mPaint.setColorFilter(new LightingColorFilter(0xffffff,0x0000f0));
        canvas.drawBitmap(mTempBp,null,new Rect(0,0,width,height),mPaint);

    }
}
