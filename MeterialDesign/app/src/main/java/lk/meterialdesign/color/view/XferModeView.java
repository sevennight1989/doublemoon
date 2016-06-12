package lk.meterialdesign.color.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by uiprj on 6/12/16.
 */
public class XferModeView extends View {


    private Bitmap deskBp;
    private Bitmap srcBp;
    int width = 400;
    int height = 400;
    private Paint mPaint;

    public XferModeView(Context context) {
        this(context, null);
    }

    public XferModeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XferModeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        deskBp = makeDesk(width, height);
        srcBp = makeSrc(width, height);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layerId = canvas.saveLayer(0, 0, width * 2, height * 2, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(deskBp, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(srcBp, width / 2, height / 2, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    private Bitmap makeDesk(int w, int h) {
        Bitmap bp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bp);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w, h), p);
        return bp;
    }

    private Bitmap makeSrc(int w, int h) {
        Bitmap bp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bp);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(0xFF66AAFF);
        c.drawRect(0, 0, w, h, p);
        return bp;
    }

}
