package com.example.jieyi.closet;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class HomeActivity extends Fragment implements ViewPager.OnPageChangeListener {

    private static HomeActivity homeActivity = null;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    private HomeFragmentPageAdapter homeFragmentPageAdapter;
    private ViewPager viewPager;
    private ImageView follewClick;
    private ImageView bookClick;
    private  com.example.jieyi.closet.myView.HomeScroolView homeScroolView;

    public static synchronized HomeActivity getInstance(){
        if ( homeActivity == null){
            return  new HomeActivity();
        }
        return  homeActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home,container,false);

        //布局管理
        follewClick = view.findViewById(R.id.home_follow);
        bookClick = view.findViewById(R.id.home_book);
        homeScroolView = view.findViewById(R.id.home_scrool_view);

        //添加内部类实现onClick 接口,添加点击事件
        HomeOnClickListener homeOnClickListener = new HomeOnClickListener();
        follewClick.setOnClickListener(homeOnClickListener);
        bookClick.setOnClickListener(homeOnClickListener);

        //viewpage切换：（1）创建碎片适配器
        homeFragmentPageAdapter = new HomeFragmentPageAdapter(getFragmentManager());//getSupportFragmentManager
        //（2）初始化 viewpage
        initViewPage(view);

        //return super.onCreateView(inflater, container, savedInstanceState);
        return view;

    }


    public void initViewPage(View view){
        viewPager = view.findViewById(R.id.home_viewpage);
        viewPager.setAdapter(homeFragmentPageAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

        /**
         * 原博文中注释（state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕）有误
         * 特此更正为如下：
         *
         *SCROLL_STATE_IDLE : 值为0，表示当前页已经选定。
         *SCROLL_STATE_DRAGGING: 值为1，表示当前页面正在拖动。
         *SCROLL_STATE_SETTING: 值为2，表示当前页面正在拖动中，还没有到选定状态。
         */
        if(i == 2){
            int currentItemPosition = viewPager.getCurrentItem();
            switch (currentItemPosition){
                case PAGE_ONE:
                    translateAnimation(PAGE_ONE);
                    //homeScroolView
                    break;
                case PAGE_TWO:
                    translateAnimation(PAGE_TWO);
                    break;
            }
        }
    }

    private void translateAnimation(int Type){
        int startX = 0;
        int stopX =0;
        if(Type == PAGE_TWO){
            stopX = 260;
        }
        if(Type == PAGE_ONE){
            startX = 260;
            stopX = 0;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(startX, stopX, 0, 0);
        translateAnimation.setDuration(600);
        translateAnimation.setFillAfter(true);
        homeScroolView.setAnimation(translateAnimation);
        homeScroolView.startAnimation(translateAnimation);
    }

    //隐藏更新内容
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }else{

        }
    }

    public class HomeOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.home_follow:
                    //切换到关注
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.home_book:
                    viewPager.setCurrentItem(1);
                    break;
            }
        }
    }



}
