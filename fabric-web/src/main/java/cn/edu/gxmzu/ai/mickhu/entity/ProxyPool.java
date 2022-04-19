package cn.edu.gxmzu.ai.mickhu.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 16:42
 */

public class ProxyPool {
    private Map<Integer, Proxy> proxyPool;

    public ProxyPool() {
        init();
    }

    public void init() {
        proxyPool = new HashMap<>();
    }

    public Map<Integer, Proxy> addProxy(Proxy proxy){
        Map<Integer, Proxy> proxyPool = getProxyPool();
        proxyPool.put(proxyPool.size(), proxy);
        return getProxyPool();
    }

    public Map<Integer, Proxy> getProxyPool() {
        return proxyPool;
    }

    /**
     * 分流选取代理节点
     * @param shuntParameter 分流参数
     * @return 返回选中的代理节点
     */
    public Proxy shuntProxy(String shuntParameter){
        //1. get proxyPool siez
        int size = getProxyPool().size();
        //2. get index by shuntParameter to choose
        int index = Integer.decode(shuntParameter) % size;
        //3. choose proxy at proxyPool
        Proxy proxy = getProxyPool().get(index);
        //4. return proxy
        return proxy;
    }

}
