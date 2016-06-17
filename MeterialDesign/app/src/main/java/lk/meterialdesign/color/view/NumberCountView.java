package lk.meterialdesign.color.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by uiprj on 6/16/16.
 */
public class NumberCountView extends View {

    private Paint mPaint;

    private RectF mRectF;

    private String mNumber;

    float mStartX = 10;
    float mStartY = 10;
    float mWidth = 100L;
    float mHeight = 100L;
    int mSpace = 15;
    int mCount = 0;
    char mS[];
    int mMaxCount = 0;

    public NumberCountView(Context context) {
        this(context, null);
    }

    public NumberCountView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mRectF = new RectF(0, 0, mWidth, mHeight);
    }

    public void update(int number) {



        if (number < 10) {
            mNumber = "00" + number;
        } else if (number < 100) {
            mNumber = "0" + number;
        } else {
            mNumber = number + "";
        }
//        for(int i = 0;i<mMaxCount;i++){
//
//        }

        mS = mNumber.toCharArray();
        mCount = mS.length;


        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mCount; i++) {
            //draw rectf
            canvas.translate(mStartX + i * mWidth + i * mSpace, mStartY);
            mPaint.setColor(Color.BLUE);
            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(10);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(mRectF, 5, 5, mPaint);

            canvas.translate(-mStartX + i * mWidth * -1 - i * mSpace, -mStartY);
            //draw text
            mPaint.setColor(Color.RED);
            mPaint.setTextSize(60);
            mPaint.setStrokeWidth(5);
            canvas.translate(mWidth / 2 + i * mWidth + i * mSpace, mStartY);
            canvas.drawText(String.valueOf(mS[i]), 0, 70, mPaint);
            canvas.translate(mWidth / 2 * -1 - i * mWidth - i * mSpace, -mStartY);
        }
    }

}
