package com.medisafe.task.view.common.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import com.medisafe.task.R;

import androidx.core.content.ContextCompat;

public class ProgressView extends View {
    public ProgressView(Context context) {
        super(context);
        paint.setColor(ContextCompat.getColor(context,R.color.dark_orange));
        paint.setAntiAlias(true);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(ContextCompat.getColor(context, R.color.dark_orange));
        paint.setAntiAlias(true);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setColor(ContextCompat.getColor(context,R.color.dark_orange));
        paint.setAntiAlias(true);
    }

    private final Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        float x1 = getLeft() + getPaddingLeft();
        float x2 = getRight() - getPaddingRight();
        float y1 = getTop() + getPaddingTop();
        float y2 = getBottom() - getPaddingBottom();
        float w = x2 - x1;
        float d = w / 120f;
        float interpolation = getInterpolation();
        loop:
        for (float f1 = interpolation * PART - PART; true; f1 += PART) {
            float f2 = f1 + PART;
            float dx1 = f1 >= 0 ? (f1 * f1 - SHIFT) * w : 0f;
            float dx2 = (f2 * f2 - SHIFT) * w - d;
            if (dx1 < x1) {
                dx1 = x1;
            }
            if (dx2 > x2) {
                dx2 = x2;
            }
            if (dx1 >= x2) {
                break loop;
            }
            if (dx2 >= x1) {
                canvas.drawRect(dx1, y1, dx2, y2, paint);
            }
        }
        invalidate();
    }
    private static final float PART = 0.25f;
    private static final float SHIFT = 0.05f;

    private float getInterpolation() {
        return (float) (System.currentTimeMillis() % 500L) / 500f;
    }

}
