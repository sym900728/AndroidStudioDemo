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
    private Path path, pa;
    private Matrix matrix;

    public CanvasView(Context context) {
        super(context);
        init();
    }

    private void init() {
        defaultPaint = new Paint();
        defaultPaint.setColor(0xFF78D2F6);
        defaultPaint.setStrokeWidth(2.0f);
        defaultPaint.setStyle(Paint.Style.STROKE);
        defaultPaint.setAntiAlias(true);



        path = new Path();
        pa = new Path();
        matrix = new Matrix();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawToCanvas(canvas);
    }

    private void drawToCanvas(Canvas canvas) {
        drawCircle(canvas);
        drawTriangle(canvas);

        /*Matrix matrix = new Matrix();
        matrix.setRotate(150, 300, 300);
        pa.reset();
        path.reset();
        path.moveTo(100, 100);
        path.lineTo(50, 50);
        path.lineTo(150, 50);
        path.close();
        pa.addPath(path, matrix);
        canvas.drawPath(pa, defaultPaint);*/
    }

    private void drawCircle(Canvas canvas) {
        defaultPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300, 300 , 300, defaultPaint);
    }

    private void drawTriangle(Canvas canvas) {
        defaultPaint.setStyle(Paint.Style.FILL);
        path.reset();
        pa.reset();
        path.moveTo(300, 0);
        path.lineTo(250, 86);
        path.lineTo(350, 86);
        path.close();
        matrix.setRotate(180, 300, 300);
        path.transform(matrix);
        //pa.addPath(path, matrix);
        //pa.transform(matrix);
        canvas.drawPath(path, defaultPaint);
        //pa.reset();
        //matrix.setRotate(60, 300, 300);
        //pa.addPath(path, matrix);
        //canvas.drawPath(pa, defaultPaint);
    }
}
