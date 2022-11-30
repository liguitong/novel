package com.lgt.beans;

import lombok.Data;

@Data
public class Chapter {
    //TODO 实现ID主键自增，虽然与mongodb的定位有偏差，但就想实现
//    private int id;
    private int chapterId = 0;
    private String chapterName = "";
    private int novelId;
    private String content;
}
