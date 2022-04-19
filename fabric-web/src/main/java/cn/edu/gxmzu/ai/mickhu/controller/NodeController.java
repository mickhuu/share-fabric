package cn.edu.gxmzu.ai.mickhu.controller;

import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.service.impl.NodeServiceImpl;
import cn.edu.gxmzu.ai.mickhu.entity.test.GetDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @describe 创建节点控制器
 * @author mickhu
 * @date 2022-04-07 17:03
 */
@RestController
public class NodeController {

    @Autowired
    NodeServiceImpl nodeService;

    @RequestMapping("/createNodeOfAdmin")
    public String createNodeOfAdmin(@RequestParam(defaultValue = "adminpw")String password,
                                    @RequestParam(defaultValue = "192.168.6.128")String caHost,
                                    @RequestParam(defaultValue = "7054")String caPort,
                                    @RequestParam(defaultValue = "192.168.6.1")String accessHost){
        return nodeService.createNodeOfAdmin(password, caHost, caPort, accessHost);
    }

    @RequestMapping("/createNodeOfUser")
    public String createNodeOfUser(CertUser certUser,
                                   @RequestParam(defaultValue = "192.168.6.128")String caHost,
                                   @RequestParam(defaultValue = "7054")String caPort){
        try {
            certUser = GetDefault.getCertUserDefault("mickhu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodeService.createNodeOfUser(certUser, caHost, caPort);
    }

}
