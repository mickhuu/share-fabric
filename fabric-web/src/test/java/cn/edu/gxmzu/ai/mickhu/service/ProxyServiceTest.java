package cn.edu.gxmzu.ai.mickhu.service;

import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.entity.ProxyUser;
import cn.edu.gxmzu.ai.mickhu.entity.test.GetDefault;
import cn.edu.gxmzu.ai.mickhu.service.impl.NodeServiceImpl;
import cn.edu.gxmzu.ai.mickhu.service.impl.ProxyServiceImpl;
import cn.edu.gxmzu.ai.mickhu.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-11 13:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyServiceTest {

    @Autowired
    ProxyServiceImpl proxyService;

    @Test
    public void queryCipherByDataBase(){
        CertUser senderCertUser = null, receiverCertUser = null;
        try {
            senderCertUser = GetDefault.getCertUserDefault("mickhu");
            receiverCertUser = GetDefault.getCertUserDefault("mickhu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String senderHost = "192.168.6.1";
        String senderPort = "9898";
        String receiverHost = "192.168.25.1";
        String receiverPort = "81";
        String shuntParameter = "my shuntParameter";
        log.println(proxyService.proxyShareRequest(new ProxyUser(senderHost, senderPort, senderCertUser), new ProxyUser(receiverHost, receiverPort, receiverCertUser), shuntParameter));
    }

}
