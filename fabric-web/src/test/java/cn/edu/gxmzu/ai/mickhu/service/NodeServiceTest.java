package cn.edu.gxmzu.ai.mickhu.service;

import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.entity.test.GetDefault;
import cn.edu.gxmzu.ai.mickhu.service.impl.NodeServiceImpl;
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
public class NodeServiceTest {

    @Autowired
    NodeServiceImpl nodeService;

    @Test
    public void createNodeOfAdmin(){
        String password = "adminpw";
        String caHost = "localhost";
        String caPort = "7054";
        String accessHost = "localhost";
        log.println(nodeService.createNodeOfAdmin(password, caHost, caPort, accessHost));
    }

    @Test
    public void createNodeOfUser(){
        CertUser certUser = new CertUser();
        try {
            certUser = GetDefault.getCertUserDefault("mickhu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String caHost = "localhost";
        String caPort = "7054";
        log.println(nodeService.createNodeOfUser(certUser, caHost, caPort));
    }
}
