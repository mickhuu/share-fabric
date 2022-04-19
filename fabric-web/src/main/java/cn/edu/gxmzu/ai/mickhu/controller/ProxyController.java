package cn.edu.gxmzu.ai.mickhu.controller;

import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.entity.Proxy;
import cn.edu.gxmzu.ai.mickhu.entity.ProxyUser;
import cn.edu.gxmzu.ai.mickhu.entity.test.GetDefault;
import cn.edu.gxmzu.ai.mickhu.service.impl.ProxyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 21:26
 */
@RestController
public class ProxyController {
    @Autowired
    ProxyServiceImpl proxyService;

    @RequestMapping("/addProxyToPool")
    public String addProxyToPool(Proxy proxy){
        proxy = null;
        proxyService.addProxyToPool(proxy);
        return "success add proxy node to proxyPool";
    }

    @RequestMapping("/proxyForward")
    public String proxyForward(CertUser certUser,
                               @RequestParam(defaultValue = "192.168.25.1")String host,
                               @RequestParam(defaultValue = "18888")String port,
                               @RequestParam(defaultValue = "this is my message..")String message){
        try {
            certUser = GetDefault.getCertUserDefault("mickhu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        proxyService.proxyForward(new ProxyUser(host, port, certUser), message);
        return "success forward by proxy";
    }

    @RequestMapping("/proxyShareRequest")
    public String proxyShareRequest(CertUser senderCertUser, CertUser receiverCertUser,
                                    @RequestParam(defaultValue = "192.168.6.1")String senderHost, @RequestParam(defaultValue = "9898")String senderPort,
                                    @RequestParam(defaultValue = "192.168.25.1")String receiverHost, @RequestParam(defaultValue = "81")String receiverPort,
                                    @RequestParam(defaultValue = "my shutParameter")String shuntParameter){
        try {
            senderCertUser = GetDefault.getCertUserDefault("mickhu");
            receiverCertUser = GetDefault.getCertUserDefault("mickhu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        proxyService.proxyShareRequest(new ProxyUser(senderHost, senderPort, senderCertUser), new ProxyUser(receiverHost, receiverPort, receiverCertUser), shuntParameter);
        return "success distribute (pk,sk) to sender and  receiver by proxy";
    }

}
