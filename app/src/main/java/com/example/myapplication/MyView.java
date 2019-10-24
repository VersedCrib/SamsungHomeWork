package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        int y = 0;
        while (y < canvas.getHeight()) {
            paint.setColor(Color.GREEN);
            canvas.drawLine(0, y, this.getWidth(), y, paint);
            paint.setColor(Color.RED);
            canvas.drawLine(y, y, canvas.getWidth(),canvas.getHeight(),paint);
            y += 30;
        }
    }
}