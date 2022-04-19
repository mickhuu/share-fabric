package cn.edu.gxmzu.ai.mickhu.service.impl;

import cn.edu.gxmzu.ai.mickhu.crypto.RSA;
import cn.edu.gxmzu.ai.mickhu.entity.Proxy;
import cn.edu.gxmzu.ai.mickhu.entity.ProxyPool;
import cn.edu.gxmzu.ai.mickhu.entity.ProxyUser;
import cn.edu.gxmzu.ai.mickhu.grpc.GrpcClient;
import cn.edu.gxmzu.ai.mickhu.service.ProxyService;
import cn.edu.gxmzu.ai.mickhu.utils.CodeConvertUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.math.BigInteger;


/**
 * @describe 代理服务实现类
 * @author mickhu
 * @date 2022-04-09 17:21
 */
@Service
public class ProxyServiceImpl implements ProxyService {

    private static ProxyPool proxyPool = new ProxyPool();

    private static GrpcClient gRpcClient = new GrpcClient();

    @Override
    public void addProxyToPool(Proxy proxy) {
        proxyPool.addProxy(proxy);
    }

    @Override
    public Proxy chooseProxy(String shuntParameter) {
        return proxyPool.shuntProxy(shuntParameter);
    }

    @Override
    public void proxyForward(ProxyUser proxyUser, String message) {
        //启动grpc clent进行web端代理转发信息
        gRpcClient.start(proxyUser.getHost(), proxyUser.getPort(), message);
    }

    @Override
//    public String proxyShareRequest(String senderHost, String senderPort, String receiverHost, String receiverPort, String shuntParameter) {
    public String proxyShareRequest(ProxyUser sender, ProxyUser receiver, String shuntParameter) {
        //1. 将分流参数转为十六进制字符串
        String shuntParameterHex = CodeConvertUtils.convertStringToHex(shuntParameter);
        //2. 根据分流参数选择代理节点
        Proxy proxy = chooseProxy(shuntParameterHex);
        //3. 通过代理节点分流选取代理全局共享参数
        BigInteger[] parameters = proxy.shuntProxy(shuntParameterHex);
//        return parameters[0].multiply(parameters[1]);
//        //4. 通过代理节点分发公私钥对给共享双方
        RSA senderRSA = new RSA(parameters[0], parameters[1]);
        RSA receiverRSA = new RSA(parameters[0], parameters[1]);
        sender.setPk(new BigInteger[]{senderRSA.e, senderRSA.n});
        sender.setSk(new BigInteger[]{senderRSA.d, senderRSA.n});
        sender.setReceiverPK(new BigInteger[]{receiverRSA.e, receiverRSA.n});
        receiver.setPk(new BigInteger[]{receiverRSA.e, receiverRSA.n});
        receiver.setSk(new BigInteger[]{receiverRSA.d, receiverRSA.n});
        receiver.setReceiverPK(new BigInteger[]{senderRSA.e, senderRSA.n});
        //4.1. 发送给sander公私钥对
//        String message2Sender = "n: " + sender.n + ", pk: " + sender.e + ", " + "sk: " + sender.d;
        proxyForward(sender, JSON.toJSONString(sender));
        //4.2. 发送给receiver公私钥对
//        String message2Receiver = "n: " + sender.n + "pk: " + receiver.e + ", " + "sk: " + receiver.d;
        proxyForward(receiver, JSON.toJSONString(receiver));
        return null;
    }

//    @Override
    public String proxyShareResponse(String host, String port, String shuntParameter) {
        
        return null;
    }

}
