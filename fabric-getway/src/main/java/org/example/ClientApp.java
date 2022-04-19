/*
SPDX-License-Identifier: Apache-2.0
*/

package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;

public class ClientApp {

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	public static void main(String[] args){
		ClientApp clientApp = new ClientApp();
		clientApp.test();
	}
	
	public byte[] test() {

		byte[] result = new byte[0];
		// create a gateway connection
		try {
			// Load a file system based wallet for managing identities.
			Path walletPath = Paths.get("fabric-getway","src","main","resources","wallet");
			Wallet wallet = Wallets.newFileSystemWallet(walletPath);
			// load a CCP
			Path networkConfigPath = Paths.get("fabric-getway","src","main","resources","config", "connection-org1.yaml");
//		Path networkConfigPath = Paths.get("..", "..", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");

			Gateway.Builder builder = Gateway.createBuilder();
			builder.identity(wallet, "appUser13").networkConfig(networkConfigPath).discovery(true);
			Gateway gateway = builder.connect();

			// get the network and contract
			Network network = gateway.getNetwork("mickhuu");
			Contract contract = network.getContract("Mychaincode");


			//注册账户
//			result = contract.submitTransaction("registerAccount", "111");
//			System.out.println("registerAccount:"+new String(result));

			//获取账户数据
//			result = contract.evaluateTransaction("getAccount", "111");
//			System.out.println("getAccount:"+new String(result));

			//上传数据到账户上
//			contract.submitTransaction("uploadAccountData", "111", "This is a example", String.valueOf(System.currentTimeMillis()));
//			System.out.println("to upload data...");

//			contract.submitTransaction("uploadAccountData", "111", "This is a example aaaaafsfsafsafhnsdjfkghjkldfhgkldfhgidfistjhdrlhtldfhtgkldfntkldfhl发生的复活节发生了封杀了发哈算了fsdhflsdhlsdhl3ir3yiortef00303--..", String.valueOf(System.currentTimeMillis()));
//			contract.submitTransaction("uploadAccountData", "111", "This is a example --..", String.valueOf(System.currentTimeMillis()));
//			for (int i = 3690; i < 10000; i++) {
//
//				contract.submitTransaction("uploadAccountData", "111", "This "+i+"--..", String.valueOf(System.currentTimeMillis()));
//			}

			//再次获取账户数据
			result = contract.evaluateTransaction("getAccount", "111");
			System.out.println("getAccount:"+new String(result));


//			//利用搜索引擎searchByKeys搜索数据
//			long s = System.currentTimeMillis();
//			result = contract.evaluateTransaction("queryAccountData", "111", "searchByKeys", "[example]", "[]", "");
//			System.out.println("searchByKeys:"+new String(result));
//			long e = System.currentTimeMillis();
//			System.out.println(e-s+"ms");
//
//
//			//利用搜索引擎searchByTags搜索数据
//			result = contract.evaluateTransaction("queryAccountData", "111", "searchByAll", "[]", "[person]", "");
//			System.out.println("searchByAll:"+new String(result));
			gateway.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
