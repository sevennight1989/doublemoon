package lk.meterialdesign.cust.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lk.meterialdesign.R;

/**
 * Created by uiprj on 7/4/16.
 */
public class CollapseView extends LinearLayout implements View.OnClickListener {

    int parentWidthMeasureSpec;
    int parentHeightMeasureSpec;

    private static final String TAG = "PengLog";
    long duration = 350;
    RelativeLayout mTitleRelativeLayout;
    RelativeLayout mContentRelativeLayout;
    TextView mNumberTextView;
    TextView mTitleTextView;
    ImageView mArrowImageView;


    public CollapseView(Context context) {
        this(context, null);
    }

    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViews();
        collapse(mContentRelativeLayout);
    }

    private void initViews() {
        mTitleRelativeLayout = (RelativeLayout) findViewById(R.id.titleRelativeLayout);
        mContentRelativeLayout = (RelativeLayout) findViewById(R.id.contentRelativeLayout);
        mNumberTextView = (TextView) findViewById(R.id.numberTextView);
        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        mArrowImageView = (ImageView) findViewById(R.id.arrowImageView);
        if (mTitleRelativeLayout != null) {
            mTitleRelativeLayout.setOnClickListener(this);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthMeasureSpec = widthMeasureSpec;
        parentHeightMeasureSpec = heightMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleRelativeLayout:
                rotateArrow();
                break;

            default:
                break;
        }
    }

    private void rotateArrow() {
        int degree;
        if (mArrowImageView.getTag() == null || mArrowImageView.getTag().equals(true)) {
            degree = -180;
            expand(mContentRelativeLayout);
            mArrowImageView.setTag(false);
        } else {
            degree = 0;
            collapse(mContentRelativeLayout);
            mArrowImageView.setTag(true);
        }
        mArrowImageView.animate().setDuration(duration).rotation(degree);

    }

    private void collapse(final View view) {
        final int measureHeight = view.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(GONE);
                } else {
                    view.getLayoutParams().height = measureHeight - (int) (measureHeight *
                    interpolatedTime);
                    requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    private void expand(final View view) {
        view.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        final int measuredHeight = view.getMeasuredHeight();
        view.setVisibility(View.VISIBLE);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.getLayoutParams().height = measuredHeight;
                } else {
                    view.getLayoutParams().height = (int) (interpolatedTime * measuredHeight);
                }
                requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }
}
