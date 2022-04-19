package cn.edu.gxmzu.ai.mickhu.controller;

import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.entity.Data;
import cn.edu.gxmzu.ai.mickhu.entity.DataURL;
import cn.edu.gxmzu.ai.mickhu.entity.test.DataUser;
import cn.edu.gxmzu.ai.mickhu.entity.test.GetDefault;
import cn.edu.gxmzu.ai.mickhu.entity.test.Record;
import cn.edu.gxmzu.ai.mickhu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @describe 操作节点控制器
 * @author mickhu
 * @date 2022-04-07 21:38
 */
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/createUser")
    public String createUser(CertUser certUser,
                             @RequestParam(defaultValue = "111")String id){
        try {
            certUser = GetDefault.getCertUserDefault("mickhu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userService.createUser(certUser, id);
    }

    @RequestMapping("/getUser")
    public Map<Integer, Record> getUser(@RequestParam(defaultValue = "admin")String certName,
                                        @RequestParam(defaultValue = "111")String userId){
        return userService.getUser(certName, userId);
    }

    // test upload to add index data record method
    @RequestMapping("/saveData")
    public String savaData(@RequestParam(defaultValue = "my seedKey")String seedKey,
                           CertUser produce, CertUser process){
        String data = new String(new byte[512 * 1 * 1024]);
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
        return userService.saveData(seedKey, data, produce, process, String.valueOf(System.currentTimeMillis())).toString();
    }

    @RequestMapping("/uploadData")
    public String uploadData(@RequestParam(defaultValue = "my seedKey")String seedKey,
                             @RequestParam(defaultValue = "admin")String certName,
                             @RequestParam(defaultValue = "111")String userId,
                             @RequestParam(defaultValue = "this is my data...")String data,
                             CertUser produce,
                             CertUser process){
        data = new String(new byte[512 * 1 * 1024]);
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
        return userService.uploadData(seedKey, certName, userId, data, produce, process);
    }

    @RequestMapping("/queryData")
    public Map<Integer, Record> queryData(@RequestParam(defaultValue = "admin")String certName,
                            @RequestParam(defaultValue = "111")String userId,
                            @RequestParam(defaultValue = "searchByKeys")String func,
                            @RequestParam(defaultValue = "[medical,data]")String keys,
                            @RequestParam(defaultValue = "[person]")String tags,
                            @RequestParam(defaultValue = "2022-04-08 10:12:30")String date){
        return userService.queryData(certName, userId, func, keys, tags, date);
    }

    @RequestMapping("/queryCipherByDataBase")
    public DataURL queryCipherByDataBase(@RequestParam(defaultValue = "8f4bebcaf5674e1f829fe2dbff347329")String dataID){
        return userService.queryCipherByDataBase(dataID);
    }


}
