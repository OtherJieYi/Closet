package com.example.jieyi.closet;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jieyi.closet.MineInfomation.MyInfomation;

public class MineActivity extends Fragment {

    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_mine,container,false);
        linearLayout = view.findViewById(R.id.mine_head_infomation);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyInfomation.class);
                getContext().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.out_in_right);
            }
        });
        return view;
    }


}
