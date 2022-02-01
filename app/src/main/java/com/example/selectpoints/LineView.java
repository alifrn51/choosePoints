package com.example.selectpoints;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class LineView extends View {

    private Paint mPaint = new Paint();
    private Point mPointStart , mPontEnd;
    private int colorLine;

    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(colorLine);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(mPointStart.getX(), mPointStart.getY(), mPontEnd.getX(), mPontEnd.getY(), mPaint);
        draw();
        super.onDraw(canvas);
    }

    public void setColorLine(int colorLine) {
        this.colorLine = colorLine;
    }

    public void setPointStart(Point mPointStart) {
        this.mPointStart = mPointStart;
    }

    public void setPointEnd(Point mPontEnd) {
        this.mPontEnd = mPontEnd;
    }

    public void draw(){
        invalidate();
        requestLayout();
    }
}
