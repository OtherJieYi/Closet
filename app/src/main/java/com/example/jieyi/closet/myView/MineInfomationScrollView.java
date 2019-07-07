package com.example.jieyi.closet.myView;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MineInfomationScrollView extends ScrollView {


    public MineInfomationScrollView(Context context) {
        super(context);
        init();
    }

    public MineInfomationScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //定义全局变量
    private int down_y;//手指点到的纵坐标
    private View childView;//srcollview的第一个布局
    private boolean rebound = false;//是否回弹
    private Rect rect = new Rect();
    private int reboundDirection =0;//回弹距离
    private ImageView backImageView;
    private ImageView garyBackImageView;
    private Rect rectBack = new Rect();
    private Rect rectGrayBack = new Rect();

    private void init(){
        // 禁用下拉到两端发荧光的效果
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public void setBackImageView(ImageView backImageView,ImageView garyBackImageView){
        this.backImageView = backImageView;
        this.garyBackImageView = garyBackImageView;
    }

    //获取手势动作
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(childView == null){
            return super.dispatchTouchEvent(ev);
        }

        if(ev.getAction() == ev.ACTION_DOWN){
            //初次触碰
            down_y = (int)ev.getY();
        }
        if(ev.getAction() == ev.ACTION_MOVE){
            if(getScrollY() != 0){//非处于顶部
                down_y = (int)ev.getY();
                return super.dispatchTouchEvent(ev);
            }
            int deltaY = (int)ev.getY() -(int)down_y;
            int offset = (int)(deltaY*0.18);
            int offsetBack = (int) (offset*0.5);

            childView.layout(rect.left,rect.top+ offset,rect.right ,rect.bottom+offset);
            backImageView.layout(rectBack.left,rectBack.top + offsetBack,rectBack.right,rectBack.bottom + offsetBack);
            garyBackImageView.layout(rectGrayBack.left,rectGrayBack.top + offsetBack,rectGrayBack.right,rectGrayBack.bottom + offsetBack);
            rebound = true;
            return super.dispatchTouchEvent(ev);
        }

        if (ev.getAction() == ev.ACTION_UP){
            if(!rebound ){
                return super.dispatchTouchEvent(ev);
            }
            reboundDirection = childView.getTop() -rect.top;
            TranslateAnimation translateAnimation = new TranslateAnimation(0,0,childView.getTop() ,rect.top);
            //Log.d("TranslateAnimation", "dispatchTouchEvent: " + childView.getTop() + "||||| "+ rect.top);
            TranslateAnimation translateAnimation1 = new TranslateAnimation(0,0,backImageView.getTop() ,rectBack.top);
            //Log.d("TranslateAnimation", "dispatchTouchEvent: " + backImageView.getTop()  + "||||| "+ rectBack.top);
            TranslateAnimation translateAnimation2 = new TranslateAnimation(0,0,garyBackImageView.getTop() ,rectGrayBack.top);
            //Log.d("TranslateAnimation", "dispatchTouchEvent: " + garyBackImageView.getTop() + "||||| "+ rectGrayBack.top);
            translateAnimation.setDuration(300);
            translateAnimation1.setDuration(300);
            translateAnimation2.setDuration(300);

            childView.startAnimation(translateAnimation);
            childView.layout(rect.left,rect.top,rect.right,rect.bottom);
            backImageView.startAnimation(translateAnimation1);
            backImageView.layout(rectBack.left,rectBack.top,rectBack.right,rectBack.bottom);
            garyBackImageView.startAnimation(translateAnimation2);
            garyBackImageView.layout(rectGrayBack.left,rectGrayBack.top,rectGrayBack.right,rectGrayBack.bottom);

            rebound = false;
            return super.dispatchTouchEvent(ev);

        }


        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获取第一个子控件
        childView = getChildAt(0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(childView != null){
            rect.set(childView.getLeft(),childView.getTop(),childView.getRight(),childView.getBottom());
        }
        if(backImageView != null){
            rectBack.set(backImageView.getLeft(),backImageView.getTop(),backImageView.getRight(),backImageView.getBottom());
        }
        if(garyBackImageView != null){
            rectGrayBack.set(garyBackImageView.getLeft(),garyBackImageView.getTop(),garyBackImageView.getRight(),garyBackImageView.getBottom());
        }
    }



    /*@Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if(scrollViewListener != null){
            scrollViewListener.onSrcollChanged(this,l,t,oldl,oldt);
        }

    }

    private ScrollViewListner scrollViewListener;
    public void setOnScrollViewListener(ScrollViewListner scrollViewListener){
        this.scrollViewListener = scrollViewListener;
    }


    public interface ScrollViewListner{
        void onSrcollChanged(MineInfomationScrollView scrollView,int x,int y,int oldx,int oldy);

    }*/
}
