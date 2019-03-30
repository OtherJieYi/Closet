package com.example.jieyi.closet.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class HomeScroolView extends View {
    private Paint paint;

    public HomeScroolView(Context context) {
        super(context);
        InitPaint();
    }

    public HomeScroolView(Context context, AttributeSet attrs){
        super(context,attrs);
        InitPaint();
    }

    private void InitPaint(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获得屏幕宽度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenW = displayMetrics.widthPixels;
        int Startx = screenW/6;
        int StopX = screenW/2;
        canvas.drawLine(Startx,0,StopX,0,paint);


    }



}
