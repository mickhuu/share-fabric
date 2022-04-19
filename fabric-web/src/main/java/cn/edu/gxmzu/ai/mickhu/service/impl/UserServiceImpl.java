package cn.edu.gxmzu.ai.mickhu.service.impl;

import cn.edu.gxmzu.ai.mickhu.annotation.Master;
import cn.edu.gxmzu.ai.mickhu.config.FabricConfig;
import cn.edu.gxmzu.ai.mickhu.crypto.EncryptAPI;
import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.entity.Data;
import cn.edu.gxmzu.ai.mickhu.entity.DataURL;
import cn.edu.gxmzu.ai.mickhu.entity.Institution;
import cn.edu.gxmzu.ai.mickhu.entity.test.DataUser;
import cn.edu.gxmzu.ai.mickhu.entity.test.Record;
import cn.edu.gxmzu.ai.mickhu.mapper.DataURlMapper;
import cn.edu.gxmzu.ai.mickhu.service.UserService;
import cn.edu.gxmzu.ai.mickhu.utils.*;
import com.alibaba.fastjson.JSON;
import org.hyperledger.fabric.gateway.*;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;


/**
 * @description 操作节点的服务实现类
 * @author mickhu
 * @date 2022-04-07 18:54
 */
@Service
public class UserServiceImpl implements UserService {

    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }

    @Autowired
    private DataURlMapper dataURlMapper;

    @Autowired
    private RedissonClient redissonClient;

    private static Contract contract;

    @Override
    public String createUser(CertUser certUser, String userid) {
        //查看钱包证书存在节点证书
        try {
            if (HFCAClientUtils.getWallet().get(certUser.getName()) == null) {
                return "An identity for the user \"" + certUser.getName() + "\" not exists in the wallet";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通过节点注册用户
        byte[] result = new byte[0];
        // create a gateway connection
        Gateway gateway = GateWayUtils.connectNetwork(certUser.getName());
        try {
            Contract contract = GateWayUtils.getContract(gateway, null, null);
            //注册账户
            result = contract.submitTransaction("registerAccount", userid);
            System.out.println("registerAccount:"+new String(result));
        }catch (ContractException | TimeoutException | InterruptedException e){
            System.out.println("chiancode execution error");
            e.printStackTrace();
        }finally {
            gateway.close();
        }
        return new String(result);
    }

    @Override
    public Map<Integer, Record> getUser(String certName, String userId) {
        Map<Integer, Record> dataMap = new HashMap<>();
        Gateway gateway = GateWayUtils.connectNetwork(certName);
        try {
            /**
             * 1. new GateWayAPI().getContract(certName, null, null);
             * 2. new GateWayAPI().getContract(certName, "mickhuu", "Mychaincode");
            **/
            Contract contract = GateWayUtils.getContract(gateway, "mickhuu", "Mychaincode");
            byte[] result = contract.evaluateTransaction("getAccount", userId);
            System.out.println("getAccount:"+new String(result));
            DataUser dataUser = JSON.parseObject(result, DataUser.class);
            System.out.println("dataUser:"+dataUser);
            dataMap = dataUser.getDataMap();
//            System.out.println("dataMap:"+dataMap.toString());
        }catch (ContractException e){
            System.out.println("chiancode execution error");
            e.printStackTrace();
        }finally {
            gateway.close();
        }
        return dataMap;
    }

    @Override
    public String uploadData(String seedKey, String certName, String userId, String data, CertUser produce, CertUser process) {
        String result = "success";
        Gateway gateway = GateWayUtils.connectNetwork(certName);
        String key = "lockKey";
        RLock lock = redissonClient.getLock(key);
        //加锁 操作很类似Java的ReentrantLock机制
        try {
//            lock.lock();
            //1. 记录时间戳
            String timeStamp = String.valueOf(System.currentTimeMillis());
            //2. 数据密文上传到服务数据库存储并返回存储区块中的实体对象
            Data d = saveData(seedKey, data, produce, process, timeStamp);
            //3. 使用fastjson序列化实体对象作为数据字符串
            data = JSON.toJSONString(d);
            //sleep 2s
//            Thread.currentThread().sleep(2000);
            //4. 存储记录到区块中
            Contract contract = GateWayUtils.getContract(gateway, null, null);
            contract.submitTransaction("uploadAccountData", userId, data, timeStamp);
            System.out.println("to upload data...");
        }catch (ContractException | TimeoutException | InterruptedException e){
            System.out.println("chiancode execution error");
            System.out.println(e.getMessage());
            result = "upload error";
            e.printStackTrace();
        }finally {
            if(gateway != null){
                gateway.close();
            }
            //解锁
//            lock.unlock();
        }
        return result;
    }
//    @Transactional
    @Master
    @Override
    public int addDataToCloud(DataURL dataURL) {
        return dataURlMapper.addDataToCloud(dataURL);
    }

    @Override
    public DataURL selectByDataID(String DataID) {
        return dataURlMapper.selectByDataID(DataID);
    }

    //把密文存储到云服务数据库
    public Data saveData(String seedKey, String data, CertUser produce, CertUser process, String timeStamp) {
        //2.1. 创建数据生产和加工的机构对象
        Institution institution = new Institution(produce.getName(), process.getName(), produce, process,
                CodeConvertUtils.convertBytesToHex(CryptoUtils.Sign(data, produce.getEnrollment().getKey())),
                CodeConvertUtils.convertBytesToHex(CryptoUtils.Sign(data, process.getEnrollment().getKey())));
        //2.2. 使用指定seedKey生成AES密钥对数据加密
        String cipher = new EncryptAPI().encryptMessageByAes(seedKey, data).getMessage();
//        System.out.println("cipher:"+cipher.length());
        //2.3. 生产DataID
        String DataID = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //2.4. 把密文存到云服务器文件里
//        FileUtils.saveDataToFile(FabricConfig.cloudFilePath, DataID, cipher);
        //2.5. 上传目录到云数据库存储
        addDataToCloud(new DataURL(DataID, cipher));
        //2.6. 生成URL查询链接
        String URL = FabricConfig.cloudDataBase + "?dataID=" + DataID;
        //2.7. 返回存储区块中的实体类
        return new Data(DataID, institution, URL, timeStamp);
    }

    /**
     *
     * @param certName 证书名
     * @param userId 用户ID
     * @param func searchByKeys | searchByTags | searchByDate
     * @param keys 关键词
     * @param tags 关键标签
     * @param date 日期
     * @return
     */
    @Override
    public Map<Integer, Record> queryData(String certName, String userId, String func, String keys, String tags, String date) {
         Map<Integer, Record> dataMap = new HashMap<>();
        Gateway gateway = GateWayUtils.connectNetwork(certName);
        try {
            Contract contract = GateWayUtils.getContract(gateway, null, null);
            //get timeStamp
            String timeStamp = CodeConvertUtils.Date2TimeStamp(date);
            byte[] result = contract.evaluateTransaction("queryAccountData", userId, func, keys, tags, timeStamp);
			System.out.println("queryData by "+func+":"+new String(result));
            DataUser dataUser = JSON.parseObject(result, DataUser.class);
            System.out.println("dataUser:"+dataUser);
            dataMap = dataUser.getDataMap();
        }catch (ContractException e){
            System.out.println("chiancode execution error");
            e.printStackTrace();
        }finally {
            gateway.close();
        }
        return dataMap;
    }

    @Override
    public DataURL queryCipherByDataBase(String dataID) {
        DataURL dataURL = selectByDataID(dataID);
//        System.out.println("dataURL:"+dataURL);
        return dataURL;
    }
}
