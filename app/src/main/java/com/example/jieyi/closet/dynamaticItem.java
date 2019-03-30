package com.example.jieyi.closet;

public class dynamaticItem {
    private String headName;
    private String ContentTest;
    private int headImageId;
    private int ContentImage;

    public dynamaticItem(String headName,String ContentTest,int headImageId,int ContentImage){
        this.headName = headName;
        this.ContentTest = ContentTest;
        this.headImageId = headImageId;
        this.ContentImage = ContentImage;
    }

    public int getContentImage() {
        return ContentImage;
    }

    public int getHeadImageId() {
        return headImageId;
    }

    public String getHeadName(){
        return headName;
    }

    public String getContentTest() {
        return ContentTest;
    }
}
