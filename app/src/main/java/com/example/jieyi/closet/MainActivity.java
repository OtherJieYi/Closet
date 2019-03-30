package com.example.jieyi.closet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.jieyi.closet.BottomClass.BottomClass;

public class MainActivity extends AppCompatActivity {

    private Fragment fragmentGroup;
    private BottomClass bottomClass;
    private  Fragment  currentFragmen = new Fragment();
    private HomeActivity homeActivity;
    private FindActivity findActivity;
    private MineActivity mineActivity;
    private StoreActivity storeActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隐藏系统默认的标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        //frament 事务开启
        homeActivity = HomeActivity.getInstance();
        findActivity = new FindActivity();
        mineActivity = new MineActivity();
        storeActivity = new StoreActivity();

        switchFrament(homeActivity).commit();
        //fragmentGroup = findViewById(R.id.mian_fragment_group);

        RadioGroup radioGroup = findViewById(R.id.bottom_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.bottom_home:
                        switchFrament(homeActivity).commit();
                        break;
                    case R.id.bottom_find:
                        switchFrament(findActivity).commit();
                        break;
                    case R.id.bottom_mine:
                        switchFrament(mineActivity).commit();
                        break;
                    case R.id.bottom_store:
                        switchFrament(storeActivity).commit();
                        break;
                }
            }
        });
    }

    private FragmentTransaction switchFrament(Fragment targetFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(!targetFragment.isAdded()){
            if(targetFragment !=  null){
                transaction.hide(currentFragmen);
            }
            transaction.add(R.id.main_fragment_group,targetFragment,targetFragment.getClass().getName()).show(targetFragment);
        }else{
            transaction.hide(currentFragmen).show(targetFragment);
        }
        currentFragmen = targetFragment;
        return transaction;
    }


}
