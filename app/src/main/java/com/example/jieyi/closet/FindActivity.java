package com.example.jieyi.closet;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FindActivity extends Fragment implements ViewPager.OnPageChangeListener{
    public static final  int PAGE_ONE = 0;
    public static final  int PAGE_TWO = 1;
    private FindFragmentPageAdapter findFragmentPageAdapter;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_find,container,false);

        //view page 切换frament
        //(1) 创建适配器
        findFragmentPageAdapter = new FindFragmentPageAdapter(getFragmentManager());
        viewPager = view.findViewById(R.id.find_viewpage);
        viewPager.setAdapter(findFragmentPageAdapter);
        viewPager.setCurrentItem(PAGE_ONE);
        viewPager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
