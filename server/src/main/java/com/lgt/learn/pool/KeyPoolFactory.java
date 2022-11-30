package com.lgt.learn.pool;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

public class KeyPoolFactory {
    private static GenericKeyedObjectPool<String, MyBean> pool;
    private static final GenericKeyedObjectPoolConfig config;
    private static final int TOTAL_PERKEY=10;
    private static final int IDLE_PERKEY=3;
    static{
        config = new GenericKeyedObjectPoolConfig();
        config.setMaxTotalPerKey(TOTAL_PERKEY);
        config.setMaxIdlePerKey(IDLE_PERKEY);

        config.setJmxEnabled(true);
        config.setJmxNamePrefix("myPoolProtocol");

        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

    }

    public static MyBean getBean(String key) throws Exception {
        if(pool == null){
            init();
        }
        return pool.borrowObject(key);
    }

    public static void returnBean(String key ,MyBean bean){
        if(pool == null){
            init();
        }
        pool.returnObject(key,bean);
    }

    public synchronized static void close(){
        if(pool!=null && !pool.isClosed()){
            pool.close();
            pool = null;
        }
    }
    private static void init() {
        if(pool ==null){
            pool = new GenericKeyedObjectPool<String, MyBean>(new MyBeanPooledFactory(),config);
        }
    }
}
