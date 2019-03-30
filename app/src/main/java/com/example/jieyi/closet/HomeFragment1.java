package com.example.jieyi.closet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment1 extends Fragment {

    private  dynamaticAdapter dynamaticAdapter;
    private List<dynamaticItem> dynamaticItemList = new ArrayList<>();
    private dynamaticItem[] dynamaticItemsString ={
            new dynamaticItem("ascuter","今天也很快乐",R.drawable.dynamic_item_1_head,0),
            new dynamaticItem("舒hi","今天不快乐",R.drawable.dynamic_item_2_head,R.drawable.dynamic_item_2),
            new dynamaticItem("ususw","今天不快乐",R.drawable.dynamic_item_3_head,R.drawable.dynamic_item_3),
            new dynamaticItem("说大话","啊咧啊咧，我喜欢你啊",R.drawable.dynamic_item_4_head,0)
    };

    public HomeFragment1(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_home_fragment_1,container,false);
        initDynamic();
        RecyclerView recyclerview = view.findViewById(R.id.dynamicView);//view.findViewById(R.id.dynamicView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        recyclerview.setLayoutManager(gridLayoutManager);
        dynamaticAdapter = new dynamaticAdapter(dynamaticItemList);
        //Log.d("closet1",dynamaticItemList.get(1).getContentTest() + "" + dynamaticItemList.get(1).getHeadName());
        recyclerview.setAdapter(dynamaticAdapter);
       // dynamaticAdapter.notifyDataSetChanged();
        //initRecycler(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("closet","HomeFragment 1 onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("closet","HomeFragment 1 onDestroy");
    }

    private void initDynamic(){
        dynamaticItemList.clear();
        //随机填写
        /*for(int i = 0; i < 10 ;i ++){
            Random random = new Random();
            int index = random.nextInt(dynamaticItemsString.length);
            dynamaticItemList.add(dynamaticItemsString[index]);
        }*/
        //正常初始化
        for(dynamaticItem dynamaticItem : dynamaticItemsString){
            dynamaticItemList.add(dynamaticItem);
        }
    }

    private void initRecycler(View view){
        RecyclerView recyclerview = view.findViewById(R.id.dynamicView);;//view.findViewById(R.id.dynamicView);
        Log.d("cloest",recyclerview.toString());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        recyclerview.setLayoutManager(gridLayoutManager);
        dynamaticAdapter = new dynamaticAdapter(dynamaticItemList);
        recyclerview.setAdapter(dynamaticAdapter);
    }

}
