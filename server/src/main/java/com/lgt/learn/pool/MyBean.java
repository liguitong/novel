package com.lgt.learn.pool;


import java.sql.Timestamp;

public class MyBean {
    public static final String[] names={"Little how","Jim Green", "Black Tom", "White Cat"
    , "Yellow Dog", "Color Wolf"};
    private long instanceTime = System.currentTimeMillis();
    private String name;
    private boolean live=true;

    public MyBean() {
        this.name = names[(int) (this.instanceTime % names.length)];

    }

    public MyBean(String name) {
        this.name = name;
    }

    public void beKilled(){
        System.out.print("我[" + this.name + "]居然被销毁了！！！");
        System.out.println("就活了" + (System.currentTimeMillis() - instanceTime)/1000 + "秒。");
    }
    public void deadBean(){
        this.live= false;
    }
    public void start () {
        System.out.println(this.name + "的生命开始了");
    }
    public String toString () {
        return "我[" + this.name + "]出生在:" + new Timestamp(this.instanceTime);
    }

    public long getInstanceTime() {
        return instanceTime;
    }

    public String getName() {
        return name;
    }

    public boolean isLive() {
        return live;
    }
}
