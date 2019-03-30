package com.example.jieyi.closet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment2 extends Fragment {
    public HomeFragment2(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_home_fragment_2,container,false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("closet","HomeFragment 2 onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("closet","HomeFragment 2 onDestroy");
    }
}
