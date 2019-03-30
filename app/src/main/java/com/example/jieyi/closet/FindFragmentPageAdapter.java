package com.example.jieyi.closet;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import static com.example.jieyi.closet.FindActivity.PAGE_ONE;

public class FindFragmentPageAdapter extends FragmentPagerAdapter {
    private FindFragment1 findFragment1;
    private FindFragment2 findFragment2;

    public FindFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        findFragment1 = new FindFragment1();
        findFragment2 = new FindFragment2();

    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case FindActivity.PAGE_ONE:
                fragment = findFragment1;
                break;
            case FindActivity.PAGE_TWO:
                fragment = findFragment2;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


    //移除一个给定位置的页面。适配器有责任从容器中删除这个视图。这是为了确保 在finishUpdate(viewGroup)返回时视图能够被移除
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }


    //（1）将给定位置的view添加到 ViewGroup (容器)中，创建并显示出来
    //（2）返回一个代表新增页面的object（key），通常直接返回view本身，key与每个view必须一一对应
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d("closet", "instantiateItem: " + position + "===="+ container );
        return super.instantiateItem(container, position);
    }

    //判断 instantiateItem 函数返回的key与一个页面视图是否是对应关系的，
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return super.isViewFromObject(view, object);
    }
}
