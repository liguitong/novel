package com.lgt.learn.pool;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


class MyBeanTest {

    static String[] keys = {"2019021500","2019021600","2019021700"};
    Random r = new Random();
    String getKey(){
        return keys[r.nextInt(keys.length)];
    }
    @Test
    public void getBean(){
        String key = getKey();
        assertTrue( key!=null && key.length()==10,"The length of key is 10 ");
        try {
            for (int i=0;i<20;i++){
                TimeUnit.SECONDS.sleep(1);
                KeyPoolFactory.getBean(key);
            }
            System.out.println("前方是否阻塞我了……");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAndReturnBean(){
        String key=getKey();
        assertTrue(key!=null && key.length() ==10,"The length of key is 10 ");
        try {
            for(int i=0;i<20;i++){
                TimeUnit.SECONDS.sleep(1);
                MyBean my = KeyPoolFactory.getBean(key);
                my.deadBean();
                KeyPoolFactory.returnBean(key,my);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getBeans(){
        String key1 = getKey();
        String key2 = key1;
        while(key1.equals(key2)){
            key2 = getKey();
        }
        assertTrue(key1!=null && key1.length() ==10,"The length of key is 10 ");
        assertTrue(key2!=null && key2.length() ==10,"The length of key is 10 ");
        try{
            for (int i = 0; i < 20; i ++) {
                TimeUnit.SECONDS.sleep(1);//睡一秒
                if (i % 2 == 0){
                    KeyPoolFactory.getBean(key1);
                } else {
                    KeyPoolFactory.getBean(key2);
                }
            }
            System.out.println("前方是否阻塞到我了...");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    volatile boolean dead = false;
    @Test
    public void getAndReturnByThread(){
        final ConcurrentHashMap<String, LinkedBlockingDeque<MyBean>> beans = new ConcurrentHashMap<>();
        /**
         * 借对象,每秒借一个
         */
        new Thread("borrow"){
            public void run(){
                while(true){
                    String key = getKey();
                    LinkedBlockingDeque<MyBean> link = beans.get(key);
                    if(link == null){
                        link = new LinkedBlockingDeque<>(10);
                        beans.put(key,link);
                    }
                    try{
                        MyBean bean = KeyPoolFactory.getBean(key);
                        link.push(bean);
                        System.out.println(Thread.currentThread().getName()+" 操作:key=" + key + " ,bean=" + bean);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
        /**
         * 还对象,每3秒还一个
         */
        new Thread("return") {
            public void run () {
                while (true) {
                    String key = getKey();
                    LinkedBlockingDeque<MyBean> link = beans.get(key);
                    if (link == null || link.size() == 0) continue;
                    /** 弹出元素 */
                    MyBean bean = link.pop();
                    if (dead) {
                        bean.deadBean();
                        dead = false;
                    }
                    System.out.println(Thread.currentThread().getName() + "操作:key="+key + ",bean="+bean);
                    KeyPoolFactory.returnBean(key, bean);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        /**
         * 每5秒死亡一对象
         */
        new Thread("dead") {
            public void run () {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        dead = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        try {
            TimeUnit.MINUTES.sleep(5);//5分钟后结束程序
            Runtime.getRuntime().exit(0);
        } catch (Exception e) {

        }
    }
}