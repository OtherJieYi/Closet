package com.example.jieyi.closet.MineInfomation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.jieyi.closet.R;
import com.example.jieyi.closet.myView.MineInfomationScrollView;

public class MyInfomation extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView backImageView;
    private ImageView backGrayImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infomation);

        //控件定义
        toolbar = findViewById(R.id.mine_infomation_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("返回");
        backImageView = findViewById(R.id.mine_infomation_backgroud);
        backGrayImageView = findViewById(R.id.mine_infomation_backgroud_gray);

        //scrollview滑动实现
        MineInfomationScrollView mineInfomationScrollView = findViewById(R.id.mine_infomation_srcollview);
        mineInfomationScrollView.setBackImageView(backImageView,backGrayImageView);
        /*mineInfomationScrollView.setOnScrollViewListener(new MineInfomationScrollView.ScrollViewListner() {
            @Override
            public void onSrcollChanged(MineInfomationScrollView scrollView, int x, int y, int oldx, int oldy) {
                Log.d("onSrcollChanged", "onSrcollChanged: " + y);
                imageView.setTranslationY(-y/2);
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:
            this.finish();
            return false;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
