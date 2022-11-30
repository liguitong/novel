package com.lgt.learn.pool;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class MyBeanPooledFactory extends BaseKeyedPooledObjectFactory<String,MyBean> {
    @Override
    public MyBean create(String s) throws Exception {
        MyBean bean = new MyBean();
        bean.start();
        System.out.println(bean);
        return bean;
    }

    @Override
    public PooledObject<MyBean> wrap(MyBean myBean) {
        return new DefaultPooledObject<>(myBean);
    }

    @Override
    public boolean validateObject(String key, PooledObject<MyBean> p) {
        MyBean bean = p.getObject();
        if(!bean.isLive()){
            System.out.println(bean.getName() + " is already dead!");
            return false;
        }
        return true;
    }

    @Override
    public void destroyObject(String key, PooledObject<MyBean> p) throws Exception {
        p.getObject().beKilled();
    }
}
