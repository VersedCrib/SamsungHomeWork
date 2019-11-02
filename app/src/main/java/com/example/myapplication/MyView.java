package com.example.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;


public class MyView extends View {
    int N = 10; // количество шариков
    float[] x  = new float[N];
    float[] y  = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];

    public MyView(Context context) {
        super(context);
        for (int i = 0; i < N; i++){
            x[i] = (float)(Math.random() * 500);
            y[i] = (float)(Math.random() * 500);
            vx[i] = (float)(Math.random() * 6 - 3);
            vy[i] = (float)(Math.random() * 6 - 3);
        }
    }

    Paint paint = new Paint();
    @Override
    protected void onDraw(Canvas canvas){

        for (int i = 0; i < N-1; i++) {
            canvas.drawLine(x[i], y[i], x[i + 1], y[i + 1], paint);
        }

        for (int i = 0; i < N-1; i++) {
            if (x[i] < 0 || x[i] > this.getHeight()){

                vx[i] = - vx[i];

            }
            if (y[i] < 0 || y[i] > this.getWidth()) {

                vy[i] = -vy[i];

            }

            /*if (x[i] < 0 || x[i] > this.getHeight()){

                canvas.drawCircle(x[i], y[i], 20, paint);

            }*/

            x[i] += vx[i];
            y[i] += vy[i];
        }



        invalidate();
    }



}