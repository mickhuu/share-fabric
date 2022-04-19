package cn.edu.gxmzu.ai.mickhu.utils;

import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @description Hyperledger fabric 客户端API实现类
 * @author mickhu
 * @date 2022-04-07 19:55
 */
public class HFCAClientUtils {
    private static Properties props;
    private static String caClientHostDefault;
    private static String caClientPortDefault;
    private static Path walletPath;
    static {
        props = new Properties();
        //默认组织证书
        props.put("pemFile", "fabric-web/src/main/resources/config/ca.org1.example.com-cert.pem");
        props.put("allowAllHostNames", "true");//生产环境记得关闭
        //默认连接ca的主机
        caClientHostDefault = "localhost";
        //默认连接ca的端口
        caClientPortDefault = "7054";
        //默认钱包目录
        walletPath = Paths.get("fabric-web","src","main","resources","wallet");
    }

    public static HFCAClient getCaClient(String caClientHost, String caClientPort){
        if(caClientHost == null || "".equals(caClientHost)){
            caClientHost = caClientHostDefault;
        }
        if(caClientPort == null || "".equals(caClientPort)){
            caClientPort = caClientPortDefault;
        }
        HFCAClient caClient = null;
        try {
            caClient = HFCAClient.createNewInstance("https://" + caClientHost + ":" + caClientPort, props);
            //add CryptoSuite
            caClient.setCryptoSuite(getCryptoSuite());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return caClient;
    }

    public static CryptoSuite getCryptoSuite(){
        CryptoSuite cryptoSuite = null;
        try {
            cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();
        } catch (CryptoException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return cryptoSuite;
    }

    public static Wallet getWallet(){
        Wallet wallet = null;
        try {
            wallet = Wallets.newFileSystemWallet(walletPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wallet;
    }
}
