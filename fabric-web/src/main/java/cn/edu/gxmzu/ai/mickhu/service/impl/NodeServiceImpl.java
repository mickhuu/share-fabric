package cn.edu.gxmzu.ai.mickhu.service.impl;

import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.service.NodeService;
import cn.edu.gxmzu.ai.mickhu.utils.HFCAClientUtils;
import org.hyperledger.fabric.gateway.*;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.springframework.stereotype.Service;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;



/**
 * @description 创建节点服务实现类
 * @author mickhu
 * @date 2022-04-07 17:14
 */
@Service
public class NodeServiceImpl implements NodeService {
    //生成环境记得关闭
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }
    @Override
    public String createNodeOfAdmin(String password, String caHost, String caPort, String accessHost) {
        String result = "success";
        //get caClent
        HFCAClient caClient = HFCAClientUtils.getCaClient(caHost, caPort);
        // Create a wallet for managing identities
        Wallet wallet = HFCAClientUtils.getWallet();
        try {
            // Check to see if we've already enrolled the admin user.
            if (wallet.get("admin") != null) {
                System.out.println("An identity for the admin user \"admin\" already exists in the wallet");
                throw new Exception();
            }

            // Enroll the admin user, and import the new identity into the wallet.
            final EnrollmentRequest enrollmentRequestTLS = new EnrollmentRequest();
            //只运行本地址进行交互请求
            enrollmentRequestTLS.addHost(accessHost);
            //设置安全传输协议tls
            enrollmentRequestTLS.setProfile("tls");
            //向链上进行注册，secret为密钥，设置一些安全策略
            Enrollment enrollment = caClient.enroll("admin", password, enrollmentRequestTLS);
            //创建该管理员所属组织的证书
            Identity user = Identities.newX509Identity("Org1MSP", enrollment);
            //输出证书到钱包里
            wallet.put("admin", user);
            System.out.println("Successfully enrolled user \"admin\" and imported it into the wallet");
        }catch (Exception e){
            result = "error";
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String createNodeOfUser(CertUser certUser, String caHost, String caPort) {
        String result = "success";
        try{
            //get caClent
            HFCAClient caClient = HFCAClientUtils.getCaClient(caHost, caPort);

            // Create a wallet for managing identities
            Wallet wallet = HFCAClientUtils.getWallet();

            // Check to see if we've already enrolled the user.
            if (wallet.get(certUser.getName()) != null) {
                System.out.println("An identity for the user \""+ certUser.getName() +"\" already exists in the wallet");
                return "error";
            }

            X509Identity adminIdentity = (X509Identity)wallet.get("admin");
            if (adminIdentity == null) {
                System.out.println("\"admin\" needs to be enrolled and added to the wallet first");
                return "error";
            }

            // Register the user, enroll the user, and import the new identity into the wallet.
            //发起该节点的注册请求附带一些节点信息
            RegistrationRequest registrationRequest = new RegistrationRequest("my name is ZhangSan, i come from china...");
            //设置部门
            registrationRequest.setAffiliation(certUser.getAffiliation());
            //设置节点id
            registrationRequest.setEnrollmentID(certUser.getName());
            //使用admin证书进行认证注册生成密码
            String enrollmentSecret = caClient.register(registrationRequest, certUser);
//            System.out.println("enrollmentSecret:"+enrollmentSecret);
            //通过ca机构注册节点
            Enrollment enrollment = caClient.enroll(certUser.getName(), enrollmentSecret);
            //节点证书
            Identity node = Identities.newX509Identity("Org1MSP", enrollment);
            //输出节点证书到钱包
            wallet.put(certUser.getName(), node);
            System.out.println("Successfully enrolled user \""+certUser.getName()+"\" and imported it into the wallet");
        } catch (Exception e){
            result = "error";
            e.printStackTrace();
        }
        return result;
    }
}
