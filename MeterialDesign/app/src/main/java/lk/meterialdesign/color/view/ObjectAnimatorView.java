package lk.meterialdesign.color.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by uiprj on 6/17/16.
 */
public class ObjectAnimatorView extends View {

    Paint mPaint;
    Point mPoint = new Point(100);

    public ObjectAnimatorView(Context context) {
        this(context,null);
    }

    public ObjectAnimatorView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ObjectAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if(mPoint !=null){
            mPaint = new Paint();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(500,500,mPoint.getmRadius(),mPaint);
        super.onDraw(canvas);
    }

    void setPointRadius(int radius){
        mPoint.setmRadius(radius);
        invalidate();
    }

}
