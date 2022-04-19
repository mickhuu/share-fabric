package cn.edu.gxmzu.ai.mickhu.utils;

import org.hyperledger.fabric.gateway.*;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 与区块链网络交互的API实现类
 * @author mickhu
 * @date 2022-04-07 18:23
 */
public class GateWayUtils {
    private static Path walletPath;
    private static Path networkConfigPath;
    private static String certNameDefault;
    private static String networkNameDefault;
    private static String contractNameDefault;
    static {
        //默认钱包目录
        walletPath = Paths.get("fabric-web","src","main","resources","wallet");
        //网络连接配置文件
        networkConfigPath = Paths.get("fabric-web","src","main","resources","config", "connection-org1.yaml");
        //默认钱包证书
        certNameDefault = "admin";
        //默认通道名
        networkNameDefault = "mickhuu";
        //默认合约名
        contractNameDefault = "Mychaincode";
    }

    public GateWayUtils() {
    }

    public static Gateway connectNetwork(String certName){
        if(certName == null || "".equals(certName)){
            certName = certNameDefault;
        }
        Gateway gateway = null;
        try {
            // Load a file system based wallet for managing identities.
            Wallet wallet = Wallets.newFileSystemWallet(walletPath);
            // load a CCP
            Gateway.Builder builder = Gateway.createBuilder();
            //生产环境记得关闭
            builder.identity(wallet, certName).networkConfig(networkConfigPath).discovery(true);
            //get a gateway instance
            gateway = builder.connect();
        }catch (Exception e){
            System.out.println("connection network error, pleace check certX.509... ");
            e.printStackTrace();
        }
       return gateway;
    }

    public static Contract getContract(Gateway gateway, String networkName, String contractName){
        if(networkName == null || "".equals(networkName)){
            networkName = networkNameDefault;
        }
        if(contractName == null || "".equals(contractName)){
            contractName = contractNameDefault;
        }
        Contract contract = null;
        try{
            // get the network and contract
            Network network = gateway.getNetwork(networkName);
            contract = network.getContract(contractName);
        }catch (Exception e){
            System.out.println("get contract error");
            e.printStackTrace();
        }
        return contract;
    }

}
