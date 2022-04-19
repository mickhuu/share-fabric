package cn.edu.gxmzu.ai.mickhu.config;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 22:19
 */
public class FabricConfig {
    //默认钱包目录
    public static final Path WALLET_PATH = Paths.get("fabric-web","src","main","resources","wallet");
    //网络连接配置文件
    public static final Path networkConfigPath = Paths.get("fabric-web","src","main","resources","config", "connection-org1.yaml");
    //默认钱包证书
    public static final String certNameDefault = "admin";
    //默认通道名
    public static final String networkNameDefault = "mickhuu";
    //默认链码名
    public static final String contractNameDefault = "Mychaincode";
    //默认云数据库查询地址
    public static final String cloudDataBase = "http://localhost:8080/queryCipherByDataBase";
    //云服务器文件存储目录
    public static final String cloudFilePath = "E:\\workspaceBlockChain\\tmp";

}
