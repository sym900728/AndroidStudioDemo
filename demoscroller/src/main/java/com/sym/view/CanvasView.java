package com.sym.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by Administrator on 2015/12/29.
 */
public class CanvasView extends View{

    private Paint defaultPaint;
    private Path path;

    public CanvasView(Context context) {
        super(context);
    }

    private void init() {
        defaultPaint = new Paint();
        defaultPaint.setColor(0xFF78D2F6);
        defaultPaint.setStrokeWidth(2.0f);
        defaultPaint.setStyle(Paint.Style.STROKE);
        defaultPaint.setAntiAlias(true);

        path = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void drawToCanvas(Canvas canvas) {
        Matrix matrix = new Matrix();
        matrix.setRotate(30, 0, 0);
        path.reset();
        path.moveTo(100, 100);
        path.lineTo(50, 50);
        path.lineTo(150, 50);
        path.close();
        path.addPath(path, matrix);
        canvas.drawPath(path, defaultPaint);
    }
}
