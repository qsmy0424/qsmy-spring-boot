package com.qsmy.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;

import java.util.Properties;

/**
 * @author qsmy
 */
public class Pub {
    public static void main(String[] args) throws NacosException, InterruptedException {
        //发布的服务名
        String serviceName = "helloworld.services";
        //构造一个nacos实例，入参是nacos server的ip和服务端口
        // NamingService naming = NamingFactory.createNamingService("qsmy.ubuntu:18848");
        // NamingService naming = NamingFactory.createNamingService("qsmy.ubuntu:18848");
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, "qsmy.ubuntu:18848");
        properties.setProperty(PropertyKeyConst.NAMESPACE, "dd63409a-0f0f-45b0-8104-1ec5c1560606");

        NamingService naming = NacosFactory.createNamingService(properties);
        //发布一个服务，该服务对外提供的ip为127.0.0.1，端口为8888
        naming.registerInstance(serviceName, "127.0.0.1", 8888);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
