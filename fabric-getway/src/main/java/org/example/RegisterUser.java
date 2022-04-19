/*
SPDX-License-Identifier: Apache-2.0
*/

package org.example;

import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.Properties;
import java.util.Set;

import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Identity;
import org.hyperledger.fabric.gateway.X509Identity;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;

public class RegisterUser {

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	public static void main(String[] args) throws Exception {

		// Create a CA client for interacting with the CA.
		Properties props = new Properties();
		props.put("pemFile",
			"fabric-getway/src/main/resources/config/ca.org1.example.com-cert.pem");
//			"../../test-network/organizations/peerOrganizations/org1.example.com/ca/ca.org1.example.com-cert.pem");

		props.put("allowAllHostNames", "true");
		HFCAClient caClient = HFCAClient.createNewInstance("https://localhost:7054", props);
		CryptoSuite cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();
		caClient.setCryptoSuite(cryptoSuite);

		// Create a wallet for managing identities
		Wallet wallet = Wallets.newFileSystemWallet(Paths.get("fabric-getway/src/main/resources/wallet"));

		//要注册的节点名
		String userName = "appUser13";
		// Check to see if we've already enrolled the user.
		if (wallet.get(userName) != null) {
			System.out.println("An identity for the user \"appUser\" already exists in the wallet");
			return;
		}

		X509Identity adminIdentity = (X509Identity)wallet.get("admin");
		if (adminIdentity == null) {
			System.out.println("\"admin\" needs to be enrolled and added to the wallet first");
			return;
		}

		User admin = new User() {

			@Override
			public String getName() {
				return "mickhu";
			}

			@Override
			public Set<String> getRoles() {
				return null;
			}

			@Override
			public String getAccount() {
				return null;
			}

			@Override
			public String getAffiliation() {
				return "org1.department1";
			}

			@Override
			public Enrollment getEnrollment() {
				return new Enrollment() {

					@Override
					public PrivateKey getKey() {
						return adminIdentity.getPrivateKey();
					}

					@Override
					public String getCert() {
						return Identities.toPemString(adminIdentity.getCertificate());
					}
				};
			}

			@Override
			public String getMspId() {
				return "Org1MSP";
			}

		};

		// Register the user, enroll the user, and import the new identity into the wallet.
		//发起该节点的注册请求附带一些节点信息
		RegistrationRequest registrationRequest = new RegistrationRequest("my name is ZhangSan, i come from china...");
		//设置部门
		registrationRequest.setAffiliation(admin.getAffiliation());
		//设置节点id
		registrationRequest.setEnrollmentID(userName);
		//使用admin证书进行认证注册生成密码
		String enrollmentSecret = caClient.register(registrationRequest, admin);
		System.out.println("enrollmentSecret:"+enrollmentSecret);
		//通过ca机构注册节点
		Enrollment enrollment = caClient.enroll(userName, enrollmentSecret);
//		Enrollment enrollment = caClient.enroll("appUser11", "hahah");
		//节点证书
		Identity node = Identities.newX509Identity("Org1MSP", enrollment);
		//输出节点证书到钱包
		wallet.put(userName, node);
		System.out.println("Successfully enrolled user \"appUser\" and imported it into the wallet");
	}

}
