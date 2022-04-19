package cn.edu.gxmzu.ai.mickhu.service;


import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.entity.DataURL;
import cn.edu.gxmzu.ai.mickhu.entity.test.DataUser;
import cn.edu.gxmzu.ai.mickhu.entity.test.Record;

import java.util.Map;

/**
 * @description 操作节点的服务类接口
 * @author mickhu
 * @date 2022-04-07 18:53
 */
public interface UserService {

    /**
     * 同过节点创建用户
     * @param certUser 节点证书
//     * @param caHost ca机构主机地址
//     * @param caPort ca机构端口
     * @param id 创建指定id的用户
     * @return
     */
    String createUser(CertUser certUser, String id);

    /**
     * 获取节点用户信息
     * @param certName 证书名
     * @param userId 用户ID
     * @return 节点用户信息
     */
    Map<Integer, Record> getUser(String certName, String userId);

    /**
     * 节点上传数据
     * @param certName 证书名
     * @param userId 用户ID
     * @param data 数据
     * @return code
     */
    String uploadData(String seedKey, String certName, String userId, String data, CertUser produce, CertUser process);

    /**
     * 添加数据密文到云数据库
     * @param dataURL 数据密文ID与密文
     * @return
     */
    int addDataToCloud(DataURL dataURL);

    /**
     * 查询指定密文
     * @param DataID 指定数据密文ID
     * @return 返回数据密文ID和URL链接
     */
    DataURL selectByDataID(String DataID);

    /**
     * 通过搜索引擎查询数据
     * @param certName 证书名
     * @param userId 用户ID
     * @param func 搜索方法
     * @param keys 关键词
     * @param tags 关键标签
     * @param date 日期
     * @return
     */
    Map<Integer, Record> queryData(String certName, String userId, String func, String keys, String tags, String date);

    /**
     * 查看云数据库密文
     * @param dataID 数据密文ID
     * @return 返回数据密文
     */
    DataURL queryCipherByDataBase(String dataID);
}
