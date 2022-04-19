package cn.edu.gxmzu.ai.mickhu.entity.test;

import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.utils.HFCAClientUtils;
import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.X509Identity;
import org.hyperledger.fabric.sdk.Enrollment;

import java.security.PrivateKey;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-11 16:22
 */
public class GetDefault {
    //test User
    public static CertUser getCertUserDefault(String name) throws Exception{
        String Name = name;
        Set<String> Roles = new HashSet<>();
        Roles.add("node of the org1");
        Roles.add("manager of the org1");
        String Account = "mickhu";
        String Affiliation = "org1";
        String MspId = "Org1MSP";
        Wallet wallet = HFCAClientUtils.getWallet();
//        if (wallet.get("admin") == null) {
//            new NodeServiceImpl().createNodeOfAdmin("my password", "localhost", "7054", "localhost");
//        }
        X509Identity adminIdentity = (X509Identity)wallet.get("admin");
        Enrollment Enrollment = new Enrollment() {
            @Override
            public PrivateKey getKey() {
                return adminIdentity.getPrivateKey();
            }

            @Override
            public String getCert() {
                return Identities.toPemString(adminIdentity.getCertificate());
            }

            @Override
            public String toString() {
                return "Enrollment{Key='" + Base64.getEncoder().encodeToString(getKey().getEncoded()) + '\'' +
                        ", Cert='" + getCert() + '\'' +
                        "}";
            }
        };
        return new CertUser(Name, Roles, Account, Affiliation, Enrollment, MspId);
    }
}
