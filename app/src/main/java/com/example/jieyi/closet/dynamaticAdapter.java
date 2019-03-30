package com.example.jieyi.closet;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class dynamaticAdapter extends RecyclerView.Adapter<dynamaticAdapter.ViewHolder> {

    private Context mContent;
    private List<dynamaticItem> dynamaticItemList;

    public dynamaticAdapter(List<dynamaticItem> dynamaticItemList){
        this.dynamaticItemList = dynamaticItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //若为空则获取context
        if(mContent == null){
            mContent = viewGroup.getContext();
        }
        //加载dynamic_item布局
        View view = LayoutInflater.from(mContent).inflate(R.layout.dynamic_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        dynamaticItem dynamaticItem = dynamaticItemList.get(i);
        viewHolder.headText.setText(dynamaticItem.getHeadName());
        viewHolder.ContentText.setText(dynamaticItem.getContentTest());
        //1 老方法，直接传入reId进行传值操作
        /*viewHolder.ContentImage.setImageResource(dynamaticItem.getHeadImageId());
        viewHolder.headImage.setImageResource(dynamaticItem.getHeadImageId());*/

        //2 新方法：使用Gilde库，可以加载本地图片/网络图片/GIF图片/本地视频
        //Glide.with() 传入context/Activity/Fragment
        //laod加载图片，可以传入URL地址，资源id，本地路径
        Glide.with(mContent).load(dynamaticItem.getHeadImageId()).into(viewHolder.headImage);
        Glide.with(mContent).load(dynamaticItem.getContentImage()).into(viewHolder.ContentImage);
    }

    @Override
    public int getItemCount() {
        return dynamaticItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView ContentImage;
        ImageView headImage;
        TextView ContentText;
        TextView headText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=(CardView) itemView;
            ContentImage = itemView.findViewById(R.id.dynamic_picture);
            headImage = itemView.findViewById(R.id.head_picture);
            ContentText =(TextView) itemView.findViewById(R.id.dynamic_text);
            headText = itemView.findViewById(R.id.head_name);
        }
    }


}
