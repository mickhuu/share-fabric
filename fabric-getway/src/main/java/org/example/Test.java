package org.example;

import org.hyperledger.fabric.gateway.*;
import org.hyperledger.fabric.sdk.Peer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        try{
            Properties properties = new Properties();
            InputStream inputStream = Test.class.getResourceAsStream("/fabric.config.properties");
            System.out.println(inputStream);
            properties.load(inputStream);

            String networkConfigPath = properties.getProperty("networkConfigPath");
            String channelName = properties.getProperty("channelName");
            String contractName = properties.getProperty("contractName");
            //使用org1中的user1初始化一个网关wallet账户用于连接网络
            String certificatePath = properties.getProperty("certificatePath");
            X509Certificate certificate = readX509Certificate(Paths.get(certificatePath));

            String privateKeyPath = properties.getProperty("privateKeyPath");
            PrivateKey privateKey = getPrivateKey(Paths.get(privateKeyPath));

            Wallet wallet = Wallets.newInMemoryWallet();
            wallet.put("user111", Identities.newX509Identity("Org1MSP",certificate,privateKey));

            //根据connection.json 获取Fabric网络连接对象
            Gateway.Builder builder = Gateway.createBuilder()
                    .identity(wallet, "user111")
                    .networkConfig(Paths.get(networkConfigPath)).discovery(true);
            //连接网关
            Gateway gateway = builder.connect();
            //获取通道
            Network network = gateway.getNetwork(channelName);
            //获取合约对象
            Contract contract = network.getContract(contractName);

            byte[] result;
            result = contract.evaluateTransaction("getAccount", "111");
            System.out.println("getAccount:"+new String(result));

//            result = contract.evaluateTransaction("queryAllCars");
//            System.out.println(new String(result));
            //查询现有资产
            //注意更换调用链码的具体函数
//            byte[] queryAllAssets = contract.evaluateTransaction("GetAllAssets");
//            System.out.println("所有资产："+new String(queryAllAssets, StandardCharsets.UTF_8));
//
//            // 增加新的资产
//            byte[] invokeResult = contract.createTransaction("CreateAsset")
//                    .setEndorsingPeers(network.getChannel().getPeers(EnumSet.of(Peer.PeerRole.ENDORSING_PEER)))
//                    .submit("asset5", "LaoWang", "666");
//            System.out.println(new String(invokeResult, StandardCharsets.UTF_8));
//
//            //查询更新后的资产
//            byte[] queryAllAssetsAfter = contract.evaluateTransaction("GetAllAssets");
//            System.out.println("更新资产："+new String(queryAllAssetsAfter, StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static X509Certificate readX509Certificate(final Path certificatePath) throws IOException, CertificateException {
        try (Reader certificateReader = Files.newBufferedReader(certificatePath, StandardCharsets.UTF_8)) {
            return Identities.readX509Certificate(certificateReader);
        }
    }

    private static PrivateKey getPrivateKey(final Path privateKeyPath) throws IOException, InvalidKeyException {
        try (Reader privateKeyReader = Files.newBufferedReader(privateKeyPath, StandardCharsets.UTF_8)) {
            return Identities.readPrivateKey(privateKeyReader);
        }
    }
}
