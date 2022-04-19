package cn.edu.gxmzu.ai.mickhu.service;

import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.entity.test.GetDefault;
import cn.edu.gxmzu.ai.mickhu.service.impl.NodeServiceImpl;
import cn.edu.gxmzu.ai.mickhu.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
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
public class UserServiceTest {

    @Autowired
    NodeServiceImpl nodeService;

    @Autowired
    UserServiceImpl userService;

    @Test
    public void createNodeOfAdmin(){
        String password = "adminpw";
        String caHost = "localhost";
        String caPort = "7054";
        String accessHost = "localhost";
        nodeService.createNodeOfAdmin(password, caHost, caPort, accessHost);
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

    @Test
    public void createUser(){
        CertUser certUser = new CertUser();
        try {
            certUser = GetDefault.getCertUserDefault("mickhu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String userid = "111";
        log.println(userService.createUser(certUser, userid));
    }

    @Test
    public void getUser(){
        String certName = "mickhu";
        String userId = "test";
        log.println(userService.getUser(certName, userId));
    }

    @Test
    public void uploadData(){
        String seedKey = "my seedKey";
        String certName = "mickhu";
        String userId = "test";
        String data = "this is my data";
        CertUser produce = null;
        CertUser process = null;
        try {
            produce = GetDefault.getCertUserDefault("mickhu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            process = GetDefault.getCertUserDefault("mickhu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.println(userService.uploadData(seedKey, certName, userId, data, produce, process));
    }

    @Test
    public void queryData(){
        String certName = "mickhu";
        String userId = "test";
        String func = "searchByKeys";
        String keys = "[medical,data]";
        String tags = "[person]";
        String date = "2022-04-08 10:12:30";
        log.println(userService.queryData(certName, userId, func, keys, tags, date));
    }

    @Test
    public void queryCipherByDataBase(){
        String dataID = "8f4bebcaf5674e1f829fe2dbff347329";
        log.println(userService.queryCipherByDataBase(dataID));
    }

}
