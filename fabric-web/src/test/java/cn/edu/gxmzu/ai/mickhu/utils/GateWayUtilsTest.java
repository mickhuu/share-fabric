package cn.edu.gxmzu.ai.mickhu.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


/**
 * @author mickhu
 * @describe
 * @date 2022-04-11 13:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GateWayUtilsTest {

    @Test
    public void connectNetwork() throws IOException {
        String certName = "admin";
//        Path walletPath = Paths.get("fabric-web","src","main","resources","wallet");
//        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
//        // load a CCP
//        Path networkConfigPath = Paths.get("fabric-web","src","main","resources","config", "connection-org1.yaml");
////		Path networkConfigPath = Paths.get("..", "..", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");
//
//        Gateway.Builder builder = Gateway.createBuilder();
//        builder.identity(wallet, "appUser13").networkConfig(networkConfigPath).discovery(true);
//        Gateway gateway = builder.connect();
//        log.println(gateway);
        log.println(GateWayUtils.connectNetwork(certName));
    }
}
