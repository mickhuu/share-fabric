package cn.edu.gxmzu.ai.mickhu.service;


import cn.edu.gxmzu.ai.mickhu.entity.CertUser;

/**
 * @description 注册节点的服务类接口
 * @author mickhu
 * @date 2022-04-07 17:04
 */
public interface NodeService {
    /**
     * 创建组织管理节点用户
     * @param password 密码
     * @param caHost ca机构主机
     * @param caPort ca机构端口
     * @param accessHost 允许交互的主机地址
     * @return
     */
    String createNodeOfAdmin(String password, String caHost, String caPort, String accessHost);

    /**
     * 创建组织普通节点用户
     * @param certUser 节点证书
     * @param caHost ca机构主机地址
     * @param caPort ca机构端口
     * @return
     */
    String createNodeOfUser(CertUser certUser, String caHost, String caPort);

}
