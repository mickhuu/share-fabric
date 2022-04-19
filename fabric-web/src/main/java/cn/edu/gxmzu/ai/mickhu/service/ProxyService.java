package cn.edu.gxmzu.ai.mickhu.service;

import cn.edu.gxmzu.ai.mickhu.entity.Proxy;
import cn.edu.gxmzu.ai.mickhu.entity.ProxyUser;

import java.math.BigInteger;
import java.util.Map;

/**
 * @describe 代理服务接口
 * @author mickhu
 * @date 2022-04-09 13:59
 */
public interface ProxyService {

    /**
     * 添加代理节点到代理池
     * @param proxy 添加代理节点
     * @return 返回新的代理池
     */
    void addProxyToPool(Proxy proxy);


    /**
     * 通过分流在代理池选择代理节点及代理参数
     * @param shuntParameter 分流参数
     * @return 返回代理节点
     */
    Proxy chooseProxy(String shuntParameter);

    /**
     * 发起web端代理转发信息
     * @param proxyUser 发起者代理用户实体
     * @param message 传输消息
     */
    void proxyForward(ProxyUser proxyUser, String message);

    /**
     * 请求者发起web端代理共享请求给共享者，开启代理共享通道以及双方得到各自公私钥对
     * @param sender 请求者代理用户实体
     * @param receiver 共享者代理用户实体
     * @param shuntParameter 分流参数
     * @return
     */
    String proxyShareRequest(ProxyUser sender, ProxyUser receiver, String shuntParameter);

}
