package com.example.jieyi.closet.BottomClass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.jieyi.closet.HomeActivity;
import com.example.jieyi.closet.MainActivity;
import com.example.jieyi.closet.R;

public class BottomClass extends RelativeLayout {

    public RadioGroup radioGroup;

    public BottomClass(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bottom,this);

        //控件绑定
        radioGroup = findViewById(R.id.bottom_radiogroup);
        RadioButton homeRadio = findViewById(R.id.bottom_home);
        RadioButton findRadio = findViewById(R.id.bottom_find);
        RadioButton storeRadio = findViewById(R.id.bottom_store);
        RadioButton mineRadio = findViewById(R.id.bottom_mine);

        //修该图片
        RadioButton[] radioArraylist = new RadioButton[4];
        radioArraylist[0] = homeRadio;
        radioArraylist[1] = findRadio;
        radioArraylist[2] = storeRadio;
        radioArraylist[3] = mineRadio;

        for(RadioButton radioButton : radioArraylist){
            //获取drawables
            Drawable[] drawables = radioButton.getCompoundDrawables();
            //定义一个Rect边界
            Rect rect = new Rect(0,0,drawables[1].getMinimumWidth()*3/11,drawables[1].getMinimumHeight()*3/11);
            drawables[1].setBounds(rect);
            radioButton.setCompoundDrawables(null,drawables[1],null,null);
        }
        radioGroup.check(R.id.bottom_home);
        //添加点击事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.bottom_home:

                        break;
                    case R.id.bottom_find:
                        break;
                    case R.id.bottom_store:
                        break;
                    case R.id.bottom_mine:
                        break;
                }
            }
        });


    }

    /*private FragmentTransaction switchFragment(Fragment targetMent){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
    }*/


}
